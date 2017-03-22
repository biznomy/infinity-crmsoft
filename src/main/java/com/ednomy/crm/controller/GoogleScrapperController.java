/**
 *
 */
package com.ednomy.crm.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.location.LatLongCalculator;
import com.ednomy.crm.commons.location.Point;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdGoogleScrapper;
import com.ednomy.crm.model.EdPoint;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.EdGoogleScrapperService;
import com.ednomy.crm.service.EdPointService;
import com.ednomy.crm.util.EdResponse;
import com.ednomy.crm.util.FileUtility;
import com.ednomy.crm.util.GoogleScrapperUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/scrapper")
public class GoogleScrapperController {
	
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
	private EdGoogleScrapperService googleScrapperListService;
	
	//devs.bzn account
	private String apiKey = "AIzaSyA0tFWxANSZcRUWjbgao2K-4Oyu0KSyU";	
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	
	
//	@Scheduled(cron = "1 */1 * * * *")//second, minute, hour, day of month, month, day(s) of week
	public void schedular(){
		updateLoaded(this.apiKey);		
	}

	
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public EdResponse<Integer> searchPlaces(@RequestParam("latlng") String latlng, @RequestParam("radius") double radius, 
			@RequestParam("keyword") String keyword, @RequestParam("apikey") String apikey) {
		final EdResponse<Integer> response = new EdResponse<Integer>();
		
		if (latlng.isEmpty()) {
			latlng = "28.612612, 77.229048";
		}
		if (keyword.isEmpty()) {
			keyword = "coaching";
		}
		if (radius <= 0.0) {
			radius = 10 * 1000;			
		}
		if (apikey.isEmpty()) {
			apikey = this.apiKey;
		}
		
		String urlEncode = ("location="+latlng+"&radius="+radius+"&keyword="+keyword+"&key="+apikey).replaceAll("\\s", "");
		String url = "https://maps.googleapis.com/maps/api/place/radarsearch/json?"+urlEncode;
		
		LOGGER.debug(url);

		JsonNode jsonNode = null;
		JsonNode resultNode = null;
		
		jsonNode = googleScrapperUtil.getRequest(url);
		
		if (jsonNode != null && jsonNode.has("results")) {
			long count=0;
			resultNode = jsonNode.get("results");
			googleScrapperUtil.iterateJsonNode(resultNode, count);
			response.setResult(resultNode.size());
			response.setCode(EdErrorCode.SUCCESS_CODE);
			response.setCount(count);
			response.setMessage(EdErrorCode.SUCCESS_MSG);
		}else{
			response.setResult(0);
		}	

		return response;
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	@ResponseBody
	public EdResponse<Integer> updateLoaded(@RequestParam("apikey") String apiKey){
		final EdResponse<Integer> response = new EdResponse<Integer>();
		
		String query = "{\"query\":{\"edGoogleScrapper\":{\"filter\":{\"detailStatus\":{\"equals\":0}}}},\"page\":{\"start\":0,\"max\":10}}";
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		Set<EdGoogleScrapper> edGoogleScrappers = googleScrapperListService.list(queryMap);
		
		Iterator<EdGoogleScrapper> iterator = edGoogleScrappers.iterator();
		while (iterator.hasNext()) {
			EdGoogleScrapper edGoogleScrapper = (EdGoogleScrapper) iterator.next();
			String placeId = edGoogleScrapper.getPlaceId(); 
			String url = "https://maps.googleapis.com/maps/api/place/details/json?placeid="+placeId+"&key="+apiKey;
			JsonNode jsonNode = googleScrapperUtil.getRequest(url);
			if (jsonNode != null && jsonNode.has("result")) {
				JsonNode resultNode = jsonNode.get("result");
				googleScrapperUtil.addDetail(resultNode, edGoogleScrapper);				
			}			
		}
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		googleScrapperUtil.updateAddress();
		return response;
	}
	
	@RequestMapping(value = "/circular", method = RequestMethod.POST)
	@ResponseBody
	public void circularRadar(@RequestParam("file") MultipartFile multipartFile, @RequestParam("log") boolean log, 
			@RequestParam("radius") double radius, @RequestParam("distance") double distance, 
			@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude, @RequestParam("keywords") String keywords) throws UnsupportedEncodingException{
		
		keywords = URLDecoder.decode(keywords, "UTF-8");
		
		JsonNode jsonNode = null;
		try{
			File file = fileUtility.multipartToFile(multipartFile);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsondata = mapper.readTree(file);
			jsonNode = jsondata.findValue("coordinates");
			
		}catch(Exception exception){
			throw new EdnomyException(exception.getMessage());
		}
			
		if (jsonNode == null) {
			throw new EdnomyException("missing \"coordinates\" attributes inside file");
		}	
			
		try {
			
			latLongCalculator.setPrintLogger(log);
			Map<String, List<Point>> map = latLongCalculator.getResult(jsonNode, distance, latitude, longitude);
			
			String[] keywordArray = StringUtils.splitByWholeSeparator(keywords, ",");
			
			LOGGER.info("Total Boundary Result : "+ map.get("result").size());
			
			for (int i = 0; i < keywordArray.length; i++) {
				Iterator<Point> iterator = map.get("result").iterator();
				while (iterator.hasNext()) {
					Point point = (Point) iterator.next();
					LOGGER.info(point.getLatitude()+","+point.getLongitude());
					//search for coaching
					searchPlaces(point.getLatitude()+","+point.getLongitude(), radius, keywordArray[i], "");					
				}				
			}			
			
		} catch (Exception exception) {
			throw new EdnomyException(exception.getMessage());
		}
	}
	
	
	@RequestMapping(value = "/loadplaces", method = RequestMethod.GET)
	@ResponseBody
	public void uploadPlaces(@RequestParam("keywords") String keywords, @RequestParam("jsq") String jsq) throws UnsupportedEncodingException{
		
		keywords = URLDecoder.decode(keywords, "UTF-8");
	
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, jsq);
		
		List<EdPoint> edPoints = edPointService.list(queryMap);
		
		String[] keywordArray = StringUtils.splitByWholeSeparator(keywords, ",");
		
		LOGGER.info("all keywords : "+ keywords);
		
		for (int i = 0; i < keywordArray.length; i++) {
			Iterator<EdPoint> iterator = edPoints.iterator();
			while (iterator.hasNext()) {
				EdPoint edPoint = (EdPoint) iterator.next();
				LOGGER.info(edPoint.getLatitude()+","+edPoint.getLongitude());
				searchPlaces(edPoint.getLatitude()+","+edPoint.getLongitude(), edPoint.getDistance() * 1000, keywordArray[i].replaceAll("\\s", "+"), "");
				edPoint.setStatus(1);
				edPointService.patch(edPoint);
			}				
		}			
	}
	
	
	@Scheduled(cron = "1 * * * * *")//second, minute, hour, day of month, month, day(s) of week
	public void schedular2() throws UnsupportedEncodingException{
		String keywords = "hardware+store,tools+store,safety+equipment+store,electrical+store,hydraulics+store,penumatics+store,sanitary+store,plmbing+store,hardware+dealers,tools+dealers,safety+equipment+dealers,electrical+dealers,hydraulics+dealers,penumatics+dealers,sanitary+dealers,plmbing+dealers,hardware+supplier,tools+supplier,safety+equipment+supplier,electrical+supplier,hydraulics+supplier,penumatics+supplier,sanitary+supplier,plmbing+supplier";
		String jsq = "{\"query\":{\"edPoint\":{\"filter\":{\"code1\":{\"equals\":\"IN\"},\"code2\":{\"equals\":\"DELHI-NCR\"},\"status\":{\"equals\":0}}}},\"page\":{\"start\":1,\"max\":100}}";
		uploadPlaces(keywords, jsq);		
	}
}