package com.globits.hivimportcsadapter.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.core.service.impl.GenericServiceImpl;
import com.globits.hivimportcsadapter.domain.Sample;
import com.globits.hivimportcsadapter.dto.SampleDto;
import com.globits.hivimportcsadapter.functiondto.SampleSearchDto;
import com.globits.hivimportcsadapter.repository.SampleRepository;
import com.globits.hivimportcsadapter.service.SampleService;

@Transactional
@Service
public class SampleServiceImpl extends GenericServiceImpl<Sample, UUID> implements SampleService {

	@Autowired
	private EntityManager manager;

	@Autowired
	public SampleRepository sampleRepository;

	@Override
	public Page<SampleDto> searchByDto(SampleSearchDto dto) {
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
		String sqlCount = "select count(eqap.id) from Sample as eqap where (1=1) ";
		String sql = "select new com.globits.srms.dto.SampleDto(eqap) from Box as eqap where (1=1) ";

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			whereClause += " AND (eqap.name LIKE :text " + "OR eqap.code LIKE :text "
					+ "OR eqap.numberOfColumns LIKE :text " + "OR eqap.numberOfRows LIKE :text) ";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;
		Query q = manager.createQuery(sql, SampleDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getText() != null && StringUtils.hasText(dto.getText())) {
			q.setParameter("text", '%' + dto.getText().trim() + '%');
			qCount.setParameter("text", '%' + dto.getText().trim() + '%');
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<SampleDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<SampleDto> result = new PageImpl<SampleDto>(entities, pageable, count);

		return result;
	}

	@Override
	public SampleDto saveOrUpdate(SampleDto dto, UUID id) {
		if (dto != null) {
			Sample entity = null;
			if (id != null) {
				entity = sampleRepository.getOne(id);
			}
			if (entity == null) {
				entity = new Sample();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity = sampleRepository.save(entity);
			if (entity != null) {
				return new SampleDto(entity);
			}
		}
		return null;

	}

	@Override
	public SampleDto getById(UUID id) {
		if (id != null) {
			Sample entity = sampleRepository.getOne(id);
			if (entity != null) {
				return new SampleDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteById(UUID id) {
		if (id != null) {
			Sample entity = sampleRepository.getOne(id);
			if (entity != null) {
				sampleRepository.deleteById(id);
				return true;
			}
		}
		return false;
	}

	@Override
	public SampleDto getByCode(String code) {
		if (StringUtils.hasText(code)) {
			List<Sample> entities = sampleRepository.getByCode(code);
			if (entities != null && !entities.isEmpty()) {
				return new SampleDto(entities.get(0));
			}
		}
		return null;
	}

}
