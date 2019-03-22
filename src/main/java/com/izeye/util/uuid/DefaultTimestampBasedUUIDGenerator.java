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

package com.izeye.util.uuid;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;

/**
 * Default {@link TimestampBasedUUIDGenerator}.
 *
 * @author Johnny Lim
 */
public class DefaultTimestampBasedUUIDGenerator implements TimestampBasedUUIDGenerator {

	private static final byte[] LOCAL_IP_ADDRESS;

	private static final int IP_ADDRESS_SIZE = 4;

	private static final int TIMESTAMP_SIZE = Long.BYTES;

	private static final int COUNTER_SIZE = Integer.BYTES;

	private static final int SIZE = IP_ADDRESS_SIZE + TIMESTAMP_SIZE + COUNTER_SIZE;

	static {
		try {
			LOCAL_IP_ADDRESS = InetAddress.getLocalHost().getAddress();
			if (LOCAL_IP_ADDRESS.length != IP_ADDRESS_SIZE) {
				throw new IllegalStateException(
						"Only support IPv4 addresses but was: " + LOCAL_IP_ADDRESS);
			}
		}
		catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
	}

	private final AtomicInteger counter = new AtomicInteger();

	@Override
	public String generate(long timestampInMillis) {
		byte[] uuid = new byte[SIZE];
		int uuidIndex = 0;
		System.arraycopy(LOCAL_IP_ADDRESS, 0, uuid, 0, LOCAL_IP_ADDRESS.length);
		uuidIndex += LOCAL_IP_ADDRESS.length;

		byte[] timestampBytes = Longs.toByteArray(timestampInMillis);
		System.arraycopy(timestampBytes, 0, uuid, uuidIndex, timestampBytes.length);
		uuidIndex += timestampBytes.length;

		int count = this.counter.getAndIncrement();
		byte[] countBytes = Ints.toByteArray(count);
		System.arraycopy(countBytes, 0, uuid, uuidIndex, countBytes.length);
		return Base64.getEncoder().encodeToString(uuid);
	}

}
