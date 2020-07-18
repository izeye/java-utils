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

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Stopwatch}.
 *
 * @author Johnny Lim
 */
class StopwatchTests {

	@Test
	void test() throws InterruptedException {
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		TimeUnit.MILLISECONDS.sleep(100);
		stopwatch.stop();
		System.out.println(stopwatch.getElapsedTimeInMillis());
	}

}
