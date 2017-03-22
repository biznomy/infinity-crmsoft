package com.ednomy.crm.util;

import java.util.StringTokenizer;

import org.codehaus.jackson.JsonNode;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

	public JsonNode getNestedNode(JsonNode sourceNode, String nodePath) {
		StringTokenizer st = new StringTokenizer(nodePath, ".");

		String fieldName = st.nextToken();

		if (sourceNode.has(fieldName)) {
			JsonNode subNode = sourceNode.get(fieldName);
			if (!st.hasMoreTokens()) {
				return subNode;
			} else {
				String newNodePath = nodePath.replace(fieldName + ".", "");
				return getNestedNode(subNode, newNodePath);
			}
		} else {
			return null;
		}

	}

	public String getNestedVal(JsonNode sourceNode, String nodePath,
			String fieldName) {
		if (nodePath != null && !nodePath.isEmpty()) {
			sourceNode = getNestedNode(sourceNode, nodePath);
		} else {
			if (sourceNode == null || fieldName == null || fieldName.isEmpty()) {
				return null;
			} else {
				if (sourceNode.has(fieldName)) {
					return (sourceNode.get(fieldName).asText().trim());
				}

			}
		}
		return null;
	}
	
	public JsonNode getNestedRoot(JsonNode sourceNode, String nodePath) {
		return sourceNode.findPath(nodePath);
	}
}
