package com.globits.facility.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.facility.domain.FacilityAdministrativeUnit;
import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.search.FacilityAdministrativeUnitSearchDto;

public interface FacilityAdministrativeUnitService extends GenericService<FacilityAdministrativeUnit, UUID> {
    Page<FacilityAdministrativeUnitDto> searchByDto(FacilityAdministrativeUnitSearchDto searchDto);

    FacilityAdministrativeUnitDto saveOrUpdate(FacilityAdministrativeUnitDto dto, UUID id);

    FacilityAdministrativeUnitDto getById(UUID id);

    Boolean deleteById(UUID id);

    Boolean checkCode(String code, UUID id);
    
    void saveOrUpdateList(List<FacilityAdministrativeUnitDto> listFmsAdministrativeUnit);

	List<FacilityAdministrativeUnitDto> getListBasic();

}
