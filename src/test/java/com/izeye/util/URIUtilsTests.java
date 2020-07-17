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

import java.net.URL;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link URIUtils}.
 *
 * @author Johnny Lim
 */
class URIUtilsTests {

	@Test
	void toURL() {
		URL url = URIUtils.toURL("https://www.google.com/");
		assertThat(url.getProtocol()).isEqualTo("https");
		assertThat(url.getHost()).isEqualTo("www.google.com");
		assertThat(url.getPort()).isEqualTo(-1);
		assertThat(url.getDefaultPort()).isEqualTo(443);
		assertThat(url.getPath()).isEqualTo("/");
	}

}
