package com.ednomy.crm.commons.constants;

public class EdQueryConstants {

	public static final String JSON_QUERY_PARAMETER = "jsq";

	public static final String PAGE_NODE = "page";
	public static final String PAGE_START = "start";
	public static final String PAGE_MAX = "max";

	public static final String FETCH_NODE = "fetch";

	public static final String SORT_NODE = "sort";

	

	public static final String SORT_ASC = "asc";
	public static final String SORT_DESC = "desc";

	public static final int DEFAULT_PAGE_START_VALUE = 0;
	public static final int DEFAULT_PAGE_MAX_VALUE = 10;

	public static final String QUERY_NODE = "query";
	public static final String FILTER_NODE = "filter";

	public static final String FIELD_OP_EQUALS = "equals";
	public static final String FIELD_OP_LIKE = "like";
	public static final String FIELD_OP_GREATER = "greater";
	public static final String FIELD_OP_LESSER = "less";
	public static final String FIELD_OP_GREATER_EQUAL = "ge";
	public static final String FIELD_OP_LESSER_EQUAL = "le";
	public static final String FIELD_OP_NOT_EQUAL = "notequals";
	public static final String FIELD_OP_IN = "in";
	public static final String FIELD_OP_Is_NULL = "isnull";
	public static final String FIELD_OP_Is_NOT_NULL = "isnotnull";
	

	public static final String CONDITION_OP_AND = "and";
	public static final String CONDITION_OP_OR = "or";

	public static final String DEFAULT_CONDITION_OP = CONDITION_OP_AND;

	public static final String DATA_TYPE_DATE = "java.util.Date";
	public static final String DATA_TYPE_STRING = "java.lang.String";
	public static final String DATA_TYPE_LONG = "java.lang.Long";
	
	public static final String CONDITION_OP = "op";

	private EdQueryConstants() {

	}

}
