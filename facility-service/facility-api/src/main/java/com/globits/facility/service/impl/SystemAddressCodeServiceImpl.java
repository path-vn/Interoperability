package com.globits.facility.service.impl;

import java.util.ArrayList;
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
import com.globits.facility.domain.FacilityAdministrativeUnit;
import com.globits.facility.domain.HealthOrganization;
import com.globits.facility.domain.SystemAddressCode;
import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.dto.HealthOrganizationDto;
import com.globits.facility.dto.SystemAddressCodeDto;
import com.globits.facility.repository.FacilityAdministrativeUnitRepository;
import com.globits.facility.repository.SystemAddressCodeRepository;
import com.globits.facility.search.SystemAddressCodeSearchDto;
import com.globits.facility.search.SystemSearchDto;
import com.globits.facility.service.SystemAddressCodeService;
import com.globits.facility.utilities.FacilityEnums;

@Service
public class SystemAddressCodeServiceImpl extends GenericServiceImpl<SystemAddressCode, UUID>
		implements SystemAddressCodeService {

	@Autowired
	private SystemAddressCodeRepository repos;

	@Autowired
	private FacilityAdministrativeUnitRepository facilityAdministrativeUnitRepository;

	@Override
	public Page<SystemAddressCodeDto> searchByDto(SystemAddressCodeSearchDto dto) {
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
		String sqlCount = "select count(entity.id) from SystemAddressCode as entity where (1=1) ";
		String sql = "select new com.globits.facility.dto.SystemAddressCodeDto(entity) from SystemAddressCode as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text ) ";
		}
		if (dto.getLevel() != null) {
			whereClause += " AND (entity.level = :level ) ";
		}
		if (dto.getAdministrativeUnitId() != null) {
			whereClause += "AND ((entity.administrativeUnit.id)) = :administrativeUnitId";
		}
		if (dto.getSystemType() != null) {
			whereClause += "AND (entity.systemType) = :systemType";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, SystemAddressCodeDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		if (dto.getLevel() != null) {
			q.setParameter("level", dto.getLevel());
			qCount.setParameter("level", dto.getLevel());
		}
		if (dto.getAdministrativeUnitId() != null) {
			q.setParameter("administrativeUnitId", dto.getAdministrativeUnitId());
			qCount.setParameter("administrativeUnitId", dto.getAdministrativeUnitId());
		}
		if (dto.getSystemType() != null) {
			q.setParameter("systemType", FacilityEnums.SystemType.get(dto.getSystemType()));
			qCount.setParameter("systemType", FacilityEnums.SystemType.get(dto.getSystemType()));
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<SystemAddressCodeDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<SystemAddressCodeDto> result = new PageImpl<SystemAddressCodeDto>(entities, pageable,
				count);

		return result;
	}

	@Override
	public SystemAddressCodeDto getById(UUID id) {
		SystemAddressCode entity = repos.findById(id).get();
		if (entity != null) {
			return new SystemAddressCodeDto(entity);
		}
		return null;
	}

	@Override
	public SystemAddressCodeDto saveOrUpdate(SystemAddressCodeDto dto, UUID id) {
		if (dto != null && dto.getSystemType() != null) {
			SystemAddressCode entity = null;
			if (id != null) {
				entity = repos.getOne(id);		
				}
			if (id == null) {
				entity = new SystemAddressCode();
			}
			entity.setSystemType(FacilityEnums.SystemType.get(dto.getSystemType()));
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setLevel(dto.getLevel());
			FacilityAdministrativeUnit administrativeUnit = null;
			if (dto.getAdministrativeUnit() != null && dto.getAdministrativeUnit().getId() != null) {
				administrativeUnit = facilityAdministrativeUnitRepository.findById(dto.getAdministrativeUnit().getId()).get();
			}
			if (administrativeUnit == null || administrativeUnit.getId() == null) {
				return null;
			}
			entity.setAdministrativeUnit(administrativeUnit);
			entity = repos.save(entity);
			if (entity != null) {
				return new SystemAddressCodeDto(entity);
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

	@Override
	public void saveOrUpdateList(List<SystemAddressCodeDto> listFmsAdministrativeUnit) {
		ArrayList<SystemAddressCodeDto> ret = new ArrayList<SystemAddressCodeDto>();
		for (int i = 0; i < listFmsAdministrativeUnit.size(); i++) {
			SystemAddressCodeDto dto = listFmsAdministrativeUnit.get(i);
			saveImport(dto,null);
		}
	}

	@Override
	public SystemAddressCodeDto saveImport(SystemAddressCodeDto dto, UUID id) {
		if (dto != null && dto.getSystemType() != null) {
			SystemAddressCode entity = null;
			if (id != null) {
				entity = repos.getOne(id);		
				}
			if (id == null) {
				entity = new SystemAddressCode();
			}
			entity.setSystemType(FacilityEnums.SystemType.get(dto.getSystemType()));
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setLevel(dto.getLevel());
			FacilityAdministrativeUnit administrativeUnit = null;
			if (dto.getAdministrativeUnit() != null && dto.getAdministrativeUnit().getId() != null) {
				administrativeUnit = facilityAdministrativeUnitRepository.findById(dto.getAdministrativeUnit().getId()).get();
			}
			if (administrativeUnit != null) {
				entity.setAdministrativeUnit(administrativeUnit);
			}
			
			entity = repos.save(entity);
			if (entity != null) {
				return new SystemAddressCodeDto(entity);
			}
		}
		return null;
	}

	@Override
	public SystemAddressCodeDto getAddess(SystemSearchDto dto) {
		if (dto == null) {
			return null;
		}
		
		String whereClause = "";
		String orderBy = " ";
		String sqlCount = "select count(entity.id) from SystemAddressCode as entity where (1=1) ";
		String sql = "select new com.globits.facility.dto.SystemAddressCodeDto(entity) from SystemAddressCode as entity where (1=1) ";


		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			whereClause += " AND (entity.name = :name ) ";
		}
		if (dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
			whereClause += "AND (entity.code = :code ) ";
		}
		if (dto.getSystem() != null) {
			whereClause += "AND (entity.systemType) = :systemType";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, SystemAddressCodeDto.class);

		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			q.setParameter("name", dto.getName());
		}
		if (dto.getCode() != null && StringUtils.hasText(dto.getCode())) {
			q.setParameter("code", dto.getCode());
		}
		if (dto.getSystem() != null) {
			q.setParameter("systemType", dto.getSystem());
		}
		List<SystemAddressCodeDto> entities = q.getResultList();
		if(entities != null &&  entities.size()>0) {
			return entities.get(0);
		}
		return null;
	}

}
