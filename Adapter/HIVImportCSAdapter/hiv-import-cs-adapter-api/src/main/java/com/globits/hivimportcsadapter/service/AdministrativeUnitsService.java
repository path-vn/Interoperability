package com.globits.hivimportcsadapter.service;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.AdministrativeUnitDto;
import com.globits.core.service.GenericService;
import com.globits.hivimportcsadapter.functiondto.AdministrativeUnitSearchDto;

public interface AdministrativeUnitsService extends GenericService<AdministrativeUnit, UUID> {

	Page<AdministrativeUnitDto> searchByDto(AdministrativeUnitSearchDto dto);

}
