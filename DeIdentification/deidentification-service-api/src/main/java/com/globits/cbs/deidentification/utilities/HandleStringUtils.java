package com.globits.cbs.deidentification.utilities;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class HandleStringUtils {

	public String handleString(String stringCellValue) {
		String string = stringCellValue.trim();

		// string = toProperCase(string);
		string = replaceSpecialCharacters(string);

		return string;
	}

	private String replaceSpecialCharacters(String str) {
		String pattern = "[,|/]";
		return str.replaceAll(pattern, ".");
	}

	// private String toProperCase(String str) {
	// str = str.toLowerCase();
	// str = StringUtils.capitalize(str);
	// return str;
	// }

	public String convertToString(String str) {
		return StringUtils.defaultString(str).trim();
	}
}
