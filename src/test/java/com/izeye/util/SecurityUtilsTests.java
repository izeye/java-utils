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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SecurityUtils}.
 *
 * @author Johnny Lim
 */
class SecurityUtilsTests {

	@Test
	void toHmacSha256HexString() throws UnsupportedEncodingException {
		byte[] secretKey = "secret".getBytes("UTF-8");
		assertThat(SecurityUtils.toHmacSha256HexString(secretKey, "Hello, world!"))
				.isEqualTo("62419bf2fe15b171049ba48b1d5d90d7420421e88b8d6b2e54d8b7a4974d9447");
	}

}
