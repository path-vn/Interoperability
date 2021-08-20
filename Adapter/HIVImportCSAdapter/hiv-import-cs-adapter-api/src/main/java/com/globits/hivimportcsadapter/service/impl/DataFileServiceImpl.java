package com.globits.hivimportcsadapter.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.hivimportcsadapter.domain.DataFile;
import com.globits.hivimportcsadapter.domain.DataSource;
import com.globits.hivimportcsadapter.dto.DataFileDto;
import com.globits.hivimportcsadapter.functiondto.DataFileSearchDto;
import com.globits.hivimportcsadapter.repository.DataFileRepository;
import com.globits.hivimportcsadapter.repository.DataSourceRepository;
import com.globits.hivimportcsadapter.service.DataFileService;

@Service
public class DataFileServiceImpl  extends GenericServiceImpl<DataFile, UUID> implements DataFileService {
	@Autowired
	private DataFileRepository fileRepository;
	@Autowired
	private DataSourceRepository dataSourceRepository;
	@Override
	public Page<DataFileDto> searchByDto(DataFileSearchDto dto) {
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
		String sqlCount = "select count(entity.id) from DataFile as entity where (1=1) ";
		String sql = "select new com.globits.hivimportcsadapter.dto.DataFileDto(entity,true) from DataFile as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text " + "OR entity.code LIKE :text  ";
		}
		if (dto.getDataSourceId() != null ) {
			whereClause += " AND (entity.dataSource.id  =:dataSourceId ) " ;
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, DataFileDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		if (dto.getDataSourceId() != null ) {
			q.setParameter("dataSourceId", dto.getDataSourceId());
			qCount.setParameter("dataSourceId", dto.getDataSourceId());
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<DataFileDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<DataFileDto> result = new PageImpl<DataFileDto>(entities, pageable, count);

		return result;
	}

	@Override
	public DataFileDto saveOrUpdate(DataFileDto dto, UUID id) {
		if (dto != null) {
			DataFile entity = null;
			if (id != null) {
				entity = fileRepository.getOne(id);
			}
			if (entity == null) {
				entity = new DataFile();
			}
			DataSource dataSource= null;
			entity.setContentSize(dto.getContentSize());
			entity.setFileName(dto.getFileName());
			entity.setFilePath(dto.getFilePath());
			entity.setContentByte(dto.getContentByte());
			if(dto.getDataSource() != null && dto.getDataSource().getId() != null) {
				dataSource = dataSourceRepository.getOne(dto.getDataSource().getId());
			}
//			entity.setDataSource(dataSource);
			entity = fileRepository.save(entity);
			if (entity != null) {
				return new DataFileDto(entity,true);
			}
		}
		return null;
	}

	@Override
	public DataFileDto getById(UUID id) {
		if (id != null) {
			DataFile entity = fileRepository.getOne(id);
			if (entity != null) {
				return new DataFileDto(entity,true);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			DataFile entity = fileRepository.getOne(id);
			if (entity != null) {
				fileRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

}
