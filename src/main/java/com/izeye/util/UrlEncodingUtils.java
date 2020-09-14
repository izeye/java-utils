/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izeye.util;

import java.io.UnsupportedEncodingException;
import java.net.IDN;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Utilities for URL encoding and decoding.
 *
 * @author Johnny Lim
 */
public abstract class UrlEncodingUtils {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private UrlEncodingUtils() {
	}

	public static String encode(String value) {
		try {
			return URLEncoder.encode(value, DEFAULT_ENCODING);
		}
		catch (UnsupportedEncodingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static String encodeIfNecessary(String value) {
		if (isEncoded(value)) {
			return value;
		}
		return encode(value);
	}

	public static String decode(String value) {
		try {
			return URLDecoder.decode(value, DEFAULT_ENCODING);
		}
		catch (UnsupportedEncodingException ex) {
			throw new IllegalArgumentException(ex);
		}
	}

	public static boolean isEncoded(String value) {
		return !decode(value).equals(value);
	}

	public static String punycode(String domain) {
		return IDN.toASCII(domain);
	}

	public static String encodeUrlComponentsIfNecessary(String url) {
		UrlComponentParser.UrlComponents components = UrlComponentParser.parse(url);

		String punycodedDomain = punycode(components.getDomain());

		String path = components.getPath();
		String encodedPath = "";
		for (String pathComponent : path.split("/")) {
			if (pathComponent.isEmpty()) {
				continue;
			}
			encodedPath += "/" + encodeIfNecessary(pathComponent);
		}

		String query = components.getQuery();
		String encodedQuery = Arrays.stream(query.split("&")).map((parameter) -> {
			String[] nameAndValue = parameter.split("=");
			return nameAndValue[0] + "=" + encodeIfNecessary(nameAndValue[1]);
		}).collect(Collectors.joining("&"));

		return components.getProtocol() + "://" + punycodedDomain + encodedPath + "?" + encodedQuery + "#"
				+ encodeIfNecessary(components.getFragment());
	}

}
