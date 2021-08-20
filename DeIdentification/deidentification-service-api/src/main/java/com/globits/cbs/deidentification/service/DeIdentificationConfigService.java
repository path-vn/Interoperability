package com.globits.cbs.deidentification.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;

@Service
public interface DeIdentificationConfigService {
	public Page<DeIdentificationConfigDto> getPage(int pageSize, int pageIndex);
	public List<DeIdentificationConfigDto> getAll();
	public DeIdentificationConfigDto saveOrUpdate(UUID id,DeIdentificationConfigDto dto);
	public Boolean delete(UUID id);
	public DeIdentificationConfigDto getById(UUID id);
	public int deletes(List<DeIdentificationConfigDto> dtos);
	Page<DeIdentificationConfigDto> searchByPage(SearchDto dto);
	public Boolean checkCode(UUID id, String code);
}
