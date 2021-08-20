package com.globits.facility.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.facility.domain.FacilityAdministrativeUnit;
import com.globits.facility.dto.FacilityAdministrativeUnitDto;

@Repository
public interface FacilityAdministrativeUnitRepository extends JpaRepository<FacilityAdministrativeUnit, UUID> {
    @Query("select count(entity.id) FROM FacilityAdministrativeUnit entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);
    
    List<FacilityAdministrativeUnit> findListByCode(String code);

    @Query("select new com.globits.facility.dto.FacilityAdministrativeUnitDto(entity, false, 1) FROM FacilityAdministrativeUnit entity ")
	List<FacilityAdministrativeUnitDto> getListBasic();

}
