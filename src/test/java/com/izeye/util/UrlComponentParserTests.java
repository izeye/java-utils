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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link UrlComponentParser}.
 *
 * @author Johnny Lim
 */
class UrlComponentParserTests {

	@Test
	void parseWhenUrlIsInvalid() {
		assertThatThrownBy(() -> UrlComponentParser.parse("url")).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	void parse() {
		String url = "https://www.google.com/path1/path2?param1=1&param2=2#fragment";
		UrlComponentParser.UrlComponents components = UrlComponentParser.parse(url);
		assertThat(components.getProtocol()).isEqualTo("https");
		assertThat(components.getDomain()).isEqualTo("www.google.com");
		assertThat(components.getPath()).isEqualTo("/path1/path2");
		assertThat(components.getQuery()).isEqualTo("param1=1&param2=2");
		assertThat(components.getFragment()).isEqualTo("fragment");
	}

}
