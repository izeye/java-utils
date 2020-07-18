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

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

/**
 * Tests for {@link ApacheReverseProxyUtils}.
 *
 * @author Johnny Lim
 */
class ApacheReverseProxyUtilsTests {

	@Test
	void getIpAddressInfoWhenNoXForwardedForReturnsItself() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(ApacheReverseProxyUtils.HEADER_X_FORWARDED_FOR)).thenReturn("client");
		ApacheReverseProxyUtils.IpAddressInfo ipAddressInfo = ApacheReverseProxyUtils.getIpAddressInfo(request);
		assertThat(ipAddressInfo.getRemoteIpAddress()).isEqualTo("client");
		assertThat(ipAddressInfo.getXForwardedFor()).isNull();
	}

	@Test
	void getIpAddressInfo() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(ApacheReverseProxyUtils.HEADER_X_FORWARDED_FOR)).thenReturn("client, proxy1, proxy2");
		ApacheReverseProxyUtils.IpAddressInfo ipAddressInfo = ApacheReverseProxyUtils.getIpAddressInfo(request);
		assertThat(ipAddressInfo.getRemoteIpAddress()).isEqualTo("proxy2");
		assertThat(ipAddressInfo.getXForwardedFor()).isEqualTo("client, proxy1");
	}

}
