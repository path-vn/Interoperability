package com.globits.hiv.receive.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globits.hiv.receive.domain.HivCaseReport;
import com.globits.hiv.receive.domain.UserSystemCode;
import com.globits.hiv.receive.dto.HivCaseReportDto;
import com.globits.hiv.receive.dto.UserSystemCodeDto;


@Repository
public interface UserSystemCodeRepository extends JpaRepository<UserSystemCode, UUID>{
	@Query(" SELECT new com.globits.hiv.receive.dto.UserSystemCodeDto(e) FROM UserSystemCode e where e.user.id=?1 ")
	public UserSystemCodeDto getDtoByUser(Long userId);
}
