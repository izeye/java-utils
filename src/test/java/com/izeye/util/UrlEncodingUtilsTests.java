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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link UrlEncodingUtils}.
 *
 * @author Johnny Lim
 */
class UrlEncodingUtilsTests {

	@Test
	void test() {
		assertThat(UrlEncodingUtils.encode("=")).isEqualTo("%3D");
		assertThat(UrlEncodingUtils.decode("%3D")).isEqualTo("=");
		assertThat(UrlEncodingUtils.decode(UrlEncodingUtils.encode("테스트"))).isEqualTo("테스트");
	}

	@Test
	void isEncoded() {
		assertThat(UrlEncodingUtils.isEncoded("test")).isFalse();
		assertThat(UrlEncodingUtils.isEncoded("테스트")).isFalse();
		assertThat(UrlEncodingUtils.isEncoded(UrlEncodingUtils.encode("테스트"))).isTrue();
	}

	@Test
	void encodeIfNecessaryWhenNecessary() {
		String value = "테스트";
		assertThat(UrlEncodingUtils.encodeIfNecessary(value)).isEqualTo(UrlEncodingUtils.encode(value));
	}

	@Test
	void encodeIfNecessaryWhenUnnecessary() {
		String value = UrlEncodingUtils.encode("테스트");
		assertThat(UrlEncodingUtils.encodeIfNecessary(value)).isEqualTo(value);
	}

	@Test
	void punycode() {
		assertThat(UrlEncodingUtils.punycode("테스트")).isEqualTo("xn--9t4b11yi5a");
	}

	@Test
	void encodeUrlComponentsIfNecessaryWhenNecessary() {
		String url = "https://테스트/경로1/경로2?param1=값1&param2=값2#여기";
		String expected = "https://xn--9t4b11yi5a/%EA%B2%BD%EB%A1%9C1/%EA%B2%BD%EB%A1%9C2?param1=%EA%B0%921&param2=%EA%B0%922#%EC%97%AC%EA%B8%B0";
		assertThat(UrlEncodingUtils.encodeUrlComponentsIfNecessary(url)).isEqualTo(expected);
	}

	@Test
	void encodeUrlComponentsIfNecessaryWhenUnnecessary() {
		String url = "https://www.google.com/path1/path2?param1=1&param2=2#here";
		assertThat(UrlEncodingUtils.encodeUrlComponentsIfNecessary(url)).isEqualTo(url);
	}

}
