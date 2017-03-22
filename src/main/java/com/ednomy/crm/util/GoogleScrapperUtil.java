package com.ednomy.crm.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdGoogleScrapper;
import com.ednomy.crm.service.EdGoogleScrapperService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GoogleScrapperUtil {

	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EdGoogleScrapperService edGoogleScrapperService;

	public JsonNode getRequest(String url) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);

		// add request header
		request.addHeader("User-Agent", "");
		HttpResponse response = null;
		try {
			response = client.execute(request);
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (response == null) {
			return null;
		}

		if (response.getStatusLine().getStatusCode() != 200) {
			return null;
		}

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}

		StringBuffer result = new StringBuffer();
		String line = "";
		try {
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		LOGGER.info(result.toString());

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonNode = null;
		try {
			jsonNode = objectMapper.readTree(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonNode;
		
	}

	public void iterateJsonNode(JsonNode resultNode, long count) {
		EdGoogleScrapper edGoogleScrapper = null;
		Iterator<JsonNode> iterator = resultNode.iterator();
		while (iterator.hasNext()) {
			
			edGoogleScrapper = new EdGoogleScrapper();

			JsonNode singleResultNode = (JsonNode) iterator.next();

			if (singleResultNode.findPath("lat") != null) {
				edGoogleScrapper.setLat(singleResultNode.findPath("lat").asText());
			}
			
			if (singleResultNode.findPath("lng") != null) {
				edGoogleScrapper.setLng(singleResultNode.findPath("lng").asText());
			}
			
			if (singleResultNode.findPath("id") != null) {
				edGoogleScrapper.setId(singleResultNode.findPath("id").asText());
			}
			
			if (singleResultNode.findPath("place_id") != null) {
				edGoogleScrapper.setPlaceId(singleResultNode.findPath("place_id").asText());
			}
			
			if (singleResultNode.findPath("reference") != null) {
				edGoogleScrapper.setReference(singleResultNode.findPath("reference").asText());
			}
			
			try{
				try{
					edGoogleScrapperService.get(edGoogleScrapper.getId());
					count++;					
				}catch(EdnomyException ednomyException){
					edGoogleScrapperService.save(edGoogleScrapper);					
				}
			}catch(DataIntegrityViolationException exception){
				LOGGER.error(exception.getMessage(), exception);
			}
			
			edGoogleScrapper = null;
		}		
	}
	
	public void addDetail(JsonNode singleResultNode, EdGoogleScrapper edGoogleScrapper) {
				
		if (singleResultNode.findPath("address_components") != null) {
			edGoogleScrapper.setAddressComponents(singleResultNode.findPath("address_components").toString());
		}
		
		if (singleResultNode.findPath("formatted_address") != null) {
			edGoogleScrapper.setFormattedAddress(singleResultNode.findPath("formatted_address").asText());
		}
		
		if (singleResultNode.findPath("formatted_phone_number") != null) {
			edGoogleScrapper.setFormattedNumber(singleResultNode.findPath("formatted_phone_number").asText());
		}
		
		if (singleResultNode.findPath("international_phone_number") != null) {
			edGoogleScrapper.setInternationalPhoneNumber(singleResultNode.findPath("international_phone_number").asText());
		}
		
		if (singleResultNode.findPath("name") != null) {
			edGoogleScrapper.setName(singleResultNode.findPath("name").asText());
		}
		
		if (singleResultNode.findPath("scope") != null) {
			edGoogleScrapper.setScope(singleResultNode.findPath("scope").asText());
		}
		
		if (singleResultNode.findPath("url") != null) {
			edGoogleScrapper.setGoogleURL(singleResultNode.findPath("url").asText());
		}
		
		if (singleResultNode.findPath("vicinity") != null) {
			edGoogleScrapper.setVicinity(singleResultNode.findPath("vicinity").asText());
		}
		
		if (singleResultNode.findPath("website") != null) {
			edGoogleScrapper.setWebsite(singleResultNode.findPath("website").asText());
		}
		
		edGoogleScrapper.setCompleteDetail(singleResultNode.toString());
		edGoogleScrapper.setDetailStatus(1);
		
		try{
			edGoogleScrapperService.patch(edGoogleScrapper);
		}catch(DataIntegrityViolationException exception){
			LOGGER.error(exception.getMessage(), exception);
		}		
	}
	
	@Async
	public void updateAddress(){
		
		String query = "{\"query\":{\"edGoogleScrapper\":{\"filter\":{\"addressComponents\":{\"isnotnull\":\"\"},\"country\":{\"isnull\":\"\"}}}},\"page\":{\"start\":0,\"max\":1000}}";
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		
		Set<EdGoogleScrapper> edGoogleScrappers = edGoogleScrapperService.list(queryMap);
		Iterator<EdGoogleScrapper> iterator = edGoogleScrappers.iterator();
		
		while (iterator.hasNext()) {
			EdGoogleScrapper edGoogleScrapper = (EdGoogleScrapper) iterator.next();
			if(edGoogleScrapper.getAddressComponents() != null && edGoogleScrapper.getCountry() == null){
				
				JsonNode jsonNode = null;
				try{
					ObjectMapper objectMapper = new ObjectMapper();
					jsonNode = objectMapper.readTree(edGoogleScrapper.getAddressComponents());
				}catch(Exception exception){
					LOGGER.error(exception.getMessage(), exception);
				}
				
				if (jsonNode != null && jsonNode.isArray()) {
					Iterator<JsonNode> jsonNodes = jsonNode.elements();
					while (jsonNodes.hasNext()) {
						JsonNode jsonNode2 = (JsonNode) jsonNodes.next();
						
						if (jsonNode2.has("types")) {
							JsonNode node = jsonNode2.findPath("types");
							Iterator<JsonNode> iterator2 = node.iterator();
							while (iterator2.hasNext()) {
								JsonNode jsonNode3 = (JsonNode) iterator2.next();
								if (jsonNode3.asText().equalsIgnoreCase("country")) {
									edGoogleScrapper.setCountry(jsonNode2.findValue("long_name").asText());
									edGoogleScrapper.setCountryCode(jsonNode2.findValue("short_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("postal_code")) {
									edGoogleScrapper.setPostalCode(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("administrative_area_level_1")) {
									edGoogleScrapper.setAdministrativeAreaLevel1(jsonNode2.findValue("long_name").asText());
									edGoogleScrapper.setAdministrativeAreaLevel1Code(jsonNode2.findValue("short_name").asText());									
								}
								if (jsonNode3.asText().equalsIgnoreCase("administrative_area_level_2")) {
									edGoogleScrapper.setAdministrativeAreaLevel2(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("administrative_area_level_3")) {
									edGoogleScrapper.setAdministrativeAreaLevel3(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("administrative_area_level_4")) {
									edGoogleScrapper.setAdministrativeAreaLevel4(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("sublocality_level_1")) {
									edGoogleScrapper.setSublocalityLevel1(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("sublocality_level_2")) {
									edGoogleScrapper.setSublocalityLevel2(jsonNode2.findValue("long_name").asText());
								}
								if (jsonNode3.asText().equalsIgnoreCase("locality")) {
									edGoogleScrapper.setLocality(jsonNode2.findValue("long_name").asText());									
								}
								if (jsonNode3.asText().equalsIgnoreCase("route")) {
									edGoogleScrapper.setRoute(jsonNode2.findValue("long_name").asText());
								}								
								
								
								
								
							}
						}else{
							continue;
						}						
					}
				}
				//
				edGoogleScrapperService.patch(edGoogleScrapper);
			}
		}
	}

}
