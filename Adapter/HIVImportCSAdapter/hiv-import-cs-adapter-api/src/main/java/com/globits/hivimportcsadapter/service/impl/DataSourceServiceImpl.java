package com.globits.hivimportcsadapter.service.impl;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.domain.DataSource;
import com.globits.hivimportcsadapter.dto.DataFileDto;
import com.globits.hivimportcsadapter.dto.DataSourceDto;
import com.globits.hivimportcsadapter.functiondto.SampleSearchDto;
import com.globits.hivimportcsadapter.repository.DataFileRepository;
import com.globits.hivimportcsadapter.repository.DataSourceRepository;
import com.globits.hivimportcsadapter.service.DataSourceService;

@Service
public class DataSourceServiceImpl extends GenericServiceImpl<DataSource, UUID> implements DataSourceService{
	@Autowired
	DataFileRepository dataFileService;
	@Autowired
	DataSourceRepository dataSourceRepository; 
	
	@Override
	public Page<DataSourceDto> searchByDto(SampleSearchDto dto) {
		if (dto == null) {
			return null;
		}
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();

		if (pageIndex > 0) {
			pageIndex--;
		} else {
			pageIndex = 0;
		}

		String whereClause = "";
		String orderBy = " ";
		String sqlCount = "select count(entity.id) from DataSource as entity where (1=1) ";
		String sql = "select new com.globits.hivimportcsadapter.dto.DataSourceDto(entity,true) from DataSource as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text ) ";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, DataSourceDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<DataSourceDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<DataSourceDto> result = new PageImpl<DataSourceDto>(entities, pageable, count);

		return result;
	}
	@Override
	public DataSourceDto saveOrUpdate(DataSourceDto dto, UUID id) {
		if (dto != null) {
			DataSource entity = null;
			if (id != null) {
				entity = dataSourceRepository.getOne(id);
			}
			if (entity == null) {
				entity = new DataSource();
			}
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setDescription(dto.getDescription());
			entity.setChannelUrl(dto.getChannelUrl());
			Set<DataFile> dataFiles = new HashSet<DataFile>();
			if (dto.getDataFiles() != null && dto.getDataFiles().size() > 0) {
				for (DataFileDto dataFileDto : dto.getDataFiles()) {
					if (dataFileDto != null) {
						DataFile dataFile = null;
						if (dataFileDto.getId() != null) {
							dataFile = dataFileService.getOne(dataFileDto.getId());
						}
						if (dataFile == null) {
							dataFile = new DataFile();
						}
						dataFile.setDataSource(entity);
						dataFile.setContentSize(dataFileDto.getContentSize());
						dataFile.setFileName(dataFileDto.getFileName());
						dataFile.setFilePath(dataFileDto.getFilePath());
						dataFiles.add(dataFile);
					}
				}
			}
			if (dataFiles != null && dataFiles.size() >0 ) {
				if(entity.getDataFiles() == null) {
					entity.setDataFiles(dataFiles);
				}else {
					entity.getDataFiles().clear();
					entity.getDataFiles().addAll(dataFiles);
				}
			}
			
			
			
			entity = dataSourceRepository.save(entity);
			if (entity != null) {
				return new DataSourceDto(entity,true);
			}
		}
		return null;
	}
	@Override
	public DataSourceDto getById(UUID id) {
		if (id != null) {
			DataSource entity = dataSourceRepository.getOne(id);
			if (entity != null) {
				return new DataSourceDto(entity,true);
			}
		}
		return null;
	}
	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			DataSource entity = dataSourceRepository.getOne(id);
			if (entity != null) {
				dataSourceRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}
	@Override
	public DataSourceDto getByCode(String code) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean checkCode(UUID id, String code) {
        if (code != null && StringUtils.hasText(code)) {
            Long count = dataSourceRepository.checkCode(code, id);
            return count != 0l;
        }
        return null;
	}
	@Override
	public Boolean checkUrl(UUID id, String url) {
		if (url != null && StringUtils.hasText(url)) {
            Long count = dataSourceRepository.checkUrl(url, id);
            return count != 0l;
        }
        return null;
	}

	

}
