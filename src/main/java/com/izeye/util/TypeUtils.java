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
 * Utilities for types.
 *
 * @author Johnny Lim
 */
public abstract class TypeUtils {

	private TypeUtils() {
	}

	private static final int BYTE_IN_BIT = 8;

	public static String byte2ZeroLeadingBinaryString(byte b) {
		StringBuilder sb = new StringBuilder(BYTE_IN_BIT);
		for (int i = BYTE_IN_BIT - 1; i >= 0; i--) {
			sb.append((b >>> i) & 1);
		}
		return sb.toString();
	}

	public static String bytes2ZeroLeadingBinaryString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * BYTE_IN_BIT);
		for (byte b : bytes) {
			sb.append(byte2ZeroLeadingBinaryString(b));
		}
		return sb.toString();
	}

}
