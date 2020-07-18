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
 * Tests for {@link NumberUtils}.
 *
 * @author Johnny Lim
 */
class NumberUtilsTests {

	@Test
	void testFormat() {
		assertThat(NumberUtils.format(100)).isEqualTo("100");
		assertThat(NumberUtils.format(1000)).isEqualTo("1,000");
		assertThat(NumberUtils.format(10000)).isEqualTo("10,000");
		assertThat(NumberUtils.format(100000)).isEqualTo("100,000");
		assertThat(NumberUtils.format(1000000)).isEqualTo("1,000,000");
	}

}
