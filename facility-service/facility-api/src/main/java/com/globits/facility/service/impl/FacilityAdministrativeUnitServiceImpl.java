package com.globits.facility.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Query;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.facility.domain.FacilityAdministrativeUnit;
import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.repository.FacilityAdministrativeUnitRepository;
import com.globits.facility.search.FacilityAdministrativeUnitSearchDto;
import com.globits.facility.service.FacilityAdministrativeUnitService;
import com.globits.security.domain.User;

@Service
public class FacilityAdministrativeUnitServiceImpl extends GenericServiceImpl<FacilityAdministrativeUnit, UUID>
		implements FacilityAdministrativeUnitService {

	@Autowired
	private FacilityAdministrativeUnitRepository repos;

	@Override
	public Page<FacilityAdministrativeUnitDto> searchByDto(FacilityAdministrativeUnitSearchDto dto) {
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
		String sqlCount = "select count(entity.id) from FacilityAdministrativeUnit as entity where (1=1) ";
		String sql = "select new com.globits.facility.dto.FacilityAdministrativeUnitDto(entity) from FacilityAdministrativeUnit as entity where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (entity.name LIKE :text OR entity.code LIKE :text ) ";
		}
		if (dto.getParentId() != null) {
			whereClause += " AND (entity.parent.id=:parentId ) ";
		}
		if (dto.getIsGetAllCity() != null && dto.getIsGetAllCity()) {
			whereClause += " AND (entity.parent.id IS NULL ) ";
		}
		if (dto.getLevel() != null) {
			whereClause += " AND (entity.level = :level ) ";
		}
		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, FacilityAdministrativeUnitDto.class);
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
		List<FacilityAdministrativeUnitDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<FacilityAdministrativeUnitDto> result = new PageImpl<FacilityAdministrativeUnitDto>(entities, pageable,
				count);

		return result;
	}

	@Override
	public FacilityAdministrativeUnitDto saveOrUpdate(FacilityAdministrativeUnitDto dto, UUID id) {
		if (dto != null) {
			FacilityAdministrativeUnit entity = null;
			if (id != null) {
				entity = repos.findById(id).get();
			}
			if (id == null) {
				entity = new FacilityAdministrativeUnit();
			}
			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setLevel(dto.getLevel());
			entity.setMapCode(dto.getMapCode());
			if (dto.getParent() != null && dto.getParent().getId() != null) {
				FacilityAdministrativeUnit parent = repos.findById(dto.getParent().getId()).get();
				entity.setParent(parent);
			}
			entity = repos.save(entity);
			if (entity != null) {
				return new FacilityAdministrativeUnitDto(entity);
			}
		}
		return null;
	}

	@Override
	public FacilityAdministrativeUnitDto getById(UUID id) {
		FacilityAdministrativeUnit entity = repos.findById(id).get();
		if (entity != null) {
			return new FacilityAdministrativeUnitDto(entity);
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
	public Boolean checkCode(String code, UUID id) {
		if (code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public void saveOrUpdateList(List<FacilityAdministrativeUnitDto> listFmsAdministrativeUnit) {
		ArrayList<FacilityAdministrativeUnitDto> ret = new ArrayList<FacilityAdministrativeUnitDto>();
		for (int i = 0; i < listFmsAdministrativeUnit.size(); i++) {
			FacilityAdministrativeUnitDto dto = listFmsAdministrativeUnit.get(i);
			saveAdministrativeImport(dto, dto.getId());
		}
	}

	private void saveAdministrativeImport(FacilityAdministrativeUnitDto dto, UUID id) {

		FacilityAdministrativeUnit administrativeUnit = null;
		Boolean isEdit = false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User modifiedUser = null;
		LocalDateTime currentDate = LocalDateTime.now();
		String currentUserName = "Unknown User";
		if (authentication != null) {
			modifiedUser = (User) authentication.getPrincipal();
			currentUserName = modifiedUser.getUsername();
		}
		if (id != null) {// trường hợp edit
			isEdit = true;
			administrativeUnit = repos.findById(id).get();
		} else if (dto.getId() != null) {
			administrativeUnit = repos.findById(dto.getId()).get();
		} else if (dto.getCode() != null) {
			List<FacilityAdministrativeUnit> aus = repos.findListByCode(dto.getCode());
			if (aus != null && aus.size() == 1) {
				administrativeUnit = aus.get(0);
			} else if (aus != null && aus.size() > 1) {
				for (FacilityAdministrativeUnit item : aus) {
					if (item.getName().equals(dto.getName())) {
						administrativeUnit = item;
						break;
					}
				}
			}
		}

		if (administrativeUnit == null) {// trường hợp thêm mới
			administrativeUnit = new FacilityAdministrativeUnit();
			administrativeUnit.setCreateDate(currentDate);
			administrativeUnit.setCreatedBy(currentUserName);
		}

		if (dto.getName() != null)
			administrativeUnit.setName(dto.getName());

		if (dto.getCode() != null)
			administrativeUnit.setCode(dto.getCode());

		if (dto.getMapCode() != null)
			administrativeUnit.setMapCode(dto.getMapCode());

		if (dto.getParent() != null) {
			FacilityAdministrativeUnit parent = null;
			if (dto.getParent().getId() != null) {
				parent = repos.findById(dto.getParent().getId()).get();
			} else if (dto.getParent().getCode() != null) {
				List<FacilityAdministrativeUnit> aus = repos.findListByCode(dto.getParent().getCode());
				if (aus != null && aus.size() == 1) {
					parent = aus.get(0);
				} else if (aus != null && aus.size() > 1) {
					for (FacilityAdministrativeUnit item : aus) {
						if (item.getName().equals(dto.getParent().getName())) {
							parent = item;
							break;
						}
					}
				}
			}
			if (parent != null) {
				administrativeUnit.setParent(parent);
				if (parent.getLevel() != null && parent.getLevel() > 0) {
					administrativeUnit.setLevel(parent.getLevel() + 1);
				}
			}
		} else {
			administrativeUnit.setLevel(1); // level = 1 là cấp thành phố
			administrativeUnit.setParent(null);
		}

		repos.save(administrativeUnit);
		dto.setId(administrativeUnit.getId());
	}

	@Override
	public List<FacilityAdministrativeUnitDto> getListBasic() {
		List<FacilityAdministrativeUnitDto> results = repos.getListBasic();
		return results;
	}

}
