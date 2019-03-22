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

import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link ElasticsearchUtils}.
 *
 * @author Johnny Lim
 */
public class ElasticsearchUtilsTests {

	// To prepare this test, do as follows:
	// curl -XPOST localhost:9200/persons/persons?pretty -d '{firstName: "Johnny",
	// lastName: "Lim", age: 20}'
	// curl -XPOST localhost:9200/persons/persons?pretty -d '{firstName: "John", lastName:
	// "Kim", age: 30}'
	@Ignore
	@Test
	public void testSearch() {
		String clusterName = "elasticsearch";
		String hostname = "localhost";
		String indexName = "persons";
		int port = ElasticsearchUtils.NATIVE_PORT;

		String query = "{query: {match_all: {}}}";
		String response = ElasticsearchUtils.search(clusterName, hostname, port,
				indexName, query);
		System.out.println(response);

		query = "{query: {term: {age: 20}}}";
		response = ElasticsearchUtils.search(clusterName, hostname, port, indexName,
				query);
		System.out.println(response);

		query = "{query: {match: {firstName: \"Johnny\"}}}";
		response = ElasticsearchUtils.search(clusterName, hostname, port, indexName,
				query);
		System.out.println(response);
	}

}
