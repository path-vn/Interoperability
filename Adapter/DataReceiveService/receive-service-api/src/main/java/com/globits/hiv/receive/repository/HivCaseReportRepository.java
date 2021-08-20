package com.globits.hiv.receive.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.hiv.receive.domain.HivCaseReport;
import com.globits.hiv.receive.dto.HivCaseReportDto;


@Repository
public interface HivCaseReportRepository extends JpaRepository<HivCaseReport, UUID>{
	@Query(" SELECT new com.globits.hiv.receive.dto.HivCaseReportDto(e) FROM HivCaseReport e WHERE e.systemId = ?1")
	Page<HivCaseReportDto> getListPage(String systemId,PageRequest page);
}
