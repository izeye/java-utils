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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Utilities for {@link Map}.
 *
 * @author Johnny Lim
 */
public abstract class MapUtils {

	private MapUtils() {
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValueByPath(Map<String, Object> map, Class<T> returnType,
			String... path) {
		String key = path[0];
		if (path.length == 1) {
			return returnType.cast(map.get(key));
		}
		return getValueByPath((Map<String, Object>) map.get(key), returnType,
				Arrays.copyOfRange(path, 1, path.length));
	}

	public static <K, V> void putValueIntoList(Map<K, List<V>> map, K key, V value) {
		List<V> values = map.get(key);
		if (values == null) {
			values = new ArrayList<>();
			map.put(key, values);
		}
		values.add(value);
	}

}
