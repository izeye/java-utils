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

import java.util.concurrent.atomic.LongAdder;

/**
 * Default implementation for {@link ServiceMetricsCollector}.
 *
 * @author Johnny Lim
 */
public class DefaultServiceMetricsCollector implements ServiceMetricsCollector {

	private final int timeoutInMillis;

	private LongAdder requestCount = new LongAdder();

	private LongAdder timeoutCount = new LongAdder();

	private LongAdder accumulatedProcessTimeInMillis = new LongAdder();

	public DefaultServiceMetricsCollector(int timeoutInMillis) {
		this.timeoutInMillis = timeoutInMillis;
	}

	@Override
	public void collect(long processTimeInMillis) {
		this.requestCount.increment();
		if (processTimeInMillis > this.timeoutInMillis) {
			this.timeoutCount.increment();
		}
		this.accumulatedProcessTimeInMillis.add(processTimeInMillis);
	}

	@Override
	public ServiceMetrics getThenReset() {
		long requestCount = this.requestCount.sumThenReset();
		long timeoutCount = this.timeoutCount.sumThenReset();
		long accumulatedProcessTimeInMillis = this.accumulatedProcessTimeInMillis.sumThenReset();
		long averageProcessTimeInMillis = (requestCount == 0L) ? 0L : accumulatedProcessTimeInMillis / requestCount;
		return new ServiceMetrics(requestCount, timeoutCount, averageProcessTimeInMillis);
	}

}
