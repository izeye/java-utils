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

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HttpUtils}.
 *
 * @author Johnny Lim
 */
class HttpUtilsTests {

	@Test
	void testParseCookieHeaderValue() {
		String cookieHeaderValue = "a=b; c=d; e=; f=g==";
		Map<String, String> cookies = HttpUtils.parseCookieHeaderValue(cookieHeaderValue);
		assertThat(cookies.size()).isEqualTo(4);
		assertThat(cookies.get("a")).isEqualTo("b");
		assertThat(cookies.get("c")).isEqualTo("d");
		assertThat(cookies.get("e")).isEmpty();
		assertThat(cookies.get("f")).isEqualTo("g==");
	}

	@Test
	void testExtractCookieValue() {
		String cookieHeaderValue = "a=b; c=d; e=; f=g==";
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "a")).isEqualTo("b");
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "c")).isEqualTo("d");
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "e")).isEmpty();
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "f")).isEqualTo("g==");
	}

	@Test
	void testParseQueryString() {
		assertThat(HttpUtils.parseQueryString("a=1")).hasSize(1).containsEntry("a", "1");
		assertThat(HttpUtils.parseQueryString("a=1&b=2")).hasSize(2).containsEntry("a", "1").containsEntry("b", "2");
		assertThat(HttpUtils.parseQueryString("a=1&b=")).hasSize(1).containsEntry("a", "1");
		assertThat(HttpUtils.parseQueryString("a=1&b=2&c=")).hasSize(2).containsEntry("a", "1").containsEntry("b", "2");
		assertThat(HttpUtils.parseQueryString("a=1%3A2&b=2&c=")).hasSize(2).containsEntry("a", "1:2").containsEntry("b",
				"2");
	}

	@Test
	void testBuildQueryString() {
		assertThat(HttpUtils.buildQueryString(Collections.singletonMap("a", "1"))).isEqualTo("a=1");

		Map<String, Object> parameters = new LinkedHashMap<>();
		parameters.put("a", "1:2");
		parameters.put("b", 2);
		assertThat(HttpUtils.buildQueryString(parameters)).isEqualTo("a=1%3A2&b=2");
	}

}
