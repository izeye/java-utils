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

import org.junit.Test;
import org.w3c.dom.Document;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link XmlUtils}.
 *
 * @author Johnny Lim
 */
public class XmlUtilsTests {

	@Test
	public void test() {
		String xml = "<Person><id>1</id><name><firstName>Johnny</firstName><lastName>Lim</lastName></name><age>20</age><favoriteFruits><favoriteFruits>apple</favoriteFruits><favoriteFruits>banana</favoriteFruits></favoriteFruits><createdTime>1472769728985</createdTime></Person>";
		Document document = XmlUtils.xml2Document(xml);
		assertThat(XmlUtils.document2Xml(document)).isEqualTo(xml);
	}

}
