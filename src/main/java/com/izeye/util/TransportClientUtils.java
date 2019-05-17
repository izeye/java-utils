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

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Utilities for Elasticsearch {@link TransportClient}.
 *
 * @author Johnny Lim
 */
public final class TransportClientUtils {

	/**
	 * Elasticsearch default native port.
	 */
	public static final int NATIVE_PORT = 9300;

	private static final String CLUSTER_NAME = "cluster.name";

	private static final String CLIENT_TRANSPORT_SNIFF = "client.transport.sniff";

	public static String search(String clusterName, String hostname, int port,
			String indexName, String query) {
		Client client = createClient(clusterName, hostname, port);
		SearchRequestBuilder requestBuilder = client.prepareSearch(indexName)
				.setQuery(query);
		SearchResponse response = requestBuilder.execute().actionGet();
		return response.toString();
	}

	private static Client createClient(String clusterName, String hostname, int port) {
		TransportClient client = TransportClient.builder().settings(settings(clusterName))
				.build();
		try {
			client.addTransportAddress(new InetSocketTransportAddress(
					InetAddress.getByName(hostname), port));
			return client;
		}
		catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}

	private static Settings settings(String clusterName) {
		return Settings.settingsBuilder().put(CLUSTER_NAME, clusterName)
				.put(CLIENT_TRANSPORT_SNIFF, true).build();
	}

	private TransportClientUtils() {
	}

}
