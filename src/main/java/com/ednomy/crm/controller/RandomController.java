/**
 *
 */
package com.ednomy.crm.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.location.LatLongCalculator;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdContentCollection;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.model.EdGoogleScrapper;
import com.ednomy.crm.model.EdPoint;
import com.ednomy.crm.model.EdUser;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.EdGoogleScrapperService;
import com.ednomy.crm.service.EdPointService;
import com.ednomy.crm.util.EdResponse;
import com.ednomy.crm.util.FileUtility;
import com.ednomy.crm.util.GoogleScrapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class RandomController {
	
	@Autowired
	LatLongCalculator latLongCalculator;

	@Autowired
	FileUtility fileUtility;
	
	@Autowired
	private GoogleScrapperUtil googleScrapperUtil;
	
	@Autowired
	private EdContentDataService edContentDataService;
	
	@Autowired
	private EdPointService edPointService; 
	
	@Autowired
	private EdGoogleScrapperService edGoogleScrapperService;
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	//devs.bzn account
	private String apiKey = "AIzaSyA0tFWxANSZcRUWjbgao2K-4Oyu0KSzU";
	
	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public String google() {
		return "google";
	}
	
	@RequestMapping(value = "/edpoints", method = RequestMethod.GET)
	public String uploadPoints() {
		return "edpoints";
	}
	
	
	/**
	 * Having coordinate points from .json file from request /edpoints and 
	 * parse file and read content and separate distance, country and location, 
	 * their after insert coordinate information to database
	 * @param multipartFile
	 * @param request
	 * @return
	 * @throws JsonProcessingException
	 * @throws IOException
	 */
	@RequestMapping(value = "/points", method = RequestMethod.POST)
	@ResponseBody
	public EdResponse<String> uploadPoints(@RequestParam("file") CommonsMultipartFile multipartFile, HttpServletRequest request) throws JsonProcessingException, IOException {
		EdResponse<String> docHandlerResponse = new EdResponse<String>();
		
		byte[] bytes = multipartFile.getBytes();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(bytes);
		JsonNode coordinates = jsonNode.get("co-ordinates");
		
		double distance = jsonNode.get("distance").asDouble();
		String country = jsonNode.get("country").asText();
		String location = jsonNode.get("location").asText();
		
		Iterator<String> iterator = coordinates.fieldNames();
		while (iterator.hasNext()) {
			
			String string = (String) iterator.next();
			
			EdPoint edPoint = new EdPoint();
			
			String[] latlng = coordinates.get(string).asText().split(",");
			
			edPoint.setLatitude(Double.parseDouble(latlng[0].trim()));
			edPoint.setLongitude(Double.parseDouble(latlng[1].trim()));
			edPoint.setLatlng(coordinates.get(string).asText());
			edPoint.setCode1(country);
			edPoint.setCode2(location);
			edPoint.setDistance(distance);
			edPointService.save(edPoint);
			
		}
				
		return docHandlerResponse;
	}
	
	
	@RequestMapping(value = "/places", method = RequestMethod.POST)
	@ResponseBody
	public EdResponse<Integer> addPlaces(@RequestParam("file") CommonsMultipartFile multipartFile, @RequestParam("keywords") String keywords) throws JsonProcessingException, IOException {
		final EdResponse<Integer> response = new EdResponse<Integer>();
		
		keywords = URLDecoder.decode(keywords, "UTF-8");
		
		String [] searches = org.springframework.util.StringUtils.commaDelimitedListToStringArray(keywords);
		
		byte[] bytes = multipartFile.getBytes();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(bytes);
		JsonNode coordinates = jsonNode.get("co-ordinates");
		double distance = jsonNode.get("distance").asDouble();
		
		Iterator<String> iterator = coordinates.fieldNames();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String latlng = coordinates.get(key).asText();
			for (String keyword : searches) {
				keyword = keyword.replaceAll("\\s", "+");
				String urlEncode = ("location="+latlng+"&radius="+(distance * 1000)+"&keyword="+keyword+"&key="+this.apiKey).replaceAll("\\s", "");
				String url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+urlEncode;
				JsonNode jsonResult = googleScrapperUtil.getRequest(url);
				if (jsonResult != null && jsonResult.has("results")) {
					long count=0;
					JsonNode resultNode = jsonResult.get("results");
					googleScrapperUtil.iterateJsonNode(resultNode, count);			
				}
			}
		}
		return response;
	}	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/google-calc", method = RequestMethod.POST)
	public void googleCalc(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException {
		Map<Double, Double> resultMap = new HashMap<Double, Double>();
		
		double distance  =  Double.parseDouble(request.getParameter("distance"));
		String country = request.getParameter("country");
		String location = request.getParameter("location");
		
		String northWest =  request.getParameter("nw-cordinate");
		//String northEast =  request.getParameter("ne-cordinate");
		String southWest =  request.getParameter("sw-cordinate");
		String southEast =  request.getParameter("se-cordinate");
		
		
		double northWestLat = Double.parseDouble(northWest.split(",")[0].trim());
		double southWestLat = Double.parseDouble(southWest.split(",")[0].trim());
		double northWestLng = Double.parseDouble(northWest.split(",")[1].trim());
		double southEastLng = Double.parseDouble(southEast.split(",")[1].trim());
		
		Map<Double, Double> tempMap = new HashMap<Double, Double>(); 
		List<Double> temp = null;
		
		double northWestLatTemp = northWestLat;
		double northWestLngTemp = northWestLng;
		do {
			double radianV = 180;
			temp = calculateCordinates(distance, northWestLatTemp, northWestLngTemp, radianV);
			tempMap.put(temp.get(0), temp.get(1));
			northWestLatTemp = temp.get(0);
			northWestLngTemp = temp.get(1);
		} while (temp.get(0) > southWestLat);
		
		resultMap.putAll(tempMap);
		
		Iterator<Entry<Double,Double>> iterator = tempMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Double,Double> entry = (Map.Entry<Double,Double>) iterator.next();
			
			double toSouthEastLatTemp = entry.getKey();
			double toSouthEastLngTemp = entry.getValue();
			do {
				double radianH = 90;
				temp = calculateCordinates(distance, toSouthEastLatTemp, toSouthEastLngTemp, radianH);
				toSouthEastLatTemp = temp.get(0);
				toSouthEastLngTemp = temp.get(1);
				resultMap.put(temp.get(0), temp.get(1));
			} while (temp.get(1) < southEastLng);
		} 
		
		File file = ResourceUtils.getFile("classpath:notification-template//co-ordinates.json");
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode cordinateNode =  objectMapper.createObjectNode();

		ObjectNode jsonNode =  objectMapper.createObjectNode();
		jsonNode.put("cordinatesby", "http://ednomy.com");
		jsonNode.put("distance", distance);
		jsonNode.put("country", country);
		jsonNode.put("location", location.toUpperCase());
		
		long count = 0 ;
		Iterator<Entry<Double,Double>> it = resultMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Double,Double> entry = (Map.Entry<Double,Double>) it.next();
			cordinateNode.put(String.valueOf(count), entry.getKey()+","+entry.getValue());
			LOGGER.info(entry.getKey()+","+entry.getValue());
			count++;
		}
		jsonNode.put("co-ordinates", cordinateNode);
		FileUtils.write(file, String.valueOf(jsonNode));
		FileInputStream inputStream = new FileInputStream(file);
		
		String mimeType = "application/octet-stream";
		response.setContentType(mimeType);
        response.setContentLength((int) file.length());
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", country+"_"+location+".json");
        response.setHeader(headerKey, headerValue);
 
        // get output stream of the response
        OutputStream outStream = response.getOutputStream();
        int BUFFER_SIZE = 4096;
 
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;
 
        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
 
        inputStream.close();
        outStream.close();		
	}
	
	
	private List<Double> calculateCordinates(double distance, double lat, double lng, double radian){
		List<Double> result = null;		
		
		double dist = distance/ 6371.0;
		double brng = Math.toRadians(radian);
		double lat1 = Math.toRadians(lat);
		double lon1 = Math.toRadians(lng);
		double lat2 = Math.asin( Math.sin(lat1)*Math.cos(dist) + Math.cos(lat1)*Math.sin(dist)*Math.cos(brng) );
		double a = Math.atan2(Math.sin(brng)*Math.sin(dist)*Math.cos(lat1), Math.cos(dist)-Math.sin(lat1)*Math.sin(lat2));
		double lon2 = lon1 + a;
		lon2 = (lon2+ 3*Math.PI) % (2*Math.PI) - Math.PI;
		result = new ArrayList<Double>();
		result.add(0, Math.toDegrees(lat2));
		result.add(1, Math.toDegrees(lon2));
		return result;
	}
	
	
	
	
	@RequestMapping(value = "/crm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = "application/json")
	@ResponseBody
	public EdResponse<String> updateStatus(@RequestParam Map<String, String> data) {
		final EdResponse<String> response = new EdResponse<String>();
		
		List<Long> companies = new ArrayList<Long>();  
		String comapnyArray = data.get("companies");
		String[] company = org.springframework.util.StringUtils.commaDelimitedListToStringArray(comapnyArray);
		Long endUser = null;
		try{
			for (String comp : company) {
				companies.add(Long.parseLong(comp));
			}
			endUser = Long.parseLong(data.get("endUser"));
		}catch(NumberFormatException numberFormatException){
			throw new EdnomyException("data format is incorrect");
		} 
		
		String sendMessage = data.get("message");
		String mediaLink = data.get("media");
		String updatedBy = data.get("updatedBy");
		String type = data.get("type");
		
		if (sendMessage == null || sendMessage.trim().isEmpty()) {
			throw new EdnomyException("message missing");
		}
		if (mediaLink == null || mediaLink.trim().isEmpty()) {
			throw new EdnomyException("media missing");
		}
		if (updatedBy == null || updatedBy.trim().isEmpty()) {
			throw new EdnomyException("updatedBy missing");
		}
		if (type == null || type.trim().isEmpty()) {
			throw new EdnomyException("type missing");
		}
		if (endUser == null) {
			throw new EdnomyException("endUser missing");
		}
		if (companies.isEmpty()) {
			throw new EdnomyException("companies missing");
		}
				
		response.setResult(edContentDataService.updateCRMStatus(companies, sendMessage, mediaLink, updatedBy, type, endUser));
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
//	@Scheduled(cron = "* * * * * *")//second, minute, hour, day of month, month, day(s) of week
	public void moveGoogleScrapperToData(){
		
		String query = "{\"query\":{\"edGoogleScrapper\":{\"filter\":{\"status\":{\"equals\":0},\"detailStatus\":{\"notequals\":0}}}},\"page\":{\"start\":0,\"max\":100}}";
		Map<String, String> queryMap = new HashMap<String, String>(); 
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		Iterator<EdGoogleScrapper> iterator = edGoogleScrapperService.list(queryMap).iterator();
		
		while (iterator.hasNext()) {
			EdGoogleScrapper edGoogleScrapper = (EdGoogleScrapper) iterator.next();
			updateData(edGoogleScrapper);
		}		
	}


	private void updateData(EdGoogleScrapper edGoogleScrapper) {
		
		EdContentData edContentData = new EdContentData();
		edContentData.setTitle(edGoogleScrapper.getName());
		edContentData.setDetail(edGoogleScrapper.getFormattedAddress());
		edContentData.setLt6(edGoogleScrapper.getVicinity());
		
		edContentData.setSt1(edGoogleScrapper.getLocality());
		edContentData.setSt2(edGoogleScrapper.getAdministrativeAreaLevel1());
		edContentData.setSt3(edGoogleScrapper.getCountry());
		
		edContentData.setCategory1("education");
//		edContentData.setCategory2("category2");
//		edContentData.setCategory3("category3");
		
		edContentData.setSt4(edGoogleScrapper.getWebsite());
		
		
		String contact = edGoogleScrapper.getInternationalPhoneNumber();
		
		contact = contact.replace("+91", "").trim();
		String[] ss = contact.split("\\s");
		if (ss != null && ss.length > 1 && ss[0].length() > 4) {
			edContentData.setSt7(","+edGoogleScrapper.getInternationalPhoneNumber()+",");//mobile number
		}else if(contact.length() >= 7){
			edContentData.setSt6(","+edGoogleScrapper.getInternationalPhoneNumber()+",");//landline number
		}
		
//		edContentData.setSt5("email");
//		edContentData.setSt8("link");
		
//		edContentData.setLt1("twitter");
//		edContentData.setLt2("fb");
//		edContentData.setLt3("linkedIn");
//		edContentData.setLt4("whatsapp");
		
		edContentData.setLn1(0L);//"fb status"
		edContentData.setLn2(0L);//"linkedin status"
		edContentData.setLn3(0L);//"twitter status"
		edContentData.setLn4(0L);//"whatsapp status"
		
		edContentData.setLt7(edGoogleScrapper.getGoogleURL());
		
//		edContentData.setLt8("remark1");
//		edContentData.setLt9("remark2");
//		edContentData.setLt10("remark3");
		
		
		edContentData.setLanguage("en");
		
		EdUser endUser = new EdUser();
		endUser.setId(123456L);
		edContentData.setEndUser(endUser);
		
		edContentData.setType("company");
		edContentData.setRole("End User");
		
		
		EdContentCollection edContentCollection = new EdContentCollection();
		edContentCollection.setId(123456L);
		edContentData.setEdContentCollection(edContentCollection);
		
		edContentData.setSt10(edGoogleScrapper.getScope());//justdail
		
		edContentData.setSt11(edGoogleScrapper.getLat());
		edContentData.setSt12(edGoogleScrapper.getLng());
		edContentData.setSt13(edGoogleScrapper.getPlaceId());
		
		edContentData.setLt11(edGoogleScrapper.getAddressComponents());
		edContentData.setLt12(edGoogleScrapper.getCompleteDetail());
		
		edContentDataService.save(edContentData);
		
		edGoogleScrapper.setStatus(1);
		edGoogleScrapperService.patch(edGoogleScrapper);
		LOGGER.info("done");
	}
	
}