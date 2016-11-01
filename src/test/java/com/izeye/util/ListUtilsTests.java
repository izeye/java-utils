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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Tests for {@link ListUtils}.
 *
 * @author Johnny Lim
 */
public class ListUtilsTests {

	@Test
	public void testSelectByHashCode() throws InterruptedException {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		for (int i = 0; i < 10; i++) {
			Integer selected = ListUtils.selectByHashCode(list, i);
			System.out.println(selected);
		}
	}

}
