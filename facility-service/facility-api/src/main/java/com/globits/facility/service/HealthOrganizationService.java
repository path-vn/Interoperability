package com.globits.facility.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.facility.domain.HealthOrganization;
import com.globits.facility.dto.HealthOrganizationDto;
import com.globits.facility.search.HealthOrganizationSearchDto;

public interface HealthOrganizationService extends GenericService<HealthOrganization, UUID> {

	Page<HealthOrganizationDto> searchByDto(HealthOrganizationSearchDto dto);

	HealthOrganizationDto saveOrUpdate(HealthOrganizationDto dto, UUID id);

	HealthOrganizationDto getById(UUID id);

	Boolean deleteById(UUID id);

}
