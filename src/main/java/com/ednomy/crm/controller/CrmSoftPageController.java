/**
 *
 */
package com.ednomy.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.commons.query.EdQueryBuilder;
import com.ednomy.crm.commons.query.Filter;
import com.ednomy.crm.exception.EdnomyException;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.service.EdContentDataService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Controller
@RequestMapping(value = "/")
public class CrmSoftPageController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EdContentDataService edContentDataService; 
	
	@RequestMapping(value = {"lumino", "lumino/", "lumino/index", "lumino/index.html", "lumino/index.jsp", "/index"})
	public String lumino(){
		return "lumino/index";
	}
	
	@RequestMapping(value = {"charts"})
	public String charts(){
		return "lumino/charts";
	}
	
	@RequestMapping(value = {"forms"})
	public String forms(){
		return "lumino/forms";
	}
	
	@RequestMapping(value = {"icons"})
	public String icons(){
		return "lumino/icons";
	}
	
	@RequestMapping(value = {"login"})
	public String login(){
		return "lumino/login";
	}
	
	@RequestMapping(value = {"panels"})
	public String panels(){
		return "lumino/panels";
	}
	
	@RequestMapping(value = {"person"})
	public ModelAndView person(ModelAndView modelAndView, HttpServletRequest request){
		modelAndView.setViewName("lumino/person");
		return modelAndView;
	}

	@RequestMapping(value = {"person/{companyId}"})
	public ModelAndView personAlt(ModelAndView modelAndView, HttpServletRequest request, @PathVariable Long companyId){
		modelAndView.addObject("companyId", String.valueOf(companyId));
		modelAndView.setViewName("lumino/person");
		return modelAndView;
	}
	
	@RequestMapping(value = {"message"})
	public ModelAndView message(ModelAndView modelAndView, HttpServletRequest request){
		modelAndView.setViewName("lumino/message");
		return modelAndView;
	}

	@RequestMapping(value = {"message/{companyId}"})
	public ModelAndView messageAlt(ModelAndView modelAndView, HttpServletRequest request, @PathVariable Long companyId){
		modelAndView.addObject("companyId", String.valueOf(companyId));
		modelAndView.setViewName("lumino/message");
		return modelAndView;
	}
	
	@RequestMapping(value = {"companies"})
	public ModelAndView tables(ModelAndView modelAndView, HttpServletRequest request){
		long page = 1; int max = 10;
		
		String pageParam = request.getParameter("page");
		String maxParam = request.getParameter("max");
 		if (pageParam != null && maxParam != null) {
 			try{
 				page = Long.parseLong(pageParam);
 				max = Integer.parseInt(maxParam);
 			}catch(NumberFormatException e){
 				page = 1;max = 10;
 			}
		}
		
		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"}}}},\"page\":{\"start\":"+page+",\"max\":"+max+"}}";
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		//number of records
 		long number = edContentDataService.count(queryMap);
 		modelAndView.addObject("contentDatas", contentDatas);
 		modelAndView.addObject("totalCount", number);
 		//number of pages
 		long numberOfPages = (number/max);
 		if (number%max != 0 ) numberOfPages ++;
 		modelAndView.addObject("totalPages", numberOfPages);
 		modelAndView.addObject("currentPage", page); 		
 		modelAndView.setViewName("lumino/tables");
		return modelAndView;
	}
	
	
	@RequestMapping(value = {"campaignview/{id}"})
	public ModelAndView campaignview(@PathVariable Long id, ModelAndView modelAndView){
		try{
			EdContentData edContentData = edContentDataService.get(id);
			modelAndView.addObject("edContentData", edContentData);
			
			if (edContentData != null && edContentData.getId() != null) {
				
				EdQueryBuilder builder = new EdQueryBuilder();
				Filter filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "message");
				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));
				
				
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				
				List<EdContentData> messageDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("messageDatas", messageDatas);
				
			}
			
		}catch(EdnomyException ednomyException){
			modelAndView.addObject("noResultMessage", ednomyException.getMessage());
		}
		modelAndView.setViewName("lumino/campaignview");
		return modelAndView;
	}
	
	@RequestMapping(value = {"campaignedit/{id}"})
	public ModelAndView campaignedit(@PathVariable Long id, ModelAndView modelAndView){
		try{
			EdContentData edContentData = edContentDataService.get(id);
			modelAndView.addObject("edContentData", edContentData);
			
			if (edContentData != null && edContentData.getId() != null) {
				
				EdQueryBuilder builder = new EdQueryBuilder();
				Filter filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "message");
				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));
				
				
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				
				List<EdContentData> messageDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("messageDatas", messageDatas);
				
			}
			
		}catch(EdnomyException ednomyException){
			modelAndView.addObject("noResultMessage", ednomyException.getMessage());
		}
		modelAndView.setViewName("lumino/campaignedit");
		return modelAndView;
	}
	
	
	@RequestMapping(value = {"campaign"})
	public ModelAndView campaign(ModelAndView modelAndView, HttpServletRequest request){
		int page = 1; int max = 10;
		
		String pageParam = request.getParameter("page");
		String maxParam = request.getParameter("max");
		
		if (pageParam != null && maxParam != null) {
 			try{
 				page = Integer.parseInt(pageParam);
 				max = Integer.parseInt(maxParam);
 			}catch(NumberFormatException e){
 				page = 1;max = 10;
 			}
		}
		
		EdQueryBuilder builder = new EdQueryBuilder();
		Filter filter = new Filter();
		builder.setPageQuery(page, max);
		builder.setSort("createdOn", "asc");
		filter.addFilterMulti(builder, "edContentData", "type", "equals", "campaign");
		filter.addFilterEmpty(builder, "edContentData", "title", "like", request.getParameter("title"));
		filter.addFilterEmpty(builder, "edContentData", "st3", "equals", request.getParameter("medium"));
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		//number of records
 		long number = edContentDataService.count(queryMap);
 		modelAndView.addObject("contentDatas", contentDatas);
 		modelAndView.addObject("totalCount", number);
 		//number of pages
 		long numberOfPages = (number/max);
 		if (number%max != 0 ) numberOfPages ++;
 		modelAndView.addObject("totalPages", numberOfPages);
 		modelAndView.addObject("currentPage", page); 		
 		modelAndView.setViewName("lumino/campaign");
		return modelAndView;
	}
	
	@RequestMapping(value = {"advancequery"})
	public ModelAndView advanceQuery(ModelAndView modelAndView, HttpServletRequest request){
		int page = 1, max = 10;
		
		String pageParam = request.getParameter("page");
		String maxParam = request.getParameter("max");
 		if (pageParam != null && maxParam != null) {
 			try{
 				page = Integer.parseInt(pageParam);
 				max = Integer.parseInt(maxParam);
 			}catch(NumberFormatException e){
 				page = 1;max = 10;
 			}
		}
		
 		EdQueryBuilder builder = new EdQueryBuilder();
		Filter filter = new Filter();
		builder.setPageQuery(page, max);
		filter.addFilterEmpty(builder, "edContentData", "title", "like", request.getParameter("titleS"));
		filter.addFilterEmpty(builder, "edContentData", "st4", "like", request.getParameter("webS"));
		filter.addFilterEmpty(builder, "edContentData", "st5", "like", request.getParameter("emailS"));
		filter.addFilterEmpty(builder, "edContentData", "detail", "like", request.getParameter("add1S"));
		filter.addFilterEmpty(builder, "edContentData", "lt5", "like", request.getParameter("add2S"));
		filter.addFilterEmpty(builder, "edContentData", "st3", "like", request.getParameter("countS"));
		filter.addFilterEmpty(builder, "edContentData", "st1", "like", request.getParameter("cityS"));
		filter.addFilterEmpty(builder, "edContentData", "st2", "like", request.getParameter("stateS"));
		filter.addFilterEmpty(builder, "edContentData", "st10", "like", request.getParameter("sourcS"));
		filter.addFilterEmpty(builder, "edContentData", "category1", "like", request.getParameter("cat1S"));
		filter.addFilterEmpty(builder, "edContentData", "category2", "like", request.getParameter("cat2S"));
		filter.addFilterEmpty(builder, "edContentData", "category3", "like", request.getParameter("cat3S"));
		filter.addFilterEmpty(builder, "edContentData", "st6", "like", request.getParameter("landS"));
		filter.addFilterEmpty(builder, "edContentData", "st7", "like", request.getParameter("mobiS"));
		filter.addFilterEmpty(builder, "edContentData", "lt1", "like", request.getParameter("twittS"));
		filter.addFilterEmpty(builder, "edContentData", "lt2", "like", request.getParameter("faceS"));
		filter.addFilterEmpty(builder, "edContentData", "lt3", "like", request.getParameter("linkeS"));
		filter.addFilterEmpty(builder, "edContentData", "lt4", "like", request.getParameter("whatS"));
		filter.addFilterEmpty(builder, "edContentData", "lt8", "like", request.getParameter("rema1S"));
		filter.addFilterEmpty(builder, "edContentData", "lt9", "like", request.getParameter("rema2S"));
		filter.addFilterEmpty(builder, "edContentData", "lt10", "like", request.getParameter("rema3S"));
		filter.addFilterEmpty(builder, "edContentData", "st10", "like", request.getParameter("sourceS"));
		filter.addFilterEmpty(builder, "edContentData", "ln6", "equals", request.getParameter("ln6S"));
		filter.addFilterEmpty(builder, "edContentData", "status1", "equals", request.getParameter("status1S"));
		
		
		if (request.getParameter("twitterStateS") != null) {
			filter.addFilterMulti(builder, "edContentData", "ln1", "equals", request.getParameter("twitterStateS"));
		}
		if (request.getParameter("facebookStateS") != null) {
			filter.addFilterMulti(builder, "edContentData", "ln2", "equals", request.getParameter("facebookStateS"));
		}
		if (request.getParameter("linkedInStateS") != null) {
			filter.addFilterMulti(builder, "edContentData", "ln3", "equals", request.getParameter("linkedInStateS"));
		}
		if (request.getParameter("whatsAppStateS") != null) {
			filter.addFilterMulti(builder, "edContentData", "ln4", "equals", request.getParameter("whatsAppStateS"));
		}
		
		if (request.getParameter("lanLineBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st6", "isnull", "");
		}
		if (request.getParameter("mobileBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st7", "isnull", "");
		}
		if (request.getParameter("emailBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st5", "isnull", "");
		}
		
		if (request.getParameter("lanLineNotBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st6", "notequals", request.getParameter("lanLineNotBlank"));
		}
		if (request.getParameter("mobileNotBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st7", "notequals", request.getParameter("mobileNotBlank"));
		}
		if (request.getParameter("emailNotBlank") != null) {
			filter.addFilterMulti(builder, "edContentData", "st5", "notequals", request.getParameter("emailNotBlank"));
		}
 		
//		((ObjectNode)builder.getQuery().findValue("filter")).put("op", "or");
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		//number of records
 		long number = edContentDataService.count(queryMap);
 		modelAndView.addObject("contentDatas", contentDatas);
 		modelAndView.addObject("totalCount", number);
 		//number of pages
 		long numberOfPages = (number/max);
 		if (number%max != 0 ) numberOfPages ++;
 		modelAndView.addObject("totalPages", numberOfPages);
 		modelAndView.addObject("currentPage", page); 		
 		modelAndView.setViewName("lumino/tables");
		return modelAndView;
	}
	
	@RequestMapping(value = {"simplequery"})
	public ModelAndView simpleQuery(ModelAndView modelAndView, HttpServletRequest request){
		int page = 1, max = 10;
		
		String pageParam = request.getParameter("page");
		String maxParam = request.getParameter("max");
 		if (pageParam != null && maxParam != null) {
 			try{
 				page = Integer.parseInt(pageParam);
 				max = Integer.parseInt(maxParam);
 			}catch(NumberFormatException e){
 				page = 1;max = 10;
 			}
		}
		
 		EdQueryBuilder builder = new EdQueryBuilder();
		Filter filter = new Filter();
		builder.setPageQuery(page, max);
		filter.addFilterMulti(builder, "edContentData", "title", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st4", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st5", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "detail", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt5", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st3", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st1", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st2", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st10", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "category1", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "category2", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "category3", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st6", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st7", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt1", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt2", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt3", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt4", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt8", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt9", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "lt10", "like", request.getParameter("search"));
		filter.addFilterMulti(builder, "edContentData", "st10", "like", request.getParameter("search"));
//		filter.addFilterMulti(builder, "edContentData", "ln6", "equals", request.getParameter("search"));
//		filter.addFilterMulti(builder, "edContentData", "status1", "equals", request.getParameter("search"));
		
		((ObjectNode)builder.getQuery().findValue("filter")).put("op", "or");
 		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		//number of records
 		long number = edContentDataService.count(queryMap);
 		modelAndView.addObject("contentDatas", contentDatas);
 		modelAndView.addObject("totalCount", number);
 		//number of pages
 		long numberOfPages = (number/max);
 		if (number%max != 0 ) numberOfPages ++;
 		modelAndView.addObject("totalPages", numberOfPages);
 		modelAndView.addObject("currentPage", page); 		
 		modelAndView.setViewName("lumino/tables");
		return modelAndView;
	}
	
	@RequestMapping(value = {"widgets"})
	public String widgets(){
		return "lumino/widgets";
	}
	
	@RequestMapping(value = {"view/{id}"})
	public ModelAndView view(@PathVariable Long id, ModelAndView modelAndView){
		try{
			EdContentData edContentData = edContentDataService.get(id);
			modelAndView.addObject("edContentData", edContentData);
			
			if (edContentData != null && edContentData.getId() != null) {
				
				EdQueryBuilder builder = new EdQueryBuilder();
				Filter filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "person");
				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));//comapany id
				
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				List<EdContentData> personDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("personDatas", personDatas);
				
				builder = null; filter = null;
				builder = new EdQueryBuilder();
				filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "message");
//				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));//campaign Id
				filter.addFilterMulti(builder, "edContentData", "st1", "equals", String.valueOf(edContentData.getId()));//company Id
				
				queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				List<EdContentData> messageDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("messageDatas", messageDatas);
				
			}
			
			
		}catch(EdnomyException ednomyException){
			modelAndView.addObject("noResultMessage", ednomyException.getMessage());
		}
		modelAndView.setViewName("lumino/view");
		return modelAndView;
	}
	
	@RequestMapping(value = {"edit/{id}"})
	public ModelAndView edit(@PathVariable Long id, ModelAndView modelAndView){
		try{
			EdContentData edContentData = edContentDataService.get(id);
			modelAndView.addObject("edContentData", edContentData);
			
			
			if (edContentData != null && edContentData.getId() != null) {
				
				EdQueryBuilder builder = new EdQueryBuilder();
				Filter filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "person");
				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));//comapany id
				
				Map<String, String> queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				List<EdContentData> personDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("personDatas", personDatas);
				
				builder = null; filter = null;
				builder = new EdQueryBuilder();
				filter = new Filter();
				filter.addFilterMulti(builder, "edContentData", "type", "equals", "message");
//				filter.addFilterMulti(builder, "edContentData", "ref1", "equals", String.valueOf(edContentData.getId()));//campaign Id
				filter.addFilterMulti(builder, "edContentData", "st1", "equals", String.valueOf(edContentData.getId()));//company Id
				
				queryMap = new HashMap<String, String>();
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, builder.getQuery().toString());
				List<EdContentData> messageDatas = edContentDataService.list(queryMap);
				modelAndView.addObject("messageDatas", messageDatas);
				
			}			
		}catch(EdnomyException ednomyException){
			modelAndView.addObject("noResultMessage", ednomyException.getMessage());
		}
		modelAndView.setViewName("lumino/edit");
		return modelAndView;
	}
	
	
	
	
	
	@RequestMapping(value = {"test_1"})
	public ModelAndView test(HttpServletRequest request, ModelAndView modelAndView){
		Long next = Long.parseLong(request.getParameter("next"));
		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"ln6\":{\"equals\":1},\"ln7\":{\"isnull\":\"\"},\"st10\":{\"equals\":\"GOOGLE\"},\"st1\":{\"like\":\"delhi\"}}}},\"page\":{\"start\":"+next+",\"max\":1}}";
//		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"ln6\":{\"equals\":3},\"st1\":{\"like\":\"delhi\"},\"st10\":{\"equals\":\"GOOGLE\"}}}},\"page\":{\"start\":"+next+",\"max\":1}}";
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		
 		if (!contentDatas.isEmpty() && contentDatas.size() == 1) {
 			modelAndView.addObject("contentData", contentDatas.get(0));
			//System.out.println(contentDatas.get(0).getId());

 			try{
 				String socialLinks = contentDatas.get(0).getLt15().replaceAll("\\[", "").replaceAll("\\]", "");
 	 			String [] social = StringUtils.commaDelimitedListToStringArray(socialLinks);
 	 			social = StringUtils.removeDuplicateStrings(social);
 	 			social = StringUtils.sortStringArray(social);
 	 			contentDatas.get(0).setLt15(StringUtils.arrayToCommaDelimitedString(social));
 				modelAndView.addObject("sociallinks", social);
 			}catch(Exception e){}
 			
 			
			
			String value = contentDatas.get(0).getLt14();
			List<String> arrayMobile = new ArrayList<String>();
			List<String> arrayLandline = new ArrayList<String>();
			//filtering contacts for separating land-line and mobile numbers
			try{
				filterContacts(value, arrayMobile, arrayLandline);			
				modelAndView.addObject("landLine", StringUtils.removeDuplicateStrings(StringUtils.toStringArray(arrayLandline)));
				modelAndView.addObject("mobile", StringUtils.removeDuplicateStrings(StringUtils.toStringArray(arrayMobile)));	
			}catch(Exception exception){}
			

		}		
 		modelAndView.setViewName("lumino/test");
		return modelAndView;
	}

	/**
	 * filtering contacts for separating Land-line and mobile numbers
	 * @param value
	 * @param arrayMobile
	 * @param arrayLandline
	 */
	private void filterContacts(String value, List<String> arrayMobile, List<String> arrayLandline) {
		value = value.replaceAll("\\[", "").replaceAll("\\]", "");
		String[] numbers = StringUtils.commaDelimitedListToStringArray(value);
		for (String number : numbers) {
			PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
			try {
				PhoneNumber phoneNumber = phoneNumberUtil.parse(number, "IN");
				if (phoneNumberUtil.isValidNumberForRegion(phoneNumber, "IN")) {
					String myNumber = phoneNumberUtil.formatNumberForMobileDialing(phoneNumber, "IN", true);
					if (myNumber.split("\\s").length == 2) {
						arrayMobile.add(myNumber);
					}else{
						arrayLandline.add(myNumber);
					}						
				}
			} catch (NumberParseException e) {}
		}
	}
	
	@RequestMapping(value = {"update"})
	public void update(HttpServletRequest request, EdContentData edContentData){
		System.out.println(edContentData);
		edContentData.setLn7(1L);
		edContentDataService.patch(edContentData);
	}
	
	@RequestMapping(value = {"adminmail"})
	public String adminmail(){
		return "lumino/adminmail";
	}
	
	@RequestMapping(value = {"edmessagemanager"})
	public String edmessagemanager(){
		return "lumino/edmessagemanagers";
	}
	@RequestMapping(value = {"company/new"})
	public String edCompanyAdd(){
		return "lumino/companynew";
	}
	
}
