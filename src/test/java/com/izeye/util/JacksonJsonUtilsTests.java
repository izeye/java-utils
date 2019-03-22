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
import java.util.Map;
import java.util.TreeMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link JacksonJsonUtils}.
 *
 * @author Johnny Lim
 */
public class JacksonJsonUtilsTests {

	@Test
	public void testToJson() {
		Person person = new Person("Johnny", 20);

		String json = JacksonJsonUtils.toJson(person);

		assertThat(json).isEqualTo("{\"name\":\"Johnny\",\"age\":20}");
	}

	@Test
	public void testToJsonWithMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Johnny");
		map.put("age", 20);

		String json = JacksonJsonUtils.toJson(map);

		assertThat(json).isEqualTo("{\"name\":\"Johnny\",\"age\":20}");
	}

	@Test
	public void testToJsonWithTreeMap() {
		Map<String, Object> map = new TreeMap<>();
		map.put("name", "Johnny");
		map.put("age", 20);

		String json = JacksonJsonUtils.toJson(map);

		assertThat(json).isEqualTo("{\"age\":20,\"name\":\"Johnny\"}");
	}

	@Test
	public void testToJsonLexicographicallyWithMap() {
		Map<String, Object> map = new HashMap<>();
		map.put("name", "Johnny");
		map.put("age", 20);

		String json = JacksonJsonUtils.toJsonLexicographically(map);

		assertThat(json).isEqualTo("{\"age\":20,\"name\":\"Johnny\"}");
	}

	@Test
	public void testReadPath() {
		String json = "{\"a1\":{\"b1\":{\"c1\":\"a1b1c1\"},\"b2\":[{\"c1\":\"a1b2c1\"},{\"c2\":\"a1b2c2\"},{\"c3\":\"a1b2c3\"}]}}";
		String path = "a1.b1.c1";
		assertThat(JacksonJsonUtils.readPath(json, path, String.class))
				.isEqualTo("a1b1c1");
	}

	@Test
	public void testReadPathMissingLeaf() {
		String json = "{\"a1\":{\"b1\":{\"c1\":\"a1b1c1\"},\"b2\":[{\"c1\":\"a1b2c1\"},{\"c2\":\"a1b2c2\"},{\"c3\":\"a1b2c3\"}]}}";
		String path = "a1.b1.c2";
		assertThat(JacksonJsonUtils.readPath(json, path, String.class)).isNull();
	}

	@Test
	public void testReadPathMissingIntermediate() {
		String json = "{\"a1\":{\"b1\":{\"c1\":\"a1b1c1\"},\"b2\":[{\"c1\":\"a1b2c1\"},{\"c2\":\"a1b2c2\"},{\"c3\":\"a1b2c3\"}]}}";
		String path = "a2.b1.c1";
		assertThat(JacksonJsonUtils.readPath(json, path, String.class)).isNull();
	}

	@Data
	@AllArgsConstructor
	private static class Person {

		private String name;

		private int age;

	}

}
