/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izeye.util;

import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Utilities for URLs.
 *
 * @author Johnny Lim
 */
public abstract class UrlUtils {

	private static final String PROTOCOL_DELIMITER = "://";

	private static final String HTTP_PROTOCOL = "http";

	private static final String QUERY_STRING_START_CHAR = "?";

	private static final String PARAMETER_DELIMITER = "&";

	private static final String PARAMETER_NAME_VALUE_DELIMITER = "=";

	private static final String FRAGMENT_START_CHAR = "#";

	private static ObjectMapper mapper = new ObjectMapper();
	static {
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
	}

	private UrlUtils() {
	}

	public static boolean hasProtocol(String url) {
		return url.contains(PROTOCOL_DELIMITER);
	}

	public static String prependHttpProtocolIfAbsent(String url) {
		if (hasProtocol(url)) {
			return url;
		}
		return HTTP_PROTOCOL + PROTOCOL_DELIMITER + url;
	}

	public static String addParameter(String url, String name, String value) {
		int fragmentStartIndex = url.indexOf(FRAGMENT_START_CHAR);
		String fragment = null;
		if (fragmentStartIndex != -1) {
			fragment = url.substring(fragmentStartIndex);
			url = url.substring(0, fragmentStartIndex);
		}
		if (!url.contains(QUERY_STRING_START_CHAR)) {
			url += QUERY_STRING_START_CHAR;
		}
		else {
			url += PARAMETER_DELIMITER;
		}
		url += encodeParameter(name, value);
		if (fragment != null) {
			url += fragment;
		}
		return url;
	}

	private static String encodeParameter(String name, String value) {
		String encodedValue = UrlEncodingUtils.encode(value);
		return name + PARAMETER_NAME_VALUE_DELIMITER + encodedValue;
	}

	public static void printComponents(String url) {
		Map<String, Object> components = new TreeMap<>();
		int anchorStartIndex = url.indexOf('#');
		if (anchorStartIndex != -1) {
			components.put("anchor", url.substring(anchorStartIndex + 1));
			url = url.substring(0, anchorStartIndex);
		}

		int protocolEndIndex = url.indexOf("://");
		components.put("protocol", url.substring(0, protocolEndIndex));
		url = url.substring(protocolEndIndex + 3);

		int domainEndIndex = url.indexOf('/');
		components.put("domain", url.substring(0, domainEndIndex));
		url = url.substring(domainEndIndex);

		int parameterStartIndex = url.indexOf('?');
		components.put("path", url.substring(0, parameterStartIndex));

		Map<String, String> parameters = new TreeMap<>();
		String encodedParameters = url.substring(parameterStartIndex + 1);
		for (String parameter : encodedParameters.split("&")) {
			String[] nameValuePair = parameter.split("=");
			parameters.put(nameValuePair[0], UrlEncodingUtils.decode(nameValuePair[1]));
		}
		components.put("parameters", parameters);

		try {
			String json = mapper.writeValueAsString(components);
			System.out.println(json);
		}
		catch (JsonProcessingException ex) {
			throw new RuntimeException(ex);
		}
	}

}
