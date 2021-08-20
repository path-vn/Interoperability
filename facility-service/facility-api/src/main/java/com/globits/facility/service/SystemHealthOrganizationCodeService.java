package com.globits.facility.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.facility.domain.SystemHealthOrganizationCode;
import com.globits.facility.dto.SystemHealthOrganizationCodeDto;
import com.globits.facility.search.SystemHealthOrganizationCodeSearchDto;

public interface SystemHealthOrganizationCodeService extends GenericService<SystemHealthOrganizationCode, UUID> {

	Page<SystemHealthOrganizationCodeDto> searchByDto(SystemHealthOrganizationCodeSearchDto dto);

	SystemHealthOrganizationCodeDto getById(UUID id);

	SystemHealthOrganizationCodeDto saveOrUpdate(SystemHealthOrganizationCodeDto dto, UUID id);

	Boolean deleteById(UUID fromString);

}
