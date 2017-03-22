package com.ednomy.crm.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jboss.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.model.EdContentData;
import com.ednomy.crm.service.EdContentDataService;
import com.ednomy.crm.service.MiscService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;

@Service
public class MiscServiceImpl implements MiscService {

	Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	EdContentDataService edContentDataService;

	/**
	 * Scraping ContentData for Email, PhoneNumber and Social links
	 * 
	 * @param edContentData
	 */
	@Async
	public void updateRecords(EdContentData edContentData) {
		URL url = null;
		try {
			if (edContentData.getSt4().contains("http")) {
				url = new URL(edContentData.getSt4());
			} else {
				url = new URL("http://" + edContentData.getSt4());
			}
		} catch (MalformedURLException malformedURLException) {
			LOGGER.error(malformedURLException.getMessage(),
					malformedURLException);
			edContentData.setLn6(2L);
			edContentDataService.patch(edContentData);
			return;
		}

		Set<String> urls = new HashSet<String>(20);

		Set<String> emails = new HashSet<String>();
		Set<String> contacts = new HashSet<String>();
		Set<String> facebook = new HashSet<String>();
		Set<String> twitter = new HashSet<String>();
		Set<String> linkedin = new HashSet<String>();
		Set<String> youtube = new HashSet<String>();
		Set<String> googleplus = new HashSet<String>();

		Connection connection = Jsoup.connect(url.getProtocol() + "://" + url.getHost());
		connection.timeout(100000);
		Elements elements = null;
		Document doc = null;
		try {
			doc = connection.get();
			elements = doc.select("a[href]");
		} catch (Exception ioException) {
			LOGGER.error(ioException.getMessage());
			edContentData.setLn6(3L);
			edContentDataService.patch(edContentData);
		}

		for (Element element : elements) {
			String ele = element.attr("href").trim();
			if (ele.endsWith(".jpg") || ele.endsWith(".JPG")
					|| ele.endsWith(".PNG") || ele.endsWith(".png")
					|| ele.endsWith(".JPEG") || ele.endsWith(".jpeg")
					|| ele.equals("") || ele.equals("#")
					|| ele.endsWith(".mp3") || ele.contains("javascript")
					|| ele.contains(".pdf") || ele.contains("?")) {
				// DO Nothing
			} else {
				if (ele.contains("mailto:")) {
					emails.addAll(getEmails(ele));
				} else if (ele.contains("tel:")) {
					contacts.addAll(getContacts(ele));
				} else {
					try {
						URL tempURL = new URL(ele);
						if (tempURL.getHost().equalsIgnoreCase(url.getHost())
								&& (ele.contains("cont")
										|| ele.contains("tact")
										|| ele.contains("contact")
										|| ele.contains("about") || ele
											.contains("ab"))) {
							urls.add(ele);
						}
					} catch (MalformedURLException e) {
						StringBuilder builder = new StringBuilder(
								url.getProtocol() + "://" + url.getHost());
						builder.append("/");
						builder.append(ele);
						ele = builder.toString();
						if (ele.length() < 50
								&& (ele.contains("cont")
										|| ele.contains("tact")
										|| ele.contains("contact")
										|| ele.contains("about") || ele
											.contains("ab"))) {
							urls.add(ele);
						}
					} catch (Exception exception) {
						LOGGER.error(exception.getMessage());
					}
				}
			}
		}

		emails.addAll(getEmails(doc.html()));
		contacts.addAll(getContacts(doc.html()));
		facebook.addAll(getFacebook(doc));
		twitter.addAll(getTwitter(doc));
		linkedin.addAll(getLinkedIn(doc));
		youtube.addAll(getYouTube(doc));
		googleplus.addAll(getGooglePlus(doc));

		Iterator<String> iterator = urls.iterator();
		while (iterator.hasNext()) {
			String temp = (String) iterator.next();
			LOGGER.info(temp);
			connection = Jsoup.connect(temp);
			connection.timeout(100000);
			try {
				doc = connection.get();
				emails.addAll(getEmails(doc.html()));
				contacts.addAll(getContacts(doc.html()));
				facebook.addAll(getFacebook(doc));
				twitter.addAll(getTwitter(doc));
				linkedin.addAll(getLinkedIn(doc));
				youtube.addAll(getYouTube(doc));
				googleplus.addAll(getGooglePlus(doc));
			} catch (IOException ioException) {
				LOGGER.error(ioException.getMessage());
			}
		}

		String[] emailList = emails.toArray(new String[emails.size()]);
		String emailString = Arrays.deepToString(emailList).replace("\\[", ",").replace("\\]", ",");
		System.err.println(emailString);

		String[] contactsList = contacts.toArray(new String[contacts.size()]);
		String contactsString = Arrays.deepToString(contactsList);
		System.err.println(contactsString);

		String[] faceBookList = facebook.toArray(new String[facebook.size()]);
		String facebookString = Arrays.deepToString(faceBookList);
		System.err.println(facebookString);
		
		String[] twitterList = twitter.toArray(new String[twitter.size()]);
		String twitterString = Arrays.deepToString(twitterList);
		System.err.println(twitterString);
		
		String[] linkedinList = linkedin.toArray(new String[linkedin.size()]);
		String linkedinString = Arrays.deepToString(linkedinList);
		System.err.println(linkedinString);
		
		String[] youtubeList = youtube.toArray(new String[youtube.size()]);
		String youtubeString = Arrays.deepToString(youtubeList);
		System.err.println(youtubeString);
		
		String[] googleplusList = googleplus.toArray(new String[googleplus.size()]);
		String googleplusString = Arrays.deepToString(googleplusList);
		System.err.println(googleplusString);
		
		EdContentData tempEdContentData = new EdContentData();
		tempEdContentData.setId(edContentData.getId());
		tempEdContentData.setSt5(emailString) ;
		tempEdContentData.setLt14(contactsString);
		tempEdContentData.setLt2(facebookString);
		tempEdContentData.setLt1(twitterString);
		tempEdContentData.setLt3(linkedinString);
		tempEdContentData.setLt15(youtubeString);
		tempEdContentData.setLt16(googleplusString);
		tempEdContentData.setLn6(1L);

		edContentDataService.patch(tempEdContentData);

	}

	private Set<String> getEmails(String value) {
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		Pattern pattern = Pattern
				.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+");
		Matcher m = pattern.matcher(value);
		while (m.find()) {
			stringSet.add(m.group());
		}
		return stringSet;
	}

	private Set<String> getContacts(String value) {
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		Pattern pattern2 = Pattern.compile("(.\\d+[ _ -.]*){2,20}");
		Pattern pattern3 = Pattern
				.compile("(?=.{7,32}$)(\\(?\\+?[0-9]*\\)?)?[0-9_\\- \\(\\)]*((\\s?x\\s?|ext\\s?|extension\\s?)\\d{1,5}){0,1}");
		Matcher m2 = pattern2.matcher(value);
		while (m2.find()) {
			Matcher m3 = pattern3.matcher(m2.group().trim());
			while (m3.find() && m3.group().length() >= 7) {
				stringSet.add(m3.group().trim());
			}
		}
		return stringSet;
	}

	private Set<String> getFacebook(Document document) {
		String value = document.html();
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		String[] socialRegex = new String[] {"(http://|https://)(www.)?(facebook.com)/([a-zA-Z0-9/!$%&'()*+,./:;<=>?@\\^_`{|}~-]*)?"};
		for (String regex : socialRegex) {

			Pattern socialPattern = Pattern.compile(regex);
			Matcher socialMatcher = socialPattern.matcher(value);
			while (socialMatcher.find()) {
				stringSet.add(socialMatcher.group());
			}
		}
		return stringSet;
	}
	
	private Set<String> getTwitter(Document document) {
		String value = document.html();
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		String[] socialRegex = new String[] {"(http://|https://)(www.)?(twitter.com)/([a-zA-Z0-9/!$%&'()*+,./:;<=>?@\\^_`{|}~-]*)?"};
		for (String regex : socialRegex) {

			Pattern socialPattern = Pattern.compile(regex);
			Matcher socialMatcher = socialPattern.matcher(value);
			while (socialMatcher.find()) {
				stringSet.add(socialMatcher.group());
			}
		}
		return stringSet;
	}
	
	private Set<String> getLinkedIn(Document document) {
		String value = document.html();
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		String[] socialRegex = new String[] {"(http://|https://)(www.)?(linkedin.com)/([a-zA-Z0-9/!$%&'()*+,./:;<=>?@\\^_`{|}~-]*)?"};
		for (String regex : socialRegex) {

			Pattern socialPattern = Pattern.compile(regex);
			Matcher socialMatcher = socialPattern.matcher(value);
			while (socialMatcher.find()) {
				stringSet.add(socialMatcher.group());
			}
		}
		return stringSet;
	}
	
	private Set<String> getYouTube(Document document) {
		String value = document.html();
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		String[] socialRegex = new String[] {"(http://|https://)(www.)?(youtube.com)/([a-zA-Z0-9/!$%&'()*+,./:;<=>?@\\^_`{|}~-]*)?"};
		for (String regex : socialRegex) {

			Pattern socialPattern = Pattern.compile(regex);
			Matcher socialMatcher = socialPattern.matcher(value);
			while (socialMatcher.find()) {
				stringSet.add(socialMatcher.group());
			}
		}
		return stringSet;
	}
	
	private Set<String> getGooglePlus(Document document) {
		String value = document.html();
		Set<String> stringSet = null;
		stringSet = new HashSet<String>();
		String[] socialRegex = new String[] {"(http://|https://)(www.)?(plus.google.com)?/([a-zA-Z0-9/!$%&'()*+,./:;<=>?@\\^_`{|}~-]*)?" };
		for (String regex : socialRegex) {

			Pattern socialPattern = Pattern.compile(regex);
			Matcher socialMatcher = socialPattern.matcher(value);
			while (socialMatcher.find()) {
				stringSet.add(socialMatcher.group());
			}
		}
		return stringSet;
	}

	@Override
	public void updatePhoneRecord() {
		
		
		String query = "{\"query\":{\"edContentData\":{\"filter\":{\"type\":{\"equals\":\"company\"},\"st10\":{\"equals\":\"GOOGLE\"},\"ln6\":{\"notequals\":0},\"lt13\":{\"notequals\":\"\"}}}},\"page\":{\"start\":0,\"max\":1}}";
		
		HashMap<String, String> queryMap = new HashMap<String, String>();
		queryMap.put(EdQueryConstants.JSON_QUERY_PARAMETER, query);
 		List<EdContentData> contentDatas = edContentDataService.list(queryMap);
 		
 		if (!contentDatas.isEmpty() && contentDatas.size() == 1) {
			//System.out.println(contentDatas.get(0).getId());
 			String socialLinks = contentDatas.get(0).getLt15().replaceAll("\\[", "").replaceAll("\\]", "");
 			
 			
 			String value = contentDatas.get(0).getLt14();
			List<String> arrayMobile = new ArrayList<String>();
			List<String> arrayLandline = new ArrayList<String>();
			//filtering contacts for separating land-line and mobile numbers
			filterContacts(value, arrayMobile, arrayLandline);
			
			String[] landlines = StringUtils.removeDuplicateStrings(StringUtils.toStringArray(arrayLandline));
			String[] mobiles = StringUtils.removeDuplicateStrings(StringUtils.toStringArray(arrayMobile));
			String [] social =  StringUtils.removeDuplicateStrings(StringUtils.commaDelimitedListToStringArray(socialLinks));
			StringUtils.arrayToCommaDelimitedString(social);			
		}	
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

}
