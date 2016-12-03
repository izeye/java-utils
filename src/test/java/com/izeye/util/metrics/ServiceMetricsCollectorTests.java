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

package com.izeye.util.metrics;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ServiceMetricsCollector}.
 *
 * @author Johnny Lim
 */
public class ServiceMetricsCollectorTests {

	@Test
	public void test() {
		int timeoutInMillis = 100;
		ServiceMetricsCollector serviceMetricsCollector =
				new DefaultServiceMetricsCollector(timeoutInMillis);

		serviceMetricsCollector.collect(50);
		serviceMetricsCollector.collect(99);
		serviceMetricsCollector.collect(100);
		serviceMetricsCollector.collect(101);
		serviceMetricsCollector.collect(1000);
		ServiceMetrics serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(5);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(2);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis())
				.isEqualTo((50 + 99 + 100 + 101 + 1000) / 5);

		serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(0);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(0);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis()).isEqualTo(0);

		serviceMetricsCollector.collect(50);
		serviceMetricsCollector.collect(99);
		serviceMetricsCollector.collect(100);
		serviceMetricsCollector.collect(101);
		serviceMetricsCollector.collect(1000);
		serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(5);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(2);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis())
				.isEqualTo((50 + 99 + 100 + 101 + 1000) / 5);
	}

}
