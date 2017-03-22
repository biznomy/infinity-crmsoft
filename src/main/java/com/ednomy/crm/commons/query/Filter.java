/**
 * 
 */
package com.ednomy.crm.commons.query;

import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *
 *
 */
public class Filter {

	private JsonNodeFactory factory;
	private ObjectNode filter;

	public void setFilter(ObjectNode filter) {
		this.filter = filter;
	}

	public ObjectNode getFilter() {
		return this.filter;
	}

	public Filter() {
		factory = JsonNodeFactory.instance;
		filter = factory.objectNode();
	}

	@SuppressWarnings("deprecation")
	public ObjectNode createFilter(String value, String condition,
			String opreator) {
		ObjectNode queryFilter = factory.objectNode();
		ObjectNode filter = factory.objectNode();
		ObjectNode cond = factory.objectNode();
		cond.put(condition, opreator);
		filter.put(value, cond);
		queryFilter.put(QueryConstants.FILTER, filter);
		return queryFilter;
	}

	@SuppressWarnings("deprecation")
	public ObjectNode addToFilter(ObjectNode onFilter, String value,
			String condition, String opreator) {
		ObjectNode queryFilter = onFilter;
		ObjectNode cond = factory.objectNode();
		cond.put(condition, opreator);
		if (queryFilter.has(QueryConstants.FILTER)) {
			ObjectNode obj = (ObjectNode) queryFilter
					.get(QueryConstants.FILTER);
			obj.put(value, cond);
		}
		return queryFilter;
	}

	public void getFilterArray(List<ObjectNode> filterList) {

	}

	public ArrayNode addFilterArray(List<ObjectNode> filterList) {
		ArrayNode arrayNode = factory.arrayNode();
		Iterator<ObjectNode> it = filterList.iterator();
		while (it.hasNext()) {
			arrayNode.add(it.next());
		}
		return arrayNode;
	}

	@SuppressWarnings("deprecation")
	public void addFilter(EdQueryBuilder q, String on, String value,
			String condition, String opreator) {
		ObjectNode query = q.getQuery();
		ObjectNode parent = factory.objectNode();
		ObjectNode filter = factory.objectNode();
		ObjectNode cond = factory.objectNode();
		cond.put(condition, opreator);
		filter.put(value, cond);
		parent.put(QueryConstants.FILTER, filter);
		query.put(on, parent);

	}

	public void addFilterMultiArray(ObjectNode filter, String on, String value,
			String condition, String operator) {
		String[] a = on.split("\\.");
		doit(a, 0, filter, on, value, condition, operator);
	}

	public void addFilterMulti(EdQueryBuilder q, String on,
			String value, String condition, String operator) {
		String[] a = on.split("\\.");
		doit(a, 0, ((ObjectNode) q.getQuery().get(QueryConstants.QUERY)), on,
				value, condition, operator);
	}
	
	public void addFilterEmpty(EdQueryBuilder q, String on,
			String value, String condition, String operator) {
		String[] a = on.split("\\.");
		if (operator != null && !operator.isEmpty()) {
			doit(a, 0, ((ObjectNode) q.getQuery().get(QueryConstants.QUERY)), on, value, condition, operator);
		}
	}


	@SuppressWarnings("deprecation")
	public void doit(String[] a, int i, ObjectNode q, String on, String value,
			String condition, String operator) {
		if ((a.length - 1) < i)
			return;
		ObjectNode query = q;
		@SuppressWarnings("unused")
		ObjectNode p = factory.objectNode();
		//System.err.println("Query has : " + a[i] + " , " + query.has(a[i]));
		if (!query.has(a[i])) {
			query.put(a[i], factory.objectNode());
			if ((a.length - 1) == i) {
				ObjectNode tmp = factory.objectNode();
				ObjectNode tmp1 = factory.objectNode();
				tmp1.put(condition, operator);
				tmp.put(value, tmp1);
				((ObjectNode) query.get(a[i])).put(QueryConstants.FILTER, tmp);
			}
			doit(a, i + 1, (ObjectNode) q.get(a[i]), on, value, condition,
					operator);
		} else if (query.has(a[i])) {
			if ((a.length - 1) == i) {
				ObjectNode tmp = factory.objectNode();
				ObjectNode tmp1 = factory.objectNode();
				tmp1.put(condition, operator);
				tmp.put(value, tmp1);
				if (!((ObjectNode) query.get(a[i])).has(QueryConstants.FILTER)) {
					((ObjectNode) query.get(a[i])).put(QueryConstants.FILTER,
							tmp);
				} else {
					ObjectNode tmp2 = factory.objectNode();
					tmp2.put(condition, operator);
					((ObjectNode) ((ObjectNode) query.get(a[i]))
							.get(QueryConstants.FILTER)).put(value, tmp2);
				}
			}
			doit(a, i + 1, (ObjectNode) q.get(a[i]), on, value, condition,
					operator);
		}

	}
}
