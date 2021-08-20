package com.globits.hiv.receive.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.hiv.receive.dto.HivCaseReportDto;

@Service
public interface HivCaseReportService{

	public HivCaseReportDto saveOrUpdate(HivCaseReportDto dto);
	public List<HivCaseReportDto> saveOrUpdateList(List<HivCaseReportDto> dtos);
	public Page<HivCaseReportDto> getList(int pageIndex, int pageSize);
	public HivCaseReportDto getById(UUID id);
	
}