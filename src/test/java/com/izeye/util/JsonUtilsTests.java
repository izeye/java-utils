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
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Tests for {@link JsonUtils}.
 *
 * @author Johnny Lim
 */
public class JsonUtilsTests {

	@Test
	public void toJsonWhenCustomClassShouldThrowException() {
		assertThatThrownBy(() -> JsonUtils.toJson(new Person("Johnny", "Lim")))
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void toJsonStringBuilderWhenCustomClassShouldThrowException() {
		assertThatThrownBy(() -> JsonUtils.toJsonStringBuilder(new Person("Johnny", "Lim")))
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void toJsonWhenPrimitiveArrayShouldThrowException() {
		assertThatThrownBy(() -> JsonUtils.toJson(new int[0]))
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void toJsonStringBuilderWhenPrimitiveArrayShouldThrowException() {
		assertThatThrownBy(() -> JsonUtils.toJsonStringBuilder(new int[0]))
				.isInstanceOf(UnsupportedOperationException.class);
	}

	@Test
	public void toJsonWhenEmptyMap() {
		assertAgainstJackson(Collections.emptyMap());
	}

	@Test
	public void toJsonStringBuilderWhenEmptyMap() {
		assertJsonStringBuilderAgainstJackson(Collections.emptyMap());
	}

	@Test
	public void toJsonWhenMap() {
		assertAgainstJackson(createTestMap());
	}

	@Test
	public void toJsonStringBuilderWhenMap() {
		assertJsonStringBuilderAgainstJackson(createTestMap());
	}

	@Test
	public void toJsonWhenMapHasNestedMapAndList() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("boolean", Boolean.TRUE);
		map.put("nestedMap", createTestMap());
		map.put("nestedList", createTestList());
		assertAgainstJackson(map);
	}

	@Test
	public void toJsonWhenEmptyList() {
		assertAgainstJackson(Collections.emptyList());
	}

	@Test
	public void toJsonWhenList() {
		assertAgainstJackson(createTestList());
	}

	@Test
	public void toJsonWhenListHasNestedMapAndList() {
		List<Object> list = new ArrayList<>();
		list.add(Boolean.TRUE);
		list.add(createTestMap());
		list.add(createTestList());
		assertAgainstJackson(list);
	}

	@Test
	public void toJsonWhenEmptyArray() {
		assertAgainstJackson(new Object[0]);
	}

	@Test
	public void toJsonWhenArray() {
		assertAgainstJackson(createTestArray());
	}

	@Test
	public void toJsonWhenArrayHasNestedMapListAndArray() {
		Object[] array = new Object[4];
		array[0] = Boolean.TRUE;
		array[1] = createTestMap();
		array[2] = createTestList();
		array[3] = createTestArray();
		assertAgainstJackson(array);
	}

	private Map<String, Object> createTestMap() {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("null", null);
		map.put("boolean", Boolean.TRUE);
		map.put("integer", Integer.valueOf(1));
		map.put("long", Long.valueOf(1L));
		map.put("float", Float.valueOf(3.5f));
		map.put("double", Double.valueOf(4.5d));
		map.put("string", "Hello, world!");
		// TODO: Intentionally ignore for StringBuilder version.
//		map.put("string", "I said, \"Hello, world!\".");
		return map;
	}

	private List<Object> createTestList() {
		List<Object> list = new ArrayList<>();
		list.add(null);
		list.add(Boolean.TRUE);
		list.add(Integer.valueOf(1));
		list.add(Long.valueOf(1L));
		list.add(Float.valueOf(3.5f));
		list.add(Double.valueOf(4.5d));
		list.add("I said, \"Hello, world!\".");
		return list;
	}

	private Object[] createTestArray() {
		Object[] array = new Object[7];
		array[0] = null;
		array[1] = Boolean.TRUE;
		array[2] = Integer.valueOf(1);
		array[3] = Long.valueOf(1L);
		array[4] = Float.valueOf(3.5f);
		array[5] = Double.valueOf(4.5d);
		array[6] = "I said, \"Hello, world!\".";
		return array;
	}

	private void assertAgainstJackson(Object o) {
		assertThat(JsonUtils.toJson(o)).isEqualTo(JacksonJsonUtils.toJson(o));
	}

	private void assertJsonStringBuilderAgainstJackson(Object o) {
		assertThat(JsonUtils.toJsonStringBuilder(o)).isEqualTo(JacksonJsonUtils.toJson(o));
	}

	private static class Person {

		private final String firstName;
		private final String lastName;

		Person(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public String getFirstName() {
			return this.firstName;
		}

		public String getLastName() {
			return this.lastName;
		}

	}

}
