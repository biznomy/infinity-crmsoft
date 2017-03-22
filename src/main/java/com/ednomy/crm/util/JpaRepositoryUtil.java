package com.ednomy.crm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ednomy.crm.commons.constants.EdQueryConstants;
import com.ednomy.crm.exception.EdnomyException;

@Component
public class JpaRepositoryUtil {

	private static final String REQUEST_IS_INVALID = "request is invalid";

	org.slf4j.Logger LOGGER = LoggerFactory.getLogger(JpaRepositoryUtil.class);

	@PersistenceContext
	EntityManager entityManager;

	@Autowired
	ReflectionUtil reflectionUtil;

	@Autowired
	EdDateUtil edDateUtil;

	@SuppressWarnings({ "rawtypes" })
	// Process full Query Node with all fields and sub object conditions joins
	// etc.
	public void populateConditionsForQuery(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root,
			Map<String, String> queryMap, List<Predicate> predicates) {

		String jsq = null;
		
		if (queryMap != null) {
			LOGGER.info(queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER));
			jsq = queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER);
		}else{
			LOGGER.info("queryMap is null");
			throw new EdnomyException(REQUEST_IS_INVALID);
		}
		if (jsq == null) {
			LOGGER.info("jsq is null");
			throw new EdnomyException(REQUEST_IS_INVALID);
		}

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		JsonNode queryNode;
		JsonNode rootEntityNode;
		JsonNode rootEntityFilterNode;

		try {

			rootNode = objectMapper.readTree(jsq);

			// query node is root QUERY json node
			queryNode = rootNode.get(EdQueryConstants.QUERY_NODE);
			if (queryNode == null) {
				throw new EdnomyException(REQUEST_IS_INVALID);
			}
			if (queryNode.findPath("filter").isMissingNode()) {
				throw new EdnomyException(REQUEST_IS_INVALID);
			}
			
			if (queryNode != null && queryNode.getFields().hasNext()) {

				// This is root entity (table) on which select is performed
				rootEntityNode = queryNode.getFields().next().getValue();
				String rootEntityName = queryNode.getFields().next().getKey();

				// processing filter node in root node of query node
				if (rootEntityNode.has(EdQueryConstants.FILTER_NODE)) {

					rootEntityFilterNode = rootEntityNode
							.get(EdQueryConstants.FILTER_NODE);

					// condition for rootEntityFilter
					populateForFilters(criteriaBuilder, criteriaQuery, root,
							rootEntityFilterNode, predicates, rootEntityName);

				}
				// process other nodes (joins or sub select ) for root query
				// node
				populateConditionsForRoot(criteriaBuilder, criteriaQuery, root,
						queryMap, predicates, rootEntityNode, rootEntityName);
			}else{
				throw new EdnomyException(REQUEST_IS_INVALID);
			}
		} catch (Exception exception) {
			LOGGER.error(exception.getMessage(), exception);
			throw new EdnomyException(REQUEST_IS_INVALID);
		}

	}

	@SuppressWarnings("rawtypes")
	public void populateConditionsForJoin(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Join root,
			Map<String, String> queryMap, List<Predicate> predicates,
			JsonNode rootEntityNode, String entityName) {

		Iterator<Entry<String, JsonNode>> iterator = rootEntityNode.getFields();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = iterator.next();

			if (entry.getKey().equalsIgnoreCase(
					EdQueryConstants.FILTER_NODE)) {
				continue;
			}

			// case for joins
			String joinTableName = entry.getKey();
			if (entry.getValue().isArray()) {
				joinAndFilterForJoin(criteriaBuilder, criteriaQuery, root,
						predicates, entry, joinTableName, entityName);
			} else {
				Join join = root.join(joinTableName);
				JsonNode joinNode = entry.getValue();
				if (joinNode.has(EdQueryConstants.FILTER_NODE)) {

					JsonNode joinFilterNode = joinNode
							.get(EdQueryConstants.FILTER_NODE);
					populateForFilters(criteriaBuilder, criteriaQuery, join,
							joinFilterNode, predicates, entityName);
				}
				populateConditionsForJoin(criteriaBuilder, criteriaQuery, join,
						queryMap, predicates, joinNode, entityName);
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public void joinAndFilterForJoin(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Join root, List<Predicate> predicates,
			Map.Entry<String, JsonNode> entry, String joinTableName,
			String entityName) {
		Iterator<JsonNode> iteratorFilter = entry.getValue().getElements();
		while (iteratorFilter.hasNext()) {

			JsonNode joinNode = iteratorFilter.next();

			if (joinNode.has(EdQueryConstants.FILTER_NODE)) {
				Join join = root.join(joinTableName);
				JsonNode joinFilterNode = joinNode
						.get(EdQueryConstants.FILTER_NODE);
				populateForFilters(criteriaBuilder, criteriaQuery, join,
						joinFilterNode, predicates, entityName);
			}
		}

	}

	@SuppressWarnings("rawtypes")
	public void populateConditionsForRoot(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root,
			Map<String, String> queryMap, List<Predicate> predicates,
			JsonNode rootEntityNode, String rootEntityName) {

		Iterator<Entry<String, JsonNode>> iterator = rootEntityNode.getFields();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = iterator.next();

			if (entry.getKey().equalsIgnoreCase(
					EdQueryConstants.FILTER_NODE)) {
				continue;
			}

			// case for joins
			String joinTableName = entry.getKey();
			if (entry.getValue().isArray()) {

				joinAndFilterForRoot(criteriaBuilder, criteriaQuery, root,
						predicates, entry, joinTableName, rootEntityName);

			} else {
				Join join = root.join(joinTableName);
				JsonNode joinNode = entry.getValue();
				if (joinNode.has(EdQueryConstants.FILTER_NODE)) {

					JsonNode joinFilterNode = joinNode
							.get(EdQueryConstants.FILTER_NODE);
					populateForFilters(criteriaBuilder, criteriaQuery, join,
							joinFilterNode, predicates, rootEntityName);
				}

				populateConditionsForJoin(criteriaBuilder, criteriaQuery, join,
						queryMap, predicates, joinNode, rootEntityName);

			}

		}

	}

	@SuppressWarnings("rawtypes")
	private void joinAndFilterForRoot(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root, List<Predicate> predicates,
			Map.Entry<String, JsonNode> entry, String joinTableName,
			String entityName) {
		Iterator<JsonNode> iteratorFilter = entry.getValue().getElements();
		while (iteratorFilter.hasNext()) {

			JsonNode joinNode = iteratorFilter.next();

			if (joinNode.has(EdQueryConstants.FILTER_NODE)) {
				Join join = root.join(joinTableName);
				JsonNode joinFilterNode = joinNode
						.get(EdQueryConstants.FILTER_NODE);
				populateForFilters(criteriaBuilder, criteriaQuery, join,
						joinFilterNode, predicates, entityName);
			}
		}
	}

	public boolean hasFetchCondition(Map<String, String> queryMap) {
		String jsq = null;
		if (queryMap == null) {
			return false;
		}
		jsq = queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER);
		if (jsq == null) {
			return false;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(jsq);
			if (rootNode.get(EdQueryConstants.FETCH_NODE) != null) {
				return true;
			}
		} catch (Exception exception) {
		}
		return false;

	}

	private Predicate predicateOperation(CriteriaBuilder criteriaBuilder,
			Predicate firstPredicate, Predicate secondPredicate,
			String conditionalOperator) {
		if (firstPredicate == null) {
			return (secondPredicate);
		} else {
			if (conditionalOperator
					.equalsIgnoreCase(EdQueryConstants.CONDITION_OP_AND)) {
				return criteriaBuilder.and(firstPredicate, secondPredicate);
			} else if (conditionalOperator
					.equalsIgnoreCase(EdQueryConstants.CONDITION_OP_OR)) {
				return criteriaBuilder.or(firstPredicate, secondPredicate);
			}
			return firstPredicate;
		}
	}

	@SuppressWarnings({ "rawtypes"})
	private void populateForFilters(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Path root, JsonNode filterNode,
			List<Predicate> predicates, String entityName) {
		// for filter under rootEntity
		Iterator<Entry<String, JsonNode>> iterator = filterNode.getFields();

		String op = EdQueryConstants.DEFAULT_CONDITION_OP;

		if (filterNode.has(EdQueryConstants.CONDITION_OP)) {
			op = filterNode.get(EdQueryConstants.CONDITION_OP)
					.getTextValue();
		}

		if (criteriaQuery == null) {
		}

		Predicate predicate = null;
		Predicate predicateForArray = null;

		// iterating through all fields of filter
		while (iterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = iterator.next();
			// logic for nested filter here

			String fieldName = entry.getKey();

			if (fieldName
					.equalsIgnoreCase(EdQueryConstants.CONDITION_OP)) {
				continue;
			}

			// entry in filter ie. field has array
			if (entry.getValue().isArray()) {

				Iterator<JsonNode> iteratorFilterField = entry.getValue()
						.getElements();

				Predicate fieldPredicate = null;
				predicateForArray = null;
				

				while (iteratorFilterField.hasNext()) {

					String conditionLevelOp = null;

					Predicate fieldPredicateTemp = null;
					Iterator<Entry<String, JsonNode>> iteratorFilterFieldConditions = iteratorFilterField
							.next().getFields();

					Map.Entry<String, JsonNode> nodeSingleFieldCondition = iteratorFilterFieldConditions
							.next();

					if (nodeSingleFieldCondition.getKey().equalsIgnoreCase(
							EdQueryConstants.CONDITION_OP)) {
						conditionLevelOp = nodeSingleFieldCondition.getValue()
								.asText();
						nodeSingleFieldCondition = iteratorFilterFieldConditions
								.next();
					}

					if (conditionLevelOp == null) {
						conditionLevelOp = op;
					}

					fieldPredicateTemp = populateForFilterField(
							criteriaBuilder, fieldPredicateTemp, op, root,
							fieldName, nodeSingleFieldCondition, entityName);
					fieldPredicate = predicateOperation(criteriaBuilder,
							fieldPredicate, fieldPredicateTemp,
							conditionLevelOp);
				}
				predicateForArray = fieldPredicate;
			} else {

				String conditionLevelOp = null;

				Iterator<Entry<String, JsonNode>> iteratorFilterFieldConditions = entry
						.getValue().getFields();

				Map.Entry<String, JsonNode> nodeSingleFieldCondition = iteratorFilterFieldConditions
						.next();

				if (nodeSingleFieldCondition.getKey().equalsIgnoreCase(
						EdQueryConstants.CONDITION_OP)) {
					conditionLevelOp = nodeSingleFieldCondition.getValue()
							.asText();
					nodeSingleFieldCondition = iteratorFilterFieldConditions
							.next();
				}

				if (conditionLevelOp == null) {
					conditionLevelOp = op;
				}

				predicate = populateForFilterField(criteriaBuilder, predicate,
						conditionLevelOp, root, fieldName,
						nodeSingleFieldCondition, entityName);
			}
			
			if(predicateForArray!=null && predicate==null){
				predicate=predicateForArray;
			}
			else if(predicateForArray!=null && predicate!=null){
				predicate = predicateOperation(criteriaBuilder, predicate,
						predicateForArray, op);
			}
		}

		if (predicate != null) {
			predicates.add(predicate);
		}

	}

	@SuppressWarnings("unchecked")
	public Predicate populateForFilterField(CriteriaBuilder criteriaBuilder,
			Predicate predicate, String op, @SuppressWarnings("rawtypes") Path root, String fieldName,
			Map.Entry<String, JsonNode> subentry, String entityName) {

		String optype = subentry.getKey();
		String fieldValue = "";
		List<String> fieldValueList = new ArrayList<String>();
		if (!subentry.getValue().isArray()) {
			fieldValue = subentry.getValue().asText();
		} else {
			Iterator<JsonNode> itr = subentry.getValue().getElements();
			while (itr.hasNext()) {
				fieldValueList.add(itr.next().asText());
			}
		}

		String fieldDataType = reflectionUtil.getDataTypeOfEntity(entityName, fieldName);

//		System.out.println("*** " + entityName + " " + fieldName + " " + fieldDataType);

		if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_EQUALS)) {

			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.equal(root.get(fieldName),
						new Long(fieldValue));
			} else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.equal(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}else{
				secondPredicate = criteriaBuilder.equal(root.get(fieldName), fieldValue);
			}

			predicate = predicateOperation(criteriaBuilder, predicate,
					secondPredicate, op);

		} else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_GREATER)) {
			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.gt(root.get(fieldName),
						new Long(fieldValue));
			} else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.greaterThan(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}

			predicate = predicateOperation(criteriaBuilder, predicate,
					secondPredicate, op);
		} else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_LESSER)) {
			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.le(root.get(fieldName),
						new Long(fieldValue));
			} else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.lessThan(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}
			predicate = predicateOperation(criteriaBuilder, predicate,
					secondPredicate, op);

		} else if (optype
				.equalsIgnoreCase(EdQueryConstants.FIELD_OP_GREATER_EQUAL)) {
			
			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.ge(root.get(fieldName),
						new Long(fieldValue));
			} else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}
			predicate = predicateOperation(criteriaBuilder, predicate, secondPredicate, op);

		} else if (optype
				.equalsIgnoreCase(EdQueryConstants.FIELD_OP_LESSER_EQUAL)) {
			
			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.le(root.get(fieldName),
						new Long(fieldValue));
			} else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.lessThanOrEqualTo(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}
			predicate = predicateOperation(criteriaBuilder, predicate, secondPredicate, op);

		} else if (optype
				.equalsIgnoreCase(EdQueryConstants.FIELD_OP_LIKE)) {

			predicate = predicateOperation(
					criteriaBuilder, predicate,
					criteriaBuilder.like(criteriaBuilder.upper(root.get(fieldName)), "%"
									+ fieldValue.toUpperCase() + "%"), op);

		} else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_NOT_EQUAL)) {
			Predicate secondPredicate = null;

			if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_LONG)) {
				secondPredicate = criteriaBuilder.notEqual(root.get(fieldName),
						new Long(fieldValue));
			}else if (fieldDataType.equalsIgnoreCase(EdQueryConstants.DATA_TYPE_DATE)) {
				secondPredicate = criteriaBuilder.notEqual(root.get(fieldName),
						edDateUtil.getDatefromString(fieldValue));
			}else{
				secondPredicate = criteriaBuilder.notEqual(root.get(fieldName), fieldValue);
			}

			predicate = predicateOperation(criteriaBuilder, predicate, secondPredicate, op);


		} else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_IN)) {

			predicate = predicateOperation(criteriaBuilder, predicate, root
					.get(fieldName).in(fieldValueList), op);

		}else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_Is_NULL)) {

			predicate = predicateOperation(criteriaBuilder, predicate, root
					.get(fieldName).isNull(), op);

		} else if (optype.equalsIgnoreCase(EdQueryConstants.FIELD_OP_Is_NOT_NULL)) {

			predicate = predicateOperation(criteriaBuilder, predicate, root
					.get(fieldName).isNotNull(), op);

		}

		return predicate;

	}

	public void populateConditionsForPage(Query q, Map<String, String> queryMap) {
		String jsq = null;
		if (queryMap != null) {
			jsq = queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER);
		}

		if (jsq == null) {
			return;
		}

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;

		try {
			rootNode = objectMapper.readTree(jsq);
			if (rootNode.get(EdQueryConstants.PAGE_NODE) != null) {
				if (rootNode.get(EdQueryConstants.PAGE_NODE)
						.get(EdQueryConstants.PAGE_START).asInt() > 0)
					q.setFirstResult((rootNode
							.get(EdQueryConstants.PAGE_NODE)
							.get(EdQueryConstants.PAGE_START).asInt() - 1)
							* rootNode.get(EdQueryConstants.PAGE_NODE)
									.get(EdQueryConstants.PAGE_MAX)
									.asInt());
				else
					q.setFirstResult(EdQueryConstants.DEFAULT_PAGE_START_VALUE);
				if (rootNode.get(EdQueryConstants.PAGE_NODE)
						.get(EdQueryConstants.PAGE_MAX).asInt() > 0)
					q.setMaxResults(rootNode
							.get(EdQueryConstants.PAGE_NODE)
							.get(EdQueryConstants.PAGE_MAX).asInt());
				else
					q.setMaxResults(EdQueryConstants.DEFAULT_PAGE_MAX_VALUE);
			} else {
				q.setFirstResult(EdQueryConstants.DEFAULT_PAGE_START_VALUE);
				q.setMaxResults(EdQueryConstants.DEFAULT_PAGE_MAX_VALUE);
			}

		} catch (Exception exception) {
		}

	}

	@SuppressWarnings("rawtypes")
	public void populateFetchForRoot(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root, Map<String, String> queryMap) {

		String jsq = null;
		jsq = queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER);
		if (jsq == null) {
			return;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		JsonNode rootEntityNode;
		try {
			rootNode = objectMapper.readTree(jsq);
			if (!rootNode.has(EdQueryConstants.FETCH_NODE)) {
				return;
			}
			rootEntityNode = rootNode.get(EdQueryConstants.FETCH_NODE);

			Iterator<Entry<String, JsonNode>> iterator = rootEntityNode
					.getFields();
			while (iterator.hasNext()) {
				Map.Entry<String, JsonNode> entry = iterator.next();

				String key = entry.getKey();
				Fetch fetch = root.fetch(key, JoinType.LEFT);
				if (entry.getValue().isObject()) {
					JsonNode currNode = entry.getValue();
					populateFetchForNest(criteriaBuilder, criteriaQuery, fetch,
							queryMap, currNode);
				}

			}

		} catch (Exception exception) {
		}
	}

	@SuppressWarnings("rawtypes")
	public void populateSort(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root, Map<String, String> queryMap) {
		String jsq = null;
		if (queryMap == null) {
			return;
		}
		jsq = queryMap.get(EdQueryConstants.JSON_QUERY_PARAMETER);
		if (jsq == null) {
			return;
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		JsonNode rootEntityNode;
		try {
			rootNode = objectMapper.readTree(jsq);
			if (!rootNode.has(EdQueryConstants.SORT_NODE)) {
				return;
			}
			rootEntityNode = rootNode.get(EdQueryConstants.SORT_NODE);

			parseSortNode(criteriaBuilder, criteriaQuery, root, rootEntityNode);

		} catch (Exception exception) {
		}
	}

	@SuppressWarnings("rawtypes")
	private void parseSortNode(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Root root, JsonNode rootEntityNode) {
		Iterator<Entry<String, JsonNode>> iterator = rootEntityNode.getFields();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = iterator.next();

			String key = entry.getKey();
			String val = entry.getValue().asText();

			if (val.equalsIgnoreCase(EdQueryConstants.SORT_ASC)) {
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get(key)));
			} else if (val.equalsIgnoreCase(EdQueryConstants.SORT_DESC)) {
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get(key)));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	public void populateFetchForNest(CriteriaBuilder criteriaBuilder,
			CriteriaQuery criteriaQuery, Fetch root,
			Map<String, String> queryMap, JsonNode rootEntityNode) {

		Iterator<Entry<String, JsonNode>> iterator = rootEntityNode.getFields();
		while (iterator.hasNext()) {
			Map.Entry<String, JsonNode> entry = iterator.next();

			String key = entry.getKey();
			Fetch fetch = root.fetch(key, JoinType.LEFT);
			if (entry.getValue().isObject()) {
				JsonNode currNode = entry.getValue();
				populateFetchForNest(criteriaBuilder, criteriaQuery, fetch,
						queryMap, currNode);
			}

		}

	}

	@SuppressWarnings("unchecked")
	// called directly from repo
	public <T> List<T> listWithoutFetch(Map<String, String> queryMap,
			Class<T> clazz) {

		// inititalize objects neede for jpa Criteria Apis
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);

		criteriaQuery.distinct(true);
		Root<T> root = criteriaQuery.from(clazz);

		List<Predicate> predicates = new ArrayList<Predicate>();

		// one by one we populate conditions for Query , Page and Sort node in
		// jpa queries
		populateConditionsForQuery(criteriaBuilder, criteriaQuery, root,
				queryMap, predicates);

		criteriaQuery.select(root)
				.where(predicates.toArray(new Predicate[] {}));

		populateSort(criteriaBuilder, criteriaQuery, root, queryMap);

		Query query = entityManager.createQuery(criteriaQuery);

		populateConditionsForPage(query, queryMap);

		return query.getResultList();

	}

	@SuppressWarnings("unchecked")
	// entry method from rep for list
	public <T> List<T> listWithFetch(Map<String, String> queryMap,
			List<Long> queryresult, String queryfield, Class<T> clazz) {

		// inititalize objects neede for jpa Criteria Apis
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
		criteriaQuery.distinct(true);
		Root<T> root = criteriaQuery.from(clazz);

		List<Predicate> predicates = new ArrayList<Predicate>();

		Expression<Long> exp = root.get(queryfield);
		predicates.add(exp.in(queryresult));

		populateFetchForRoot(criteriaBuilder, criteriaQuery, root, queryMap);

		populateSort(criteriaBuilder, criteriaQuery, root, queryMap);

		criteriaQuery.select(root)
				.where(predicates.toArray(new Predicate[] {}));

		Query q = entityManager.createQuery(criteriaQuery);

		return q.getResultList();

	}

	public <T> long count(Map<String, String> queryMap, Class<T> clazz) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);

		Root<T> root = criteriaQuery.from(clazz);

		List<Predicate> predicates = new ArrayList<Predicate>();

		populateConditionsForQuery(criteriaBuilder, criteriaQuery, root,
				queryMap, predicates);

		criteriaQuery.select(criteriaBuilder.countDistinct(root));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));

		Query query = entityManager.createQuery(criteriaQuery);
		return Long.parseLong(query.getSingleResult().toString());
	}

	// fetch logic ...
	// 1. in list method of repository call to listwithoutfetch(means no
	// root.fetch jpa ) with all conditions .
	// 2 This gives all root entities as per condition with fetched. ( because
	// fetch with condition was giving problem in single query jpa not
	// supporting)
	// 3. Now if fetch has anything else than root node. run query again this
	// time fetch with only in ids list returned from step 2. so fetch works and
	// no join in this query

}
