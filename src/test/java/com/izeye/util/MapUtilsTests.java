package com.izeye.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 11. 26..
 */
public class MapUtilsTests {
	
	@Test
	public void testGetValueByPath() {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> fullNameMap = new HashMap<>();
		fullNameMap.put("first_name", "Johnny");
		fullNameMap.put("last_name", "Lim");
		map.put("full_name", fullNameMap);
		map.put("age", 35);
		
		assertThat(MapUtils.getValueByPath(map, String.class, "full_name", "first_name"), is("Johnny"));
		assertThat(MapUtils.getValueByPath(map, String.class, "full_name", "last_name"), is("Lim"));
		assertThat(MapUtils.getValueByPath(map, Integer.class, "age"), is(35));
	}
	
}
