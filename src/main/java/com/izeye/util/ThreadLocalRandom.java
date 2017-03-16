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

import java.util.Random;

/**
 * Alternative for Java 8 {@code ThreadLocalRandom}.
 *
 * @author Johnny Lim
 */
public final class ThreadLocalRandom {

	private static final ThreadLocal<Random> RANDOM = new ThreadLocal<Random>() {
		@Override
		protected Random initialValue() {
			return new Random();
		}
	};

	private ThreadLocalRandom() {
	}

	public static Random current() {
		return RANDOM.get();
	}

}
