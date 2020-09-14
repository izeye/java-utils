/*
 * Copyright 2020 the original author or authors.
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Data;

/**
 * Parser for URL components.
 *
 * @author Johnny Lim
 */
public final class UrlComponentParser {

	private static final Pattern PATTERN = Pattern.compile(
			"(?<protocol>[A-Za-z]+)://(?<domain>[^/?#]+)(?<path>/[^?#]+)?(\\?(?<query>[^#]+))?(#(?<fragment>.+))?");

	public static UrlComponents parse(String url) {
		Matcher matcher = PATTERN.matcher(url);
		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid URL: " + url);
		}
		UrlComponents components = new UrlComponents();
		components.setProtocol(matcher.group("protocol"));
		components.setDomain(matcher.group("domain"));
		components.setPath(matcher.group("path"));
		components.setQuery(matcher.group("query"));
		components.setFragment(matcher.group("fragment"));
		return components;
	}

	private UrlComponentParser() {
	}

	@Data
	public static class UrlComponents {

		private String protocol;

		private String domain;

		private String path;

		private String query;

		private String fragment;

	}

}
