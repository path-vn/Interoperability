package com.globits.fhir.hiv.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class CoreUtils {
	public static String encode(String input) {
		String output = Base64.getMimeEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
		return output;
	}
	
	public static String decode(String input) {
		String output = new String (Base64.getMimeDecoder().decode(input),StandardCharsets.UTF_8);
		return output;
	}
}
