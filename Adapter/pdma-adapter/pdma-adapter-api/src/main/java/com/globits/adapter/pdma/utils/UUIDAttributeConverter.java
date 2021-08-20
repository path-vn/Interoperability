package com.globits.adapter.pdma.utils;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UUIDAttributeConverter implements AttributeConverter<UUID, String>
{

	@Override
	public String convertToDatabaseColumn(final UUID entityValue) {
		return Optional.ofNullable(entityValue).map(entityUuid -> entityUuid.toString()).orElse(null);
	}

	@Override
	public UUID convertToEntityAttribute(final String databaseValue) {
		return Optional.ofNullable(databaseValue).map(databaseUuid -> UUID.fromString(databaseUuid)).orElse(null);
	}
}