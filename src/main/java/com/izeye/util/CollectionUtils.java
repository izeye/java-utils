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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Utilities for collections.
 *
 * @author Johnny Lim
 */
public final class CollectionUtils {

	private CollectionUtils() {
	}

	@SafeVarargs
	public static <T> Set<T> asUnmodifiableSet(T... a) {
		return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(a)));
	}

	public static <T> Map<String, T> filterWithWhitelist(Map<String, T> map, Set<String> whitelist) {
		Map<String, T> filtered = new HashMap<>();
		for (Map.Entry<String, T> entry : map.entrySet()) {
			String key = entry.getKey();
			if (whitelist.contains(key)) {
				filtered.put(key, entry.getValue());
			}
		}
		return filtered;
	}

}
