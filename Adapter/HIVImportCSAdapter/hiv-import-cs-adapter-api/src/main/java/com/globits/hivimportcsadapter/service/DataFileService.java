package com.globits.hivimportcsadapter.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.dto.DataFileDto;
import com.globits.hivimportcsadapter.dto.DataSourceDto;
import com.globits.hivimportcsadapter.functiondto.DataFileSearchDto;
@Service
public interface DataFileService extends GenericService<DataFile, UUID>{
	Page<DataFileDto> searchByDto(DataFileSearchDto dto);
	DataFileDto saveOrUpdate(DataFileDto dto, UUID id);
	DataFileDto getById(UUID id);

	Boolean deleteById(UUID id);

}
