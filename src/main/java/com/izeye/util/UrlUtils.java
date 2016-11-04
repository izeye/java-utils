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

/**
 * Utilities for URLs.
 *
 * @author Johnny Lim
 */
public abstract class UrlUtils {

	private static final String PROTOCOL_DELIMITER = "://";
	private static final String HTTP_PROTOCOL = "http";

	private UrlUtils() {
	}

	public static boolean hasProtocol(String url) {
		return url.contains(PROTOCOL_DELIMITER);
	}

	public static String prependHttpProtocolIfAbsent(String url) {
		if (hasProtocol(url)) {
			return url;
		}
		return HTTP_PROTOCOL + PROTOCOL_DELIMITER + url;
	}

}
