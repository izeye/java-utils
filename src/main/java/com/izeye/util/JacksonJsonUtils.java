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

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;

/**
 * Utilities for JSON.
 *
 * @author Johnny Lim
 */
public final class JacksonJsonUtils {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final ObjectMapper LEXICOGRAPHICAL_MAPPER = new ObjectMapper();

	static {
		LEXICOGRAPHICAL_MAPPER.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
	}

	private JacksonJsonUtils() {
	}

	public static String toJson(Object object) {
		return toJson(object, MAPPER);
	}

	public static String toJsonLexicographically(Object object) {
		return toJson(object, LEXICOGRAPHICAL_MAPPER);
	}

	private static String toJson(Object object, ObjectMapper mapper) {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, object);
			return writer.toString();
		}
		catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public static <T> T readPath(String json, String path, Class<T> clazz) {
		try {
			return JsonPath.parse(json).read(path, clazz);
		}
		catch (PathNotFoundException ex) {
			return null;
		}
	}

}
