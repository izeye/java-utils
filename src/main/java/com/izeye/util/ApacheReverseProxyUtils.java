/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.izeye.util;

import javax.servlet.http.HttpServletRequest;

import lombok.Data;

/**
 * Utilities for Apache reverse proxy.
 *
 * @author Johnny Lim
 */
public abstract class ApacheReverseProxyUtils {

	/**
	 * Header name for X-Forwarded-For.
	 */
	public static final String HEADER_X_FORWARDED_FOR = "X-Forwarded-For";

	private static final String DELIMITER_X_FORWARDED_FOR = ", ";

	private ApacheReverseProxyUtils() {
	}

	public static IpAddressInfo getIpAddressInfo(HttpServletRequest request) {
		IpAddressInfo ipAddressInfo = new IpAddressInfo();
		String xForwardedFor = request.getHeader(HEADER_X_FORWARDED_FOR);
		int index = xForwardedFor.lastIndexOf(DELIMITER_X_FORWARDED_FOR);
		if (index != -1) {
			String originalXForwardedFor = xForwardedFor.substring(0, index);
			String remoteIpAddress = xForwardedFor.substring(index + 2);
			ipAddressInfo.setRemoteIpAddress(remoteIpAddress);
			ipAddressInfo.setXForwardedFor(originalXForwardedFor);
		}
		else {
			ipAddressInfo.setRemoteIpAddress(xForwardedFor);
		}
		return ipAddressInfo;
	}

	/**
	 * Information for remote IP address and X-Forwarded-For header.
	 */
	@Data
	public static class IpAddressInfo {

		private String remoteIpAddress;

		private String xForwardedFor;

	}

}
