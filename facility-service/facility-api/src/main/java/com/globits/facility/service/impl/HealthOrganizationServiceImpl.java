package com.globits.facility.service.impl;

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
import com.globits.facility.domain.HealthOrganization;
import com.globits.facility.dto.HealthOrganizationDto;
import com.globits.facility.repository.HealthOrganizationRepository;
import com.globits.facility.search.HealthOrganizationSearchDto;
import com.globits.facility.service.HealthOrganizationService;

@Service
public class HealthOrganizationServiceImpl extends GenericServiceImpl<HealthOrganization, UUID>
		implements HealthOrganizationService {

	@Autowired
	private HealthOrganizationRepository repos;

	@Override
	public Page<HealthOrganizationDto> searchByDto(HealthOrganizationSearchDto dto) {
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
		String sqlCount = "select count(entity.id) from HealthOrganization as entity where (1=1) ";
		String sql = "select new com.globits.facility.dto.HealthOrganizationDto(entity) from HealthOrganization as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text ) ";
		}
		if (dto.getParentId() != null) {
			whereClause += " AND (entity.parent.id=:parentId ) ";
		}
		if (dto.getLevel() != null) {
			whereClause += " AND (entity.level = :level ) ";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, HealthOrganizationDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		if (dto.getParentId() != null) {
			q.setParameter("parentId", dto.getParentId());
			qCount.setParameter("parentId", dto.getParentId());
		}
		if (dto.getLevel() != null) {
			q.setParameter("level", dto.getLevel());
			qCount.setParameter("level", dto.getLevel());
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<HealthOrganizationDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<HealthOrganizationDto> result = new PageImpl<HealthOrganizationDto>(entities, pageable,
				count);

		return result;
	}

	@Override
	public HealthOrganizationDto saveOrUpdate(HealthOrganizationDto dto, UUID id) {
		if (dto != null) {
			HealthOrganization entity = null;
			if (id != null) {
				entity = repos.findById(id).get();
			}
			if (id == null) {
				entity = new HealthOrganization();
			}
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setLevel(dto.getLevel());
			if (dto.getParent() != null && dto.getParent().getId() != null) {
				HealthOrganization parent = repos.findById(dto.getParent().getId()).get();
				entity.setParent(parent);
			}
			entity = repos.save(entity);
			if (entity != null) {
				return new HealthOrganizationDto(entity);
			}
		}
		return null;
	}

	@Override
	public HealthOrganizationDto getById(UUID id) {
		HealthOrganization entity = repos.findById(id).get();
		if (entity != null) {
			return new HealthOrganizationDto(entity);
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

}
