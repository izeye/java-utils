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

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utilities for security.
 *
 * @author Johnny Lim
 */
public final class SecurityUtils {

	private static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";

	/**
	 * Create an HMAC-SHA256 hex string.
	 * @param secretKey secret key
	 * @param message message
	 * @return an HMAC-SHA256 hex string
	 */
	public static String toHmacSha256HexString(byte[] secretKey, String message) {
		try {
			Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHA256);
			SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey,
					ALGORITHM_HMAC_SHA256);
			mac.init(secretKeySpec);
			byte[] bytes = mac.doFinal(message.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : bytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		}
		catch (NoSuchAlgorithmException | InvalidKeyException ex) {
			throw new RuntimeException(ex);
		}
	}

	private SecurityUtils() {
	}

}
