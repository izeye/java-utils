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

package com.izeye.util.metrics;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link ServiceMetricsCollector}.
 *
 * @author Johnny Lim
 */
class ServiceMetricsCollectorTests {

	@Test
	void test() {
		int timeoutInMillis = 100;
		ServiceMetricsCollector serviceMetricsCollector = new DefaultServiceMetricsCollector(timeoutInMillis);

		serviceMetricsCollector.collect(50L);
		serviceMetricsCollector.collect(99L);
		serviceMetricsCollector.collect(100L);
		serviceMetricsCollector.collect(101L);
		serviceMetricsCollector.collect(1000L);
		ServiceMetrics serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(5L);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(2L);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis()).isEqualTo((50L + 99L + 100L + 101L + 1000L) / 5L);

		serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(0L);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(0L);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis()).isEqualTo(0L);

		serviceMetricsCollector.collect(50L);
		serviceMetricsCollector.collect(99L);
		serviceMetricsCollector.collect(100L);
		serviceMetricsCollector.collect(101L);
		serviceMetricsCollector.collect(1000L);
		serviceMetrics = serviceMetricsCollector.getThenReset();
		System.out.println(serviceMetrics);
		System.out.println(serviceMetrics.getTimeoutRatioInPercent());
		assertThat(serviceMetrics.getRequestCount()).isEqualTo(5L);
		assertThat(serviceMetrics.getTimeoutCount()).isEqualTo(2L);
		assertThat(serviceMetrics.getAverageProcessTimeInMillis()).isEqualTo((50L + 99L + 100L + 101L + 1000L) / 5L);
	}

}
