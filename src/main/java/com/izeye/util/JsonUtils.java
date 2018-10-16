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

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utilities for JSON.
 *
 * @author Johnny Lim
 */
public final class JsonUtils {

	private static final Pattern PATTERN_DOUBLE_QUOTES = Pattern.compile("\"");

	/**
	 * Convert an object to JSON.
	 *
	 * This method supports only basic types as follows:
	 *
	 * <ul>
	 *   <li><code>Map</code></li>
	 *   <li><code>Collection</code></li>
	 *   <li><code>Object[]</code></li>
	 *   <li><code>Boolean</code></li>
	 *   <li><code>Number</code></li>
	 *   <li><code>String</code></li>
	 * </ul>
	 *
	 * @param o object to convert to JSON
	 * @return JSON
	 * @throws UnsupportedOperationException if the type of the object is an unlisted type.
	 */
	@SuppressWarnings("unchecked")
	public static String toJson(Object o) throws UnsupportedOperationException {
		if (o == null) {
			return "null";
		}

		if (o instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) o;
			return map.entrySet().stream()
					.map((entry) -> new StringBuilder().append('"').append(entry.getKey()).append("\":").append(toJson(entry.getValue())))
					.collect(Collectors.joining(",", "{", "}"));
		}

		if (o instanceof Collection) {
			return arrayToJson(((Collection<Object>) o).stream());
		}

		if (o instanceof Object[]) {
			return arrayToJson(Arrays.stream((Object[]) o));
		}

		if (o instanceof Boolean || o instanceof Number) {
			return o.toString();
		}

		if (o instanceof String) {
			String escaped = PATTERN_DOUBLE_QUOTES.matcher((String) o).replaceAll("\\\\\"");
			return new StringBuilder().append('"').append(escaped).append('"').toString();
		}

		throw new UnsupportedOperationException("Unsupported class: " + o.getClass());
	}

	private static String arrayToJson(Stream<Object> arrayStream) {
		return arrayStream
				.map((element) -> toJson(element))
				.collect(Collectors.joining(",", "[", "]"));
	}

	private JsonUtils() {
	}

}
