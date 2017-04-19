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
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Utilities for network.
 *
 * @author Johnny Lim
 */
public final class NetworkUtils {

	private NetworkUtils() {
	}

	public static String getMacAddress() {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
			byte[] hardwareAddress = networkInterface.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (byte b : hardwareAddress) {
				sb.append(String.format("%02x", b));
				sb.append(':');
			}
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString();
		}
		catch (UnknownHostException ex) {
			throw new RuntimeException(ex);
		}
		catch (SocketException ex) {
			throw new RuntimeException(ex);
		}
	}

}
