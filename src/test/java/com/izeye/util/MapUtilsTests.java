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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link MapUtils}.
 *
 * @author Johnny Lim
 */
class MapUtilsTests {

	@Test
	void testGetValueByPath() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> fullNameMap = new HashMap<>();
		fullNameMap.put("first_name", "Johnny");
		fullNameMap.put("last_name", "Lim");
		map.put("full_name", fullNameMap);
		map.put("age", 35);

		assertThat(MapUtils.getValueByPath(map, String.class, "full_name", "first_name"))
				.isEqualTo("Johnny");
		assertThat(MapUtils.getValueByPath(map, String.class, "full_name", "last_name"))
				.isEqualTo("Lim");
		assertThat(MapUtils.getValueByPath(map, Integer.class, "age")).isEqualTo(35);
	}

	@Test
	void testPutValueIntoList() {
		Map<String, List<Integer>> map = new HashMap<>();

		MapUtils.putValueIntoList(map, "a", 1);
		assertThat(map.get("a")).containsExactly(1);

		MapUtils.putValueIntoList(map, "a", 2);
		assertThat(map.get("a")).containsExactly(1, 2);
	}

}
