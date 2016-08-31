package com.hsf.restdemo.utils;

import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 操作Json数据工具类
 * 
 * @author daybreak
 * @since 2014-12-29
 */
public class JSONUtils {

	private static Logger logger = Logger.getLogger(JSONUtils.class);

	/**
	 * 将Json对象字符串转化为Map对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 转换成功返回Map对象，失败则返回null
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> JsonToMap(String jsonStr) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> params = objectMapper.readValue(jsonStr, Map.class);
			return params;
		} catch (Exception e) {
			logger.error("JsonToMap Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将Json对象字符串转化为Map<String, Object>对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 转换成功返回Map对象，失败则返回null
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> readJson2Map(String jsonStr) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> params = objectMapper.readValue(jsonStr, Map.class);
			return params;
		} catch (Exception e) {
			logger.error("JsonToMap Error " + jsonStr, e);
			return null;
		}
	}

	/**
	 * 将Json对象字符串转化为JavaBean类对象
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @param clazz
	 *            类类型
	 * @return 转换成功返回JavaBean类对象，失败则返回null
	 */
	public static <T> T readJson2Bean(String jsonStr, Class<T> clazz) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			T t = objectMapper.readValue(jsonStr, clazz);
			return t;
		} catch (Exception e) {
			logger.error("readJson2Bean Error " + jsonStr, e);
			return null;
		}
	}



}
