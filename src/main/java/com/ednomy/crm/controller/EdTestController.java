/**
 *
 */
package com.ednomy.crm.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ednomy.crm.commons.constants.EdErrorCode;
import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.EdPointService;
import com.ednomy.crm.util.EdResponse;

@Controller
@RequestMapping(value = "/test")
public class EdTestController {

	@Autowired
	EdPointService edPointService;
	
	@Autowired
	EdContentDataService edContentDataService;
	
	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(EdTestController.class);

	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<String> get(@RequestParam Map<String, String> requestParams) {
		final EdResponse<String> response = new EdResponse<String>();
		
		Iterator<Entry<String, String>> iterator = requestParams.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
			LOGGER.info("Key : "+entry.getKey());
			LOGGER.info("Value : "+entry.getValue());			
		}		
		
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public EdResponse<String> post(@RequestParam Map<String, String> requestParams) {
		final EdResponse<String> response = new EdResponse<String>();
		
		String emails = requestParams.get("emails");
		emails = emails.replaceAll("\\[", "").replaceAll("\\]","").replaceAll("\'", "");
		String[] result  = StringUtils.commaDelimitedListToStringArray(emails);
		String solution = ""; if (result.length != 0) solution = ",";
		for (String email : result) {
			solution += email + ",";
		}
		LOGGER.info(solution);
		LOGGER.info(requestParams.get("dataId"));
		
		EdContentData edContentData = new EdContentData();
		edContentData.setId(Long.parseLong(requestParams.get("dataId")));
		edContentData.setSt5(solution);
		edContentData.setLn6(1L);
		edContentDataService.patch(edContentData);
		
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}
	
	@RequestMapping(value = "/email", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public EdResponse<String> updateEmails() throws IOException, InterruptedException {
		final EdResponse<String> response = new EdResponse<String>();
		
		String query = "{\"query\":{\"edContentData\":{\"endUser\":{\"filter\":{\"id\":{\"equals\":123456}}},\"edContentCollection\":{\"filter\":{\"id\":{\"equals\":123456}}},\"filter\":{\"st4\":[{\"notequals\":\"\"},{\"isnotnull\":\"\"}],\"ln6\":[{\"op\":\"or\",\"equals\":\"0\"},{\"op\":\"or\",\"isnull\":\"\"}]}}}}";
		Map<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
		
		List<EdContentData> contentDatas = edContentDataService.list(queryMap);  
		for (EdContentData edContentData : contentDatas) {
			try{
				URL url = new URL(edContentData.getSt4().trim());
				String domainName = url.getHost();
				executeCMDForWindows(domainName, edContentData.getId());
			}catch(Exception exception){
				exception.printStackTrace();
			}			
		}
		
		response.setCode(EdErrorCode.SUCCESS_CODE);
		response.setMessage(EdErrorCode.SUCCESS_MSG);
		return response;
	}


	private void executeCMDForWindows(String domainName, Long dataId) throws IOException,
			InterruptedException {
		
		ProcessBuilder pb = null;
		
		if (System.getProperty("os.name").startsWith("Win")){
			pb = new ProcessBuilder("cmd");
			Map<String, String> env = pb.environment();
			env.put("website", domainName);
			env.put("dataId", String.valueOf(dataId));
			
			pb.environment().putAll(env);
			pb.inheritIO();
			
			File outputFile = new File("C:\\tempDir\\python_script\\Log.txt");
			File errorFile = new File("C:\\tempDir\\python_script\\ErrLog.txt");

			pb.command("C:\\tempDir\\python_script\\scrapper.bat");		
			pb.redirectOutput(outputFile);
			pb.redirectError(errorFile);			
		}else{
			pb = new ProcessBuilder("python", "/home/ubuntu/tempDir/python_script/extract_emails.py", domainName, "--dataId", String.valueOf(dataId), "-v");
		}
		
		Process process = pb.start();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println(line);
		}
		
		process.waitFor();
		System.err.println("Close for : " +domainName);
		
	}
	
	
//	@Scheduled(cron = "1 */3 * * * *")//second, minute, hour, day of month, month, day(s) of week
	public void updateEmailJob()throws IOException, InterruptedException {
		updateEmails() ;
	}

}
