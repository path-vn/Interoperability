package com.globits.adapter.opcassist.utils;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.sun.jersey.core.util.Base64;

@Converter
public class CryptoConverter implements AttributeConverter<String, String>
{

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final byte[] KEY = "CfAH9tj7auENqqHk".getBytes();

	@Override
	public String convertToDatabaseColumn(String sensitive) {

		if (CommonUtils.isEmpty(sensitive)) {
			return null;
		}

		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.ENCRYPT_MODE, key);
			final String encrypted = new String(Base64.encode(c.doFinal(sensitive.getBytes())), "UTF-8");
			return encrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String sensitive) {

		if (CommonUtils.isEmpty(sensitive)) {
			return null;
		}

		Key key = new SecretKeySpec(KEY, "AES");
		try {
			final Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			final String decrypted = new String(c.doFinal(Base64.decode(sensitive.getBytes("UTF-8"))));
			return decrypted;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}