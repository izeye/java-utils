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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UrlUtils}.
 *
 * @author Johnny Lim
 */
class UrlUtilsTests {

	@Test
	void testHasProtocol() {
		assertThat(UrlUtils.hasProtocol("www.google.com")).isFalse();
		assertThat(UrlUtils.hasProtocol("http://www.google.com")).isTrue();
		assertThat(UrlUtils.hasProtocol("https://www.google.com")).isTrue();
	}

	@Test
	void testPrependHttpProtocolIfAbsent() {
		assertThat(UrlUtils.prependHttpProtocolIfAbsent("www.google.com"))
				.isEqualTo("http://www.google.com");
		assertThat(UrlUtils.prependHttpProtocolIfAbsent("http://www.google.com"))
				.isEqualTo("http://www.google.com");
		assertThat(UrlUtils.prependHttpProtocolIfAbsent("https://www.google.com"))
				.isEqualTo("https://www.google.com");
	}

	@Test
	void testAddParameter() {
		assertThat(UrlUtils.addParameter("http://www.google.com", "name", "value"))
				.isEqualTo("http://www.google.com?name=value");
		assertThat(UrlUtils.addParameter("http://www.google.com/#ref", "name", "value"))
				.isEqualTo("http://www.google.com/?name=value#ref");
		assertThat(UrlUtils.addParameter("http://www.google.com?type=1", "name", "value"))
				.isEqualTo("http://www.google.com?type=1&name=value");
		assertThat(UrlUtils.addParameter("http://www.google.com?type=1#ref", "name",
				"value")).isEqualTo("http://www.google.com?type=1&name=value#ref");
	}

	@Test
	void printComponents() {
		String url = "https://www.google.com/path1/path2?parameter1=value1&parameter2=%EC%95%88%EB%85%95#myAnchor";
		UrlUtils.printComponents(url);
	}

}
