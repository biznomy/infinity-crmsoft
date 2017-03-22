package com.ednomy.crm.commons.constants;

import java.util.Locale;

public class EdnomyConstants {
	
	public static final int FALSE_INT = 0;
	public static final int TRUE_INT = 1;
	
	public static final int DEFAULT_INT = 0;
	public static final int UPDATE_INT = 1;
	
	public static final int TYPE_EMAIL_INT = 0;
	public static final int TYPE_SMS_INT = 1;
	
	public static final String EDNOMY_DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String EDNOMY_SERVER_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final Locale EDNOMY_LOCALE = Locale.ENGLISH;

	public static final int SYS_NOTI_EMAIL = 0;
	public static final int SYS_NOTI_SMS = 1;
	public static final int SYS_NOTI_PUSH = 2;

	public static final String NOTI_TO_ED_CONTENT_DATA = "edContentData";
	public static final String NOTI_TO_CLIENT = "clientUser";
	public static final String NOTI_TO_ENDUSER = "endUser";

	public static final int SYS_NOTI_TO_EDNOMY = 0;
	public static final int SYS_NOTI_TO_CLIENT = 1;
	public static final int SYS_NOTI_TO_ENDUSER = 2;

	public static final int SYS_NOTI_TO_ACTIVE = 0;
	public static final int SYS_NOTI_TO_INACTIVE = 1;

	public static final String NOTI_CALL_SIGNUP_VERIFIED = "signup_verified";
	public static final String NOTI_CALL_QUERY = "query";
	public static final String NOTI_CALL_FEEDBACK = "feedback";
	public static final String NOTI_CALL_TEST_SUB = "test_submit";
	public static final String NOTI_CALL_NEW_REQUEST = "new_request";

	public static final String NOTIFICATION_EMAIL_NODE = "email";
	public static final String NOTIFICATION_SMS_NODE = "sms";

	public static final String NOTIFICATION_ED_NODE = "ednomy";
	public static final String NOTIFICATION_CLIENT_NODE = "client";
	public static final String NOTIFICATION_ENDUSER_NODE = "enduser";

	public static final String END_USER = "End User";
	public static final String CLIENT_USER = "Client User";

	public static final String TYPE_EMAIL = "email";
	public static final String TYPE_P_N = "push";
	public static final String TYPE_SMS = "sms";

	public static final String NET_GCM = "gcm";
	public static final String NET_APN = "apn";
	public static final String NET_MSG = "sms";
	public static final String NET_MAIL = "email";

	public static final String SENT_TRUE = "sent";
	public static final String SENT_FALSE = "unsent";

	public static final Long STATUS_VERIFIED = 1L;
	public static final Long STATUS_UNVERIFIED = 0L;

	public static final Long STATUS_ACTIVE = 1L;
	public static final Long STATUS_INACTIVE = 0L;

	public static final String READ_TRUE = "read";
	public static final String READ_FALSE = "unread";

	public static final String DEVICE_ANDROID = "android";
	public static final String DEVICE_IOS = "ios";

	public static final int USER_NOTIFICATION_TRUE = 0;
	public static final int USER_NOTIFICATION_FALSE = 1;

	public static final String APP_DATA_TYPE_CHAPTER = "chapter";
	public static final String APP_DATA_TYPE_TEST = "test";

	public static final Double DEFAULT_APP_DATA_TYPE_TEST_TIME = 10d;
	public static final Double DEFAULT_APP_DATA_TYPE_TEST_QUESTION = 5d;

	public static final String APP_DATA_TYPE_MCQ = "chapter-mcq";
	public static final String APP_DATA_TYPE_PRACTICE = "practice";
	public static final String APP_DATA_TYPE_DETAIL = "chapter-detail";

	public static final String AUTH_TOKEN = "authToken";

	public static final Long APP_DATA_TEST_ALL_0 = 0L;
	public static final Long APP_DATA_TEST_MCQ_1 = 1L;
	public static final Long APP_DATA_TEST_QA_2 = 2L;

	public static final Long APP_DATA_TEST_NORMAL = 0L;
	public static final Long APP_DATA_TEST_PRACTICE = 1l;

	public static final String APP_BUSSINESS_TYPE_COACHING = "coaching";

	public static final Long PUSH_TO_ALL = 0L;
	public static final Long PUSH_TO_REGISTERED = 1L;
	public static final Long PUSH_TO_UNREGISTERED = 2L;
	public static final Long PUSH_TO_GROUPS = 3L;
	public static final Long PUSH_TO_SELECTED = 4L;

}
