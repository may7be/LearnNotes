package com.zg.android_utils.util_common;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	private static ObjectMapper mapper;

	private static ObjectMapper getMapper() {
		if (mapper == null) {
			mapper = new ObjectMapper();
		}
		// 反序列化时， 忽略不存在的字段
		mapper.getDeserializationConfig().set(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper;
	}

	public static String toJson(Object obj) {
		String str = null;
		try {
			str = getMapper().writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static <T> T toObj(String json, Class<T> clazz) {
		try {
			T obj = getMapper().readValue(json, clazz);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
