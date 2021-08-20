package com.globits.adapter.pdma.utils;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class CustomLocalDateDeserializer extends JsonDeserializer<LocalDate>
{

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

		JsonNode tree = jp.getCodec().readTree(jp);
		int year = tree.get("year").asInt();
		int month = tree.get("monthValue").asInt();
		int dayOfMonth = tree.get("dayOfMonth").asInt();

		return LocalDate.of(year, month, dayOfMonth);
	}
}