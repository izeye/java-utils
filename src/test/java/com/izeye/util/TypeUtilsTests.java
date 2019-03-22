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

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TypeUtils}.
 *
 * @author Johnny Lim
 */
public class TypeUtilsTests {

	@Test
	public void testByte2ZeroLeadingBinaryString() {
		byte b = 1;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b)).isEqualTo("00000001");

		b = 2;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b)).isEqualTo("00000010");

		b = 3;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b)).isEqualTo("00000011");
	}

	@Test
	public void testBytes2ZeroLeadingBinaryString() {
		String expected = "00000001" + "00000010" + "00000011";

		byte[] bytes = { 1, 2, 3 };
		assertThat(TypeUtils.bytes2ZeroLeadingBinaryString(bytes)).isEqualTo(expected);
	}

	@Test
	public void testParseInt() {
		assertThat(TypeUtils.parseInt(null, 1)).isEqualTo(1);
		assertThat(TypeUtils.parseInt("2", 1)).isEqualTo(2);
	}

}
