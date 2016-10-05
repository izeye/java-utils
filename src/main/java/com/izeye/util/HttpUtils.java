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

import java.util.HashMap;
import java.util.Map;

/**
 * Utilities for HTTP.
 *
 * @author Johnny Lim
 */
public abstract class HttpUtils {

	private static final String DELIMITER_COOKIE = ";";
	private static final char DELIMITER_COOKIE_KEY_VALUE = '=';

	private static final String DELIMITER_PARAMETER = "&";
	private static final String DELIMITER_PARAMETER_NAME_VALUE = "=";

	private HttpUtils() {
	}

	public static Map<String, String> parseCookieHeaderValue(String cookieHeaderValue) {
		Map<String, String> cookies = new HashMap<>();
		String[] cookieTokens = cookieHeaderValue.split(DELIMITER_COOKIE);
		for (String cookieToken : cookieTokens) {
			String trimmed = cookieToken.trim();
			int index = trimmed.indexOf(DELIMITER_COOKIE_KEY_VALUE);
			String name = trimmed.substring(0, index);
			String value = trimmed.substring(index + 1);
			cookies.put(name, value);
		}
		return cookies;
	}

	public static String extractCookieValue(String cookieHeaderValue, String cookieName) {
		return parseCookieHeaderValue(cookieHeaderValue).get(cookieName);
	}

	public static Map<String, String> parseQueryString(String queryString) {
		Map<String, String> parameters = new HashMap<>();
		String[] parameterPairs = queryString.split(DELIMITER_PARAMETER);
		for (String parameterPair : parameterPairs) {
			String[] nameAndValue = parameterPair.split(DELIMITER_PARAMETER_NAME_VALUE);
			String name = nameAndValue[0];
			String value = nameAndValue[1];
			parameters.put(name, value);
		}
		return parameters;
	}

	public static String buildQueryString(Map<String, ?> parameters) {
		if (parameters.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, ?> parameter : parameters.entrySet()) {
			sb.append(parameter.getKey());
			sb.append(DELIMITER_PARAMETER_NAME_VALUE);
			sb.append(parameter.getValue());
			sb.append(DELIMITER_PARAMETER);
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

}
