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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link HttpUtils}.
 *
 * @author Johnny Lim
 */
public class HttpUtilsTests {

	@Test
	public void testParseCookieHeaderValue() {
		String cookieHeaderValue = "a=b; c=d";
		Map<String, String> cookies = HttpUtils.parseCookieHeaderValue(cookieHeaderValue);
		assertThat(cookies.size()).isEqualTo(2);
		assertThat(cookies.get("a")).isEqualTo("b");
		assertThat(cookies.get("c")).isEqualTo("d");
	}

	@Test
	public void testExtractCookieValue() {
		String cookieHeaderValue = "a=b; c=d";
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "a")).isEqualTo("b");
		assertThat(HttpUtils.extractCookieValue(cookieHeaderValue, "c")).isEqualTo("d");
	}

}
