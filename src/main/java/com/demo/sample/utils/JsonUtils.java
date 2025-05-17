package com.demo.sample.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	public static String stringify(Object obj) {
		String json = null;

		if (obj != null) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);
			try {
				json = mapper.writeValueAsString(obj);
			}
			catch (JsonProcessingException e) {
				json = null;
			}
		}

		return json;
	}

	public static <T> T parse(Class<T> destClass, String json) {
		T obj = null;

		if (json != null && !json.isEmpty()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.ALWAYS);
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			try {
				return mapper.readValue(json, destClass);
			} catch (JsonMappingException e) {
				throw new RuntimeException(e);
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		}

		return obj;
	}
}
