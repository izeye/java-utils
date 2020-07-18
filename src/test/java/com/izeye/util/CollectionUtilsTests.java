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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link CollectionUtils}.
 *
 * @author Johnny Lim
 */
class CollectionUtilsTests {

	@Test
	void testAsUnmodifiableSet() {
		Set<String> set = CollectionUtils.asUnmodifiableSet("aa", "bb");
		assertThat(set).containsExactlyInAnyOrder("bb", "aa");
	}

	@Test
	void testFilterWithWhitelist() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Johnny");
		map.put("age", 20);

		Set<String> whitelist = Collections.singleton("name");

		Map<String, Object> filtered = CollectionUtils.filterWithWhitelist(map, whitelist);

		assertThat(filtered).containsEntry("name", "Johnny");
	}

	@Test
	void testFilterWithWhitelistMapStringString() {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Johnny");
		map.put("age", "20");

		Set<String> whitelist = Collections.singleton("name");

		Map<String, String> filtered = CollectionUtils.filterWithWhitelist(map, whitelist);

		assertThat(filtered).containsEntry("name", "Johnny");
	}

}
