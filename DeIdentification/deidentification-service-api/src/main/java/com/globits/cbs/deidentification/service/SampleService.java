package com.globits.cbs.deidentification.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.cbs.deidentification.domain.Sample;
import com.globits.cbs.deidentification.dto.SampleDto;
import com.globits.cbs.deidentification.functiondto.SampleSearchDto;

public interface SampleService extends GenericService<Sample, UUID> {

	Page<SampleDto> searchByDto(SampleSearchDto dto);

	SampleDto saveOrUpdate(SampleDto dto, UUID id);

	SampleDto getById(UUID id);

	Boolean deleteById(UUID id);

	SampleDto getByCode(String code);

}
