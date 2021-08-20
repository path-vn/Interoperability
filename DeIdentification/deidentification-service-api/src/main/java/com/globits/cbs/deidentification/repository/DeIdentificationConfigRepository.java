package com.globits.cbs.deidentification.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.globits.cbs.deidentification.domain.DeIdentificationConfig;
import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;

public interface DeIdentificationConfigRepository extends JpaRepository<DeIdentificationConfig, UUID>{
	@Query("select count(entity.id) from DeIdentificationConfig entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, UUID id);
	@Query("select new com.globits.cbs.deidentification.dto.DeIdentificationConfigDto(ed) from DeIdentificationConfig ed")
	Page<DeIdentificationConfigDto> getListPage( Pageable pageable);
	@Query("select new com.globits.cbs.deidentification.dto.DeIdentificationConfigDto(ed) from DeIdentificationConfig ed")
	List<DeIdentificationConfigDto> getAllConfig();
}
