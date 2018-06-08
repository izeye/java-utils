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

package com.izeye.util.http;

/**
 * HTTP headers.
 *
 * @author Johnny Lim
 */
public final class HttpHeader {

	/**
	 * For {@literal Accept}.
	 */
	public static final String ACCEPT = "Accept";

	/**
	 * For {@literal Authorization}.
	 */
	public static final String AUTHORIZATION = "Authorization";

	/**
	 * For {@literal Content-Encoding}.
	 */
	public static final String CONTENT_ENCODING = "Content-Encoding";

	/**
	 * For {@literal Content-Type}.
	 */
	public static final String CONTENT_TYPE = "Content-Type";

	private HttpHeader() {
	}

}
