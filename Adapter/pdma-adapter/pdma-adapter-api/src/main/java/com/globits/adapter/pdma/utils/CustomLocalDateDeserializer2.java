package com.globits.adapter.pdma.utils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateDeserializer2 extends StdDeserializer<LocalDate>
{

	private static final long serialVersionUID = 1416868826088071110L;

	public CustomLocalDateDeserializer2() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		String s = p.getValueAsString();

		if (CommonUtils.isEmpty(s)) {
			return null;
		}

		if (s.endsWith("Z")) {
			s = s.substring(0, s.length() - 1);
		}

		return LocalDateTime.parse(s).toLocalDate();
	}
}
