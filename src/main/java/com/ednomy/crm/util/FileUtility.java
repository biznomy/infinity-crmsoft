package com.ednomy.crm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FileUtility {
	
	public static final String TYPE = "type";
	public static final String DATA = "data";

	@Autowired
	ApplicationProperties applicationProperties;

	public File convertMultipartToFile(MultipartFile multipartFile)
			throws IOException {
		File convFile = new File(applicationProperties.getTempDir() + "/"
				+ multipartFile.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(multipartFile.getBytes());
		fos.close();
		return convFile;
	}

	public File multipartToFile(MultipartFile multipart)
			throws IllegalStateException, IOException {
		File tempDir = new File(applicationProperties.getTempDir());
		if (!tempDir.exists()) {
			tempDir.mkdir();
		}
		File convFile = FileUtils.getFile(tempDir,
				multipart.getOriginalFilename());
		if (!convFile.exists()) {
			convFile.createNewFile();
		}
		multipart.transferTo(convFile);
		return convFile;
	}

	public MultipartFile fileToMultipart(File file) throws IOException {
		FileInputStream input = new FileInputStream(file);
		MultipartFile multipartFile = new MockMultipartFile("file",
				file.getName(), "text/plain", IOUtils.toByteArray(input));
		return multipartFile;

	}

	public String saveFile(String dirName, MultipartFile multipartFile) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		String fileName = multipartFile.getOriginalFilename();
		fileName = System.currentTimeMillis() + "";
		File newFile = new File(dirName + fileName);

		try {
			inputStream = multipartFile.getInputStream();

			if (newFile.getParentFile().mkdirs()) {
				newFile.createNewFile();
			}
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (Exception exception) {
		}

		finally {
			try {
				inputStream.close();
				outputStream.close();
			} catch (Exception exception) {
			}

		}

		return newFile.getAbsolutePath();
	}

	public String getStringFromInputStream(InputStream is) throws IOException {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();

		String line;

		br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		if (br != null) {
			br.close();
		}
		return sb.toString();

	}

	public MultipartFile base64ToMultipart(String fileName, String base64Content) {
		Map<String, String> map = base64ImageTypeIdentifier(base64Content);
		byte[] fileData = Base64Utils.decodeFromString(map.get(DATA));
		MultipartFile multipartFile = new MockMultipartFile("file", fileName,
				"text/plain", fileData);
		return multipartFile;
	}

	public File base64ToFile(String base64Content) throws IOException {
		return base64ToFile(null, null, base64Content);
	}

	public File base64ToFile(String fileName, String base64Content)
			throws IOException {
		return base64ToFile(null, fileName, base64Content);
	}

	public File base64ToFile(String filePath, String fileName,
			String base64Content) throws IOException {
		if (filePath == null || filePath.trim().isEmpty()) {
			filePath = applicationProperties.getTempDir();
		}
		if (fileName == null || fileName.trim().isEmpty()) {
			fileName = String.valueOf(System.currentTimeMillis());
		}
		File file = new File(filePath + fileName);
		ObjectMapper mapper = new ObjectMapper();
		byte[] encoded = mapper.convertValue(base64Content, byte[].class);
		// String decoded = mapper.convertValue(encoded, String.class);
		FileUtils.writeByteArrayToFile(file, encoded);
		return file;
	}

	public String fileToBase64(File file) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		byte[] encoded = FileUtils.readFileToByteArray(file);
		// String decoded = Base64Utils.encodeToString(encoded);
		String decoded = mapper.convertValue(encoded, String.class);
		return decoded;
	}

	public String fileToString(File file) throws IOException {
		return FileUtils.readFileToString(file);
	}
	
	public Map<String, String> base64ImageTypeIdentifier(String base64Data){
		Map<String, String> map = null;
		if (base64Data.contains("data:image/jpeg;base64,")) {
			map = new HashMap<String, String>(); 
			map.put(TYPE, ".jpg");
			map.put(DATA, base64Data.replaceAll("data:image/jpeg;base64,", ""));
			return  map;
		}
		if (base64Data.contains("data:image/jpg;base64,")) {
			map = new HashMap<String, String>();
			map.put(TYPE, ".jpg");
			map.put(DATA, base64Data.replaceAll("data:image/jpg;base64,", ""));
			return  map;
		}
		if (base64Data.startsWith("/9j/")) {
			map = new HashMap<String, String>();
			map.put(TYPE, ".jpg");
			map.put(DATA, base64Data);
			return  map;
		}
		if (base64Data.contains("data:image/png;base64,")) {
			map = new HashMap<String, String>();
			map.put(TYPE, ".png");
			map.put(DATA, base64Data.replaceAll("data:image/png;base64,", ""));
			return  map;
		}
		if (base64Data.startsWith("iV")) {
			map = new HashMap<String, String>();
			map.put(TYPE, ".png");
			map.put(DATA, base64Data);
			return  map;
		}
		return map;
	}
	

	public HashMap<String, String> getQueryMapFromFile(String name) {
		try {
			File currFile = new File(getClass().getClassLoader()
					.getResource("queries//" + name).getFile());
			String input = FileUtils.readFileToString(currFile);
			System.out.println(input);
			HashMap<String, String> queryMap = new HashMap<String, String>();
			queryMap.put("jsq", input);
			return queryMap;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public String readFileToString(String resourcePath) {
		try {
			File currFile = new File(getClass().getClassLoader()
					.getResource(resourcePath).getFile());
			String input = FileUtils.readFileToString(currFile);
			return input;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
