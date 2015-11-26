package com.izeye.util;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by izeye on 15. 11. 26..
 */
public abstract class MapUtils {
	
	public static <T> T getValueByPath(Map<String, Object> map, Class<T> returnType, String... path) {
		String key = path[0];
		if (path.length == 1) {
			return returnType.cast(map.get(key));
		}
		return getValueByPath(
				(Map<String, Object>) map.get(key), returnType, Arrays.copyOfRange(path, 1, path.length));
	}
	
}
