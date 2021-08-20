package com.globits.adapter.opcassist.utils;

import org.apache.commons.lang3.StringUtils;

public class Base36Utils {

	private static final String CODE_BASE_36 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final Character ZERO = '0';

	/**
	 * Conversion d'un nombre en base 36.
	 *
	 * @param numToConvert Le nombre à convertir
	 * @return Le nombre passé en paramètre converti en base 36
	 */
	public static String toBase36(Long numToConvert) {
		if (numToConvert < 0) {
			throw new NumberFormatException(
					"La valeur du nombre en entrée '" + numToConvert + "' est inférieur à zéro !");
		}
		Long num = numToConvert;
		String text = StringUtils.EMPTY;
		int j = (int) Math.ceil(Math.log(num) / Math.log(CODE_BASE_36.length()));
		for (int i = 0; i < (j > 0 ? j : 1); i++) {
			text = CODE_BASE_36.charAt(Integer.parseInt(String.valueOf(num % CODE_BASE_36.length()))) + text;
			num /= CODE_BASE_36.length();
		}
		return text;
	}

	/**
	 * Conversion d'un nombre en base 36.
	 *
	 * @param numToConvert Le nombre à convertir
	 * @param length       La longueur du résultat final
	 * @return Le nombre passé en paramètre converti en base 36 sur x caractères (x
	 *         correspondant à length, le 2eme paramètre)
	 */
	public static String toBase36(Long numToConvert, int length) {
		return StringUtils.leftPad(toBase36(numToConvert), length, ZERO);
	}

}
