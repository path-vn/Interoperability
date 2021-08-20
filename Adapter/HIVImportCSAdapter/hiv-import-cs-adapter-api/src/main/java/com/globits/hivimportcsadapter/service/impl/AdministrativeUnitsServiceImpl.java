package com.globits.hivimportcsadapter.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.globits.core.domain.AdministrativeUnit;
import com.globits.core.dto.AdministrativeUnitDto;
import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.hivimportcsadapter.functiondto.AdministrativeUnitSearchDto;
import com.globits.hivimportcsadapter.service.AdministrativeUnitsService;

@Service
@Transactional
public class AdministrativeUnitsServiceImpl extends GenericServiceImpl<AdministrativeUnit, UUID>
		implements AdministrativeUnitsService {

	@Override
	public Page<AdministrativeUnitDto> searchByDto(AdministrativeUnitSearchDto dto) {
		if (dto == null)
			return null;

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();

		if (pageIndex > 0)
			pageIndex--;
		else
			pageIndex = 0;

		String hql = "SELECT new com.globits.core.dto.AdministrativeUnitDto(entity) FROM AdministrativeUnit entity";
		String where = " WHERE (1=1)";
		String orderBy = " ORDER BY entity.modifyDate DESC";

		if (dto.getText() != null && !dto.getText().isEmpty())
			where += " AND entity.code LIKE :text OR entity.name LIKE :text";

		hql += where + orderBy;
		Query query = manager.createQuery(hql, AdministrativeUnitDto.class);

		if (dto.getText() != null && !dto.getText().isEmpty())
			query.setParameter("text", "%" + dto.getText() + "%");

		int totalResult = query.getResultList().size();
		int startPosition = pageIndex * pageSize;

		query.setFirstResult(startPosition);
		query.setMaxResults(pageSize);

		List<AdministrativeUnitDto> entities = query.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		return new PageImpl<>(entities, pageable, totalResult);
	}

}
