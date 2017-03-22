/**
 *
 */
package com.ednomy.crm.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.MiscService;

@Controller
@RequestMapping(value="armor")
public class EdArmorController {

	@Autowired
	EdContentDataService edContentDataService;
	
	@Autowired
	MiscService miscService; 
	
	Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	
	//@Scheduled(cron = "*/30 * * * * *" )
	public void upGradeArmor(){
		
		Map<String, String> queryMap = new HashMap<String, String>();
		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"ln6\":{\"isnull\":\"\"}}}},\"page\":{\"start\":0,\"max\":5}}";
//		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"ln6\":{\"isnull\":\"\"},\"st1\":{\"like\":\"delhi\"},\"st10\":{\"equals\":\"GOOGLE\"}}}},\"page\":{\"start\":1,\"max\":5}}";
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		List<EdContentData> edContentDatas = edContentDataService.list(queryMap);
		
		Iterator<EdContentData> iterator = edContentDatas.iterator();
		while (iterator.hasNext()) {
			EdContentData edContentData = (EdContentData) iterator.next();
			miscService.updateRecords(edContentData);
		}		
	}	
	
	
	//@Scheduled(cron = "* * * * * *" )
	public void upDate(){
		
		Map<String, String> queryMap = new HashMap<String, String>();
		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"st10\":{\"equals\":\"GOOGLE\"},\"st4\":[{\"notequals\":\"\"},{\"isnotnull\":\"\"}],\"ln8\":{\"equals\":\"0\"}}}},\"page\":{\"start\":0,\"max\":100}}";
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		List<EdContentData> edContentDatas = edContentDataService.list(queryMap);
		
		Iterator<EdContentData> it = edContentDatas.iterator();
		while (it.hasNext()) {
			EdContentData edContentData = (EdContentData) it.next();
			String urlValue = edContentData.getSt4();
			if (!urlValue.contains("http")) {
				urlValue = "http://"+urlValue;
			}
			try {
				URL url = new URL(urlValue);
				String hostName = url.getHost();
				
				query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"st4\":{\"like\":\""+hostName+"\"},\"ln8\":{\"equals\":\"0\"}}}},\"page\":{\"start\":0,\"max\":500}}";
				queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
				List<EdContentData> edContentDatas2 = edContentDataService.list(queryMap);
				if (edContentDatas2.size() >  4) {
					Iterator<EdContentData> iterator = edContentDatas2.iterator();
					while (iterator.hasNext()) {
						EdContentData edCont = (EdContentData) iterator.next();
						edCont.setLn8(2L);
						edContentDataService.patch(edCont);
					}
				}else{
					Iterator<EdContentData> iterator = edContentDatas2.iterator();
					while (iterator.hasNext()) { 
						EdContentData edCont = (EdContentData) iterator.next();
						edCont.setLn8(1L);
						edContentDataService.patch(edCont);
					}
				}				
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}			
		}
				
	}	
	
}