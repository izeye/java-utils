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

import lombok.Data;

/**
 * Utilities for {@link String}.
 *
 * @author Johnny Lim
 */
public abstract class StringUtils {

	private StringUtils() {
	}

	public static String replaceField(String target, FieldDescription targetFieldDescription, String replacement) {
		int beforeDelimiterIndex = 0;
		int afterDelimiterIndex = 0;
		for (FieldDescription fd = targetFieldDescription; fd != null; fd = fd.getSubFieldDescription()) {
			char delimiter = fd.getDelimiter();
			for (int i = 0; i < fd.getIndex(); i++) {
				beforeDelimiterIndex = target.indexOf(delimiter, beforeDelimiterIndex + 1);
			}
			afterDelimiterIndex = target.indexOf(delimiter, beforeDelimiterIndex + 1);
		}
		return target.substring(0, beforeDelimiterIndex + 1) + replacement + target.substring(afterDelimiterIndex);
	}

	public static String getFirstNotEmptyValue(String... values) {
		for (String value : values) {
			if (value != null && !value.isEmpty()) {
				return value;
			}
		}
		return null;
	}

	/**
	 * Field description.
	 */
	@Data
	public static class FieldDescription {

		private char delimiter;

		private int index;

		private FieldDescription subFieldDescription;

	}

}
