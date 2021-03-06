package com.th.system.utils;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	protected static Log log = LogFactory.getLog(JsonUtil.class);
	private ObjectMapper mapper;
	private static JsonUtil jsonUtil;

	public static JsonUtil getInstance() {
		if (null == jsonUtil) {
			jsonUtil = new JsonUtil();
		}
		return jsonUtil;
	}

	private JsonUtil() {
		this.mapper = new ObjectMapper();
	}

	public ObjectMapper getMapper() {
		return mapper;
	}

	public static String pathValue(String jsonNode, String path) {
		String result = "";
		ObjectMapper obm = JsonUtil.getInstance().getMapper();
		JsonNode rootNode;
		try {
			rootNode = obm.readTree(jsonNode);
			// result = rootNode.path(path).getValueAsText();
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}

		return result;
	}

	public static String pathValue(JsonNode jsonNode, String path) {
		String result = null;
		// result = jsonNode.path(path).getValueAsText();
		return result;
	}

	public static JsonNode readNode(String jsonNode) {
		ObjectMapper obm = JsonUtil.getInstance().getMapper();
		JsonNode rootNode = null;
		try {
			rootNode = obm.readTree(jsonNode);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}
		return rootNode;
	}

	public static Map readMap(String jsonStr) {
		ObjectMapper om = JsonUtil.getInstance().getMapper();
		Map map = null;
		try {
			map = om.readValue(jsonStr, Map.class);
		} catch (Exception e) {
			log.error("", e);
		}
		return map;
	}

	public static Object readObj(String jsonStr, Class clazz) {
		ObjectMapper om = JsonUtil.getInstance().getMapper();
		try {
			return om.readValue(jsonStr, clazz);
		} catch (Exception e) {
			log.error("", e);
		}
		return null;
	}

	public static String writeAsString(Object obj) {
		ObjectMapper obm = JsonUtil.getInstance().getMapper();
		String reuslt = "";
		try {
			reuslt = obm.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}

		return reuslt;
	}

	
	public static String nodeText(JsonNode node, String nodeName) {
		String text = "";
		if (node != null) {
			JsonNode tmp = node.path(nodeName);
			if (tmp != null) {
				// text = tmp.getValueAsText();
			}
		}
		if (text == null) {
			text = "";
		}
		return text;
	}

	/**
	 * ?????????JSON ?????????????????????????????????java?????????????????? {"id" : idValue, "name" : nameValue, "aBean" :
	 * {"aBeanId" : aBeanIdValue, ...}}
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object getDTO(String jsonString, Class clazz) {
		Object ob = null;
		try {
			ob = JsonUtil.getInstance().getMapper().readValue(jsonString, clazz);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ob;
	}

	/**
	 * ?????????JSON??????????????????java???????????????????????? [{"id" : idValue, "name" : nameValue}, {"id" :
	 * idValue, "name" : nameValue}, ...]
	 * 
	 * @param object
	 * @param clazz
	 * @return
	 */
	public static Object[] getDTOArray(String jsonString, Class clazz) {
		Object[] arr = null;
		try {
			arr = (Object[]) JsonUtil.getInstance().getMapper().readValue(jsonString, clazz);
			System.out.println(arr.length);
			for (int i = 0; i < arr.length; i++) {
				System.out.println(arr[i]);
			}
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arr;
	}
	

}
