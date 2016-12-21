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

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;
import static org.mockito.BDDMockito.when;

/**
 * Tests for {@link ApacheReverseProxyUtils}.
 *
 * @author Johnny Lim
 */
public class ApacheReverseProxyUtilsTests {

	@Test
	public void getIpAddressInfoWhenNoXForwardedForReturnsItself() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(ApacheReverseProxyUtils.HEADER_X_FORWARDED_FOR))
				.thenReturn("1.2.3.4");
		ApacheReverseProxyUtils.IpAddressInfo ipAddressInfo =
				ApacheReverseProxyUtils.getIpAddressInfo(request);
		assertThat(ipAddressInfo.getRemoteIpAddress()).isEqualTo("1.2.3.4");
		assertThat(ipAddressInfo.getXForwardedFor()).isNull();
	}

	@Test
	public void getIpAddressInfo() {
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getHeader(ApacheReverseProxyUtils.HEADER_X_FORWARDED_FOR))
				.thenReturn("1.2.3.4,5.6.7.8,9.10.11.12");
		ApacheReverseProxyUtils.IpAddressInfo ipAddressInfo =
				ApacheReverseProxyUtils.getIpAddressInfo(request);
		assertThat(ipAddressInfo.getRemoteIpAddress()).isEqualTo("1.2.3.4");
		assertThat(ipAddressInfo.getXForwardedFor()).isEqualTo("5.6.7.8,9.10.11.12");
	}

}
