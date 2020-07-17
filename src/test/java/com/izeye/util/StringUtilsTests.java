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

import com.izeye.util.StringUtils.FieldDescription;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link StringUtils}.
 *
 * @author Johnny Lim
 */
class StringUtilsTests {

	@Test
	void testReplaceField() {
		String expected = "1,2,3,4\t5,6,100,8\t9,10,11,12";
		String target = "1,2,3,4\t5,6,7,8\t9,10,11,12";
		String replacement = "100";

		FieldDescription targetFieldDescription = new FieldDescription();
		targetFieldDescription.setDelimiter('\t');
		targetFieldDescription.setIndex(1);
		FieldDescription subFieldDescription = new FieldDescription();
		subFieldDescription.setDelimiter(',');
		subFieldDescription.setIndex(2);
		targetFieldDescription.setSubFieldDescription(subFieldDescription);

		String replaced = StringUtils.replaceField(target, targetFieldDescription,
				replacement);
		assertThat(replaced).isEqualTo(expected);
	}

	@Test
	void testGetFirstNotEmptyValue() {
		assertThat(StringUtils.getFirstNotEmptyValue(null, null, null)).isNull();
		assertThat(StringUtils.getFirstNotEmptyValue("1", null, null)).isEqualTo("1");
		assertThat(StringUtils.getFirstNotEmptyValue(null, "2", null)).isEqualTo("2");
		assertThat(StringUtils.getFirstNotEmptyValue(null, null, "3")).isEqualTo("3");
		assertThat(StringUtils.getFirstNotEmptyValue("1", "2", "3")).isEqualTo("1");
	}

}
