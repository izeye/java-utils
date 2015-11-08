package com.izeye.util;

/**
 * Created by izeye on 15. 11. 8..
 */
public abstract class TypeUtils {
	
	private static final int BYTE_IN_BIT = 8;
	
	public static String byte2ZeroLeadingBinaryString(byte b) {
		StringBuilder sb = new StringBuilder(BYTE_IN_BIT);
		for (int i = BYTE_IN_BIT - 1; i >= 0; i--) {
			sb.append((b >>> i) & 1);
		}
		return sb.toString();
	}

	public static String bytes2ZeroLeadingBinaryString(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * BYTE_IN_BIT);
		for (byte b : bytes) {
			sb.append(byte2ZeroLeadingBinaryString(b));
		}
		return sb.toString();
	}
	
}
