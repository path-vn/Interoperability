package com.globits.cbs.deidentification.service.fhir.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.cbs.deidentification.domain.DeIdentificationConfig;
import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;
import com.globits.cbs.deidentification.repository.DeIdentificationConfigRepository;
import com.globits.cbs.deidentification.service.DeIdentificationConfigService;
@Service
public class DeIdentificationConfigServiceImpl implements DeIdentificationConfigService{
    @Autowired
    EntityManager manager;
    @Autowired
    DeIdentificationConfigRepository repos;

	@Override
	public Page<DeIdentificationConfigDto> getPage(int pageSize, int pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);
        return repos.getListPage(pageable);
	}

	@Override
	public DeIdentificationConfigDto saveOrUpdate(UUID id, DeIdentificationConfigDto dto) {
		if (dto != null && dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
			DeIdentificationConfig entity = null;
            if (dto.getId() != null) {
                if (dto.getId() != null && !dto.getId().equals(id)) {
                    return null;
                }
                entity = repos.getOne(dto.getId());
                entity.setModifyDate(LocalDateTime.now());
            }
            if (entity == null) {
                entity = new DeIdentificationConfig();
                entity.setCreateDate(LocalDateTime.now());
                entity.setModifyDate(LocalDateTime.now());
            }
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setIsUsed(dto.getIsUsed());

            entity = repos.save(entity);
            if (entity != null) {
                return new DeIdentificationConfigDto(entity);
            }
        }
        return null;
	}

	@Override
	public Boolean delete(UUID id) {
		if (id != null) {
            repos.deleteById(id);
            return true;
        }
        return false;
	}
	@Override
	public Page<DeIdentificationConfigDto> searchByPage(SearchDto dto) {
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

        String orderBy = " ORDER BY entity.createDate DESC";

        String sqlCount = "select count(entity.id) from DeIdentificationConfig as entity where (1=1)   ";
        String sql = "select new com.globits.cbs.deidentification.dto.DeIdentificationConfigDto(entity) from DeIdentificationConfig as entity where (1=1)  ";

        if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
            whereClause += " AND ( entity.name LIKE :text OR entity.code LIKE :text )";
        }


        sql += whereClause + orderBy;
        sqlCount += whereClause;

        Query q = manager.createQuery(sql, DeIdentificationConfigDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
            q.setParameter("text", '%' + dto.getText() + '%');
            qCount.setParameter("text", '%' + dto.getText() + '%');
        }
        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<DeIdentificationConfigDto> entities = q.getResultList();
        long count = (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<DeIdentificationConfigDto> result = new PageImpl<DeIdentificationConfigDto>(entities, pageable, count);
        return result;
	}

	@Override
	public List<DeIdentificationConfigDto> getAll() {
		return repos.getAllConfig();
	}

	@Override
	public int deletes(List<DeIdentificationConfigDto> dtos) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DeIdentificationConfigDto getById(UUID id) {
		DeIdentificationConfig entity= repos.getOne(id);
		if(entity!=null)
			return new DeIdentificationConfigDto(entity);
		return null;
	}
	@Override
	public Boolean checkCode(UUID id, String code) {
        if (code != null && StringUtils.hasText(code)) {
            Long count = repos.checkCode(code, id);
            return count != 0l;
        }
        return null;
	}
}
