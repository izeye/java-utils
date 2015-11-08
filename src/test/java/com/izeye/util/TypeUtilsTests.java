package com.izeye.util;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 11. 8..
 */
public class TypeUtilsTests {
	
	@Test
	public void testByte2ZeroLeadingBinaryString() {
		byte b = 1;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b), is("00000001"));
		
		b = 2;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b), is("00000010"));

		b = 3;
		assertThat(TypeUtils.byte2ZeroLeadingBinaryString(b), is("00000011"));
	}

	@Test
	public void testBytes2ZeroLeadingBinaryString() {
		String expected = "00000001" + "00000010" + "00000011";
		
		byte[] bytes = {1, 2, 3};
		assertThat(TypeUtils.bytes2ZeroLeadingBinaryString(bytes), is(expected));
	}
	
}
