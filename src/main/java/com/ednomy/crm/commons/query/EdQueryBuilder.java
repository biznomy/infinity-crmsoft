/**
 * 
 */
package com.ednomy.crm.commons.query;

import com.ednomy.crm.commons.query.QueryConstants;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 *
 */
public class EdQueryBuilder {

	JsonNodeFactory factory;
	private ObjectNode query;

	public EdQueryBuilder() {
		factory = JsonNodeFactory.instance;
		query = factory.objectNode();
	}

	/**
	 * @return the query
	 */
	@SuppressWarnings("deprecation")
	public ObjectNode getQuery() {
		if (!query.has(QueryConstants.QUERY)) {
			ObjectNode queryNode = factory.objectNode();
			queryNode.put(QueryConstants.QUERY, query);
			return query = queryNode;
		} else {
			return query;
		}
	}

	public String getQueryString() {
		return "?jsq=" + getQuery();
	}

	public String getJsonString() {
		return "" + getQuery();
	}

	@SuppressWarnings("deprecation")
	public void setPageQuery(int start, int max) {
		ObjectNode page = factory.objectNode();
		page.put(QueryConstants.START, start);
		page.put(QueryConstants.MAX, max);
		query = getQuery();
		query.put(QueryConstants.PAGE, page);
	}

	@SuppressWarnings("deprecation")
	public void setPageStart(int start) {
		ObjectNode page = factory.objectNode();
		page.put(QueryConstants.START, start);
		query = getQuery();
		query.put(QueryConstants.PAGE, page);
	}

	@SuppressWarnings("deprecation")
	public void setPageMax(int max) {
		ObjectNode page = factory.objectNode();
		page.put(QueryConstants.MAX, max);
		query = getQuery();
		query.put(QueryConstants.PAGE, page);
	}

	@SuppressWarnings("deprecation")
	public void setSort(String byField, String type) {
		ObjectNode page = factory.objectNode();
		page.put(byField, type);
		query = getQuery();
		query.put(QueryConstants.SORT, page);
	}

}
