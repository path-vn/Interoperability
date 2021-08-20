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
import com.globits.facility.domain.SystemHealthOrganizationCode;
import com.globits.facility.dto.SystemHealthOrganizationCodeDto;
import com.globits.facility.repository.HealthOrganizationRepository;
import com.globits.facility.repository.SystemHealthOrganizationCodeRepository;
import com.globits.facility.search.SystemHealthOrganizationCodeSearchDto;
import com.globits.facility.service.SystemHealthOrganizationCodeService;
import com.globits.facility.utilities.FacilityEnums;

@Service
public class SystemHealthOrganizationCodeServiceImpl extends GenericServiceImpl<SystemHealthOrganizationCode, UUID>
	implements SystemHealthOrganizationCodeService {

	@Autowired
	private SystemHealthOrganizationCodeRepository repos;

	@Autowired
	private HealthOrganizationRepository healthOrganizationRepository;

	@Override
	public Page<SystemHealthOrganizationCodeDto> searchByDto(SystemHealthOrganizationCodeSearchDto dto) {
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
		String sqlCount = "select count(entity.id) from SystemHealthOrganizationCode as entity where (1=1) ";
		String sql = "select new com.globits.facility.dto.SystemHealthOrganizationCodeDto(entity) from SystemHealthOrganizationCode as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text ) ";
		}
		if (dto.getLevel() != null) {
			whereClause += " AND (entity.level = :level ) ";
		}
		if (dto.getHealthOrgId() != null) {
			whereClause += "AND ((entity.healthOrganization.id)) = :healthOrgId";
		}
		if (dto.getSystemType() != null) {
			whereClause += "AND (entity.systemType) = :systemType";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, SystemHealthOrganizationCodeDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		if (dto.getLevel() != null) {
			q.setParameter("level", dto.getLevel());
			qCount.setParameter("level", dto.getLevel());
		}
		if (dto.getHealthOrgId() != null) {
			q.setParameter("healthOrgId", dto.getHealthOrgId());
			qCount.setParameter("healthOrgId", dto.getHealthOrgId());
		}
		if (dto.getSystemType() != null) {
			q.setParameter("systemType", FacilityEnums.SystemType.get(dto.getSystemType()));
			qCount.setParameter("systemType", FacilityEnums.SystemType.get(dto.getSystemType()));
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<SystemHealthOrganizationCodeDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<SystemHealthOrganizationCodeDto> result = new PageImpl<SystemHealthOrganizationCodeDto>(entities, pageable,
				count);

		return result;
	}

	@Override
	public SystemHealthOrganizationCodeDto getById(UUID id) {
		SystemHealthOrganizationCode entity = repos.findById(id).get();
		if (entity != null) {
			return new SystemHealthOrganizationCodeDto(entity);
		}
		return null;
	}

	@Override
	public SystemHealthOrganizationCodeDto saveOrUpdate(SystemHealthOrganizationCodeDto dto, UUID id) {
		if (dto != null && dto.getSystemType() != null) {
			SystemHealthOrganizationCode entity = null;
			if (id != null) {
				entity = repos.getOne(id);		
				}
			if (id == null) {
				entity = new SystemHealthOrganizationCode();
			}
			entity.setSystemType(FacilityEnums.SystemType.get(dto.getSystemType()));
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setLevel(dto.getLevel());
			HealthOrganization healthOrganization = null;
			if (dto.getHealthOrganization() != null && dto.getHealthOrganization().getId() != null) {
				healthOrganization = healthOrganizationRepository.findById(dto.getHealthOrganization().getId()).get();
			}
			if (healthOrganization == null || healthOrganization.getId() == null) {
				return null;
			}
			entity.setHealthOrganization(healthOrganization);
			entity = repos.save(entity);
			if (entity != null) {
				return new SystemHealthOrganizationCodeDto(entity);
			}
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
