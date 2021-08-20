package com.globits.hivimportcsadapter.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.core.service.GenericService;
import com.globits.hivimportcsadapter.domain.DataSource;
import com.globits.hivimportcsadapter.dto.DataSourceDto;
import com.globits.hivimportcsadapter.functiondto.SampleSearchDto;
@Service
public interface DataSourceService extends GenericService<DataSource, UUID>{
	Page<DataSourceDto> searchByDto(SampleSearchDto dto);

	DataSourceDto saveOrUpdate(DataSourceDto dto, UUID id);

	DataSourceDto getById(UUID id);

	Boolean deleteById(UUID id);

	DataSourceDto getByCode(String code);
	
	Boolean checkCode (UUID id,String code);
	
	Boolean checkUrl (UUID id,String url);
}
