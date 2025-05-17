package com.demo.sample.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.io.Content;
import org.eclipse.jetty.server.Request;

public class ServerUtils {
	public static Map<String, String> getHeaders(Request request) {
		Map<String, String> map = new HashMap<>();
		
		Iterator<HttpField> iterator = request.getHeaders().iterator();
		while (iterator.hasNext()) {
			HttpField httpField = iterator.next();
			String key = httpField.getName();
			String value = httpField.getValue();

			map.put(key, value);
		}

		return map;
	}

	public static ByteBuffer utf_8_encode(String str) {
		if (str == null) {
			return null;
		}

		Charset charset = Charset.forName("UTF-8");
		ByteBuffer byteBuffer = charset.encode(str);

		return byteBuffer;
	}

	public static String getPathVariables(Request request) {
		String fields = Request.getPathInContext(request).replaceAll("^/", "");

		return fields;
	}

	public static String getRequestBody(Request request) {
		StringBuilder stringBuilder = new StringBuilder();
		InputStream inputStream = Content.Source.asInputStream(request);
		Reader reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
		int c = 0;
		try {
			while ((c = reader.read()) != -1) {
				stringBuilder.append((char) c);
			}
		} catch (IOException e) {
			return null;
		}

		return stringBuilder.toString();
	}
}
