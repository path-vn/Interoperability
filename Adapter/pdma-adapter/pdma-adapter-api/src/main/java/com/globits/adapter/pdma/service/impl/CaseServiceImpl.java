package com.globits.adapter.pdma.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.globits.adapter.pdma.domain.Case;
import com.globits.adapter.pdma.dto.AdapterObjectDto;
import com.globits.adapter.pdma.dto.PdmaDto;
import com.globits.adapter.pdma.dto.PdmaSearchDto;
import com.globits.adapter.pdma.service.CaseService;
import com.globits.adapter.pdma.types.AdapterObjectType;
import com.globits.adapter.pdma.types.HTSYesNoNone;
import com.globits.adapter.pdma.types.HTSc14;
import com.globits.adapter.pdma.types.PNSHivStatus;
import com.globits.adapter.pdma.types.PNSc9;
import com.globits.adapter.pdma.utils.RestTemplateUtils;

@Service
public class CaseServiceImpl implements CaseService {
	public static final long EXECUTION_TIME = 5000L;
	public static int splitSize = 10;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<PdmaDto> searchCaseByPage(PdmaSearchDto searchDto) {
		if (searchDto == null) {
			return null;
		}
		int pageIndex = searchDto.getPageIndex();
		int pageSize = searchDto.getPageSize();

		if (pageIndex > 0)
			pageIndex--;
		else
			pageIndex = 0;

		String whereClause = "";
		String sqlCount = "select count(c.id) from Case c where (1 = 1) ";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from Case c where (1 = 1) ";

		
		if(searchDto.getText() != null) {
			whereClause += "AND c.person.fullname like :caseText";
		}
		if (searchDto.getCaseId() != null) {
			whereClause += "AND c.id = :caseId";
		}
		if (searchDto.getPersonId() != null) {
			whereClause += "AND c.person.id = :personId";
		}
		if (searchDto.getLastSynDate() != null) {
			whereClause += "AND c.modifyDate >= :lastSynDate";
		}
		sql += whereClause;
		sqlCount += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		Query qCount = entityManager.createQuery(sqlCount);

		if(searchDto.getText() != null) {
			q.setParameter("caseText", "%" + searchDto.getText()+"%");
			qCount.setParameter("caseText", "%"+searchDto.getText()+"%");
		}
		
		if (searchDto.getCaseId() != null) {
			q.setParameter("caseId", searchDto.getCaseId());
			qCount.setParameter("caseId", searchDto.getCaseId());
		}
		if (searchDto.getPersonId() != null) {
			q.setParameter("personId", searchDto.getPersonId());
			qCount.setParameter("personId", searchDto.getPersonId());
		}
		if (searchDto.getLastSynDate() != null) {
			q.setParameter("lastSynDate", searchDto.getLastSynDate());
			qCount.setParameter("lastSynDate", searchDto.getLastSynDate());
		}
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<PdmaDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<PdmaDto> result = new PageImpl<>(entities, pageable, count);

		return result;
	}

	@Override
	public AdapterObjectDto getListObjectByIdAndLastSynDate(PdmaSearchDto searchDto) {
		AdapterObjectDto ret = new AdapterObjectDto();
		Page<PdmaDto> listResult = searchCaseByPage(searchDto);
		ret.setObjectType(AdapterObjectType.LIST_DATA);
		ret.setListData(listResult.getContent());
		return ret;
	}

	@Override
	public ResponseEntity<String> splitListObject(PdmaSearchDto searchDto) {
		AdapterObjectDto dto = getListPdmaObject(searchDto);

		if (dto!=null && dto.getListData() != null && dto.getListData().size() > 0) {
			if (dto.getListData().size() > splitSize) {
				if ((dto.getListData().size() % splitSize) == 0) {
					int part = ((int) dto.getListData().size() / splitSize);
					for (int i = 0; i < part; i++) {
						List<PdmaDto> subListPdmaDto = dto.getListData().subList(splitSize * i, splitSize * (i + 1));
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListPdmaDto);
						subDto.setObjectType(AdapterObjectType.LIST_DATA);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				} else {
					int part = ((int) dto.getListData().size() / splitSize) + 1;
					for (int i = 0; i < part; i++) {
						List<PdmaDto> subListPdmaDto = new ArrayList<PdmaDto>();
						if (i < (part - 1)) {
							subListPdmaDto = dto.getListData().subList(splitSize * i, splitSize * (i + 1));
						} else {
							subListPdmaDto = dto.getListData().subList(splitSize * i, dto.getListData().size());
						}
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListPdmaDto);
						subDto.setObjectType(AdapterObjectType.LIST_DATA);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				}
			} else {
				ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
			}
		}
		return new ResponseEntity<String>("Success post data to Openhim!!",HttpStatus.OK);
	}

	private Case getEntityById(Long id) {

		String sql = "from Case c where c.id=:id";

		Query q = entityManager.createQuery(sql, Case.class);
		q.setParameter("id", id);
		Object result = q.getSingleResult();
		if (result != null) {
			return (Case) result;
		}
		return null;
	}

	@Override
	public PdmaDto getItemByCaseId(Long caseID) {
		String whereClause = "";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from Case c where (1 = 1) ";

		if (caseID != null) {
			whereClause += "AND c.id =: caseId";
		}
		sql += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		if (caseID != null) {
			q.setParameter("caseId", caseID);
		}
		List<PdmaDto> entities = q.getResultList();
		if (entities != null && entities.size() > 0) {
			return entities.get(0);
		}
		return null;

	}

	@Override
	public AdapterObjectDto getObjectById(Long caseID) {
		PdmaDto result = getItemByCaseId(caseID);

		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.SINGLE_DATA);
		ret.setSingleData(result);
		return ret;
	}

	@Override
	public AdapterObjectDto getListObjectById(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			AdapterObjectDto ret = new AdapterObjectDto();
			List<PdmaDto> listResult = new ArrayList<PdmaDto>();
			ret.setObjectType(AdapterObjectType.LIST_DATA);
			for (Long id : ids) {
				PdmaDto result = getItemByCaseId(id);
				listResult.add(result);
			}
			ret.setListData(listResult);
			return ret;
		}
		return null;
	}

	@Override
	public Page<PdmaDto> searchPNSCaseByPage(PdmaSearchDto searchDto) {
		if (searchDto == null) {
			return null;
		}
		int pageIndex = searchDto.getPageIndex();
		int pageSize = searchDto.getPageSize();

		if (pageIndex > 0)
			pageIndex--;
		else
			pageIndex = 0;

		String whereClause = "";
		String sqlCount = "select count(c.id) from PNSCaseContact c where (1 = 1) ";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from PNSCaseContact c where (1 = 1) ";

		
		if(searchDto.getText() != null) {
			whereClause += "AND c.person.fullname like :caseText";
		}
		if (searchDto.getCaseId() != null) {
			whereClause += "AND c.id = :caseId";
		}
		if (searchDto.getPersonId() != null) {
			whereClause += "AND c.person.id = :personId";
		}
		if (searchDto.getLastSynDate() != null) {
			whereClause += "AND c.modifyDate >= :lastSynDate";
		}
		whereClause += " AND ( c.c2 =: status or c.c9 =: pnsc9 ) ";
		sql += whereClause;
		sqlCount += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		Query qCount = entityManager.createQuery(sqlCount);

		if(searchDto.getText() != null) {
			q.setParameter("caseText", "%" + searchDto.getText()+"%");
			qCount.setParameter("caseText", "%"+searchDto.getText()+"%");
		}
		
		if (searchDto.getCaseId() != null) {
			q.setParameter("caseId", searchDto.getCaseId());
			qCount.setParameter("caseId", searchDto.getCaseId());
		}
		if (searchDto.getPersonId() != null) {
			q.setParameter("personId", searchDto.getPersonId());
			qCount.setParameter("personId", searchDto.getPersonId());
		}
		if (searchDto.getLastSynDate() != null) {
			q.setParameter("lastSynDate", searchDto.getLastSynDate());
			qCount.setParameter("lastSynDate", searchDto.getLastSynDate());
		}
		q.setParameter("status", PNSHivStatus.answer1);
		qCount.setParameter("status", PNSHivStatus.answer1);
		q.setParameter("pnsc9", PNSc9.answer2);
		qCount.setParameter("pnsc9", PNSc9.answer2);
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<PdmaDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<PdmaDto> result = new PageImpl<>(entities, pageable, count);

		return result;
	}

	@Override
	public Page<PdmaDto> searchHTSCaseByPage(PdmaSearchDto searchDto) {
		if (searchDto == null) {
			return null;
		}
		int pageIndex = searchDto.getPageIndex();
		int pageSize = searchDto.getPageSize();

		if (pageIndex > 0)
			pageIndex--;
		else
			pageIndex = 0;

		String whereClause = "";
		String sqlCount = "select count(c.id) from HTSCase c where (1 = 1) ";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from HTSCase c where (1 = 1) ";

		whereClause += " AND c.c19 is not null  ";
		if(searchDto.getText() != null) {
			whereClause += " AND c.c23FullName like :caseText ";
		}
		if (searchDto.getCaseId() != null) {
			whereClause += " AND c.id = :caseId ";
		}
//		if (searchDto.getPersonId() != null) {
//			whereClause += "AND c.person.id = :personId";
//		}
		if (searchDto.getLastSynDate() != null) {
			whereClause += " AND c.modifyDate >= :lastSynDate ";
		}
		whereClause += " AND ( c.c14 =: c14  ) ";
		whereClause += " AND ( c.c15 =: c15  ) ";
		sql += whereClause;
		sqlCount += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		Query qCount = entityManager.createQuery(sqlCount);

		if(searchDto.getText() != null) {
			q.setParameter("caseText", "%" + searchDto.getText()+"%");
			qCount.setParameter("caseText", "%"+searchDto.getText()+"%");
		}
		
		if (searchDto.getCaseId() != null) {
			q.setParameter("caseId", searchDto.getCaseId());
			qCount.setParameter("caseId", searchDto.getCaseId());
		}
//		if (searchDto.getPersonId() != null) {
//			q.setParameter("personId", searchDto.getPersonId());
//			qCount.setParameter("personId", searchDto.getPersonId());
//		}
		if (searchDto.getLastSynDate() != null) {
			q.setParameter("lastSynDate", searchDto.getLastSynDate());
			qCount.setParameter("lastSynDate", searchDto.getLastSynDate());
		}
		q.setParameter("c14", HTSc14.answer2);
		qCount.setParameter("c14", HTSc14.answer2);
		q.setParameter("c15", HTSYesNoNone.YES);
		qCount.setParameter("c15", HTSYesNoNone.YES);
		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		List<PdmaDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<PdmaDto> result = new PageImpl<>(entities, pageable, count);

		return result;
	}

	@Override
	public AdapterObjectDto getListPdmaObjectById(List<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AdapterObjectDto getObjectPNSCaseById(Long caseID) {
		PdmaDto result = getItemPNSCaseById(caseID);

		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.SINGLE_DATA);
		ret.setSingleData(result);
		return ret;
	}

	@Override
	public AdapterObjectDto getObjectHTSCaseById(Long caseID) {
		PdmaDto result = getItemHTSCaseById(caseID);

		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.SINGLE_DATA);
		ret.setSingleData(result);
		return ret;
	}

	@Override
	public PdmaDto getItemPNSCaseById(Long caseID) {
		String whereClause = "";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from PNSCaseContact  c where (1 = 1) ";

		if (caseID != null) {
			whereClause += " AND ( c.id =: caseId ) ";
		}
		
		whereClause += " AND ( c.c2 =: status or c.c9 =: pnsc9 ) ";
		sql += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		if (caseID != null) {
			q.setParameter("caseId", caseID);
		}
		q.setParameter("status", PNSHivStatus.answer1);
		q.setParameter("pnsc9", PNSc9.answer2);
		List<PdmaDto> entities = q.getResultList();
		if (entities != null && entities.size() > 0) {
			return entities.get(0);
		}
		return null;

	}

	@Override
	public PdmaDto getItemHTSCaseById(Long caseID) {
		String whereClause = "";
		String sql = "select new com.globits.adapter.pdma.dto.PdmaDto(c) from HTSCase c where (1 = 1) ";

		if (caseID != null) {
			whereClause += "AND c.id =: caseId";
		}
		sql += whereClause;
		Query q = entityManager.createQuery(sql, PdmaDto.class);
		if (caseID != null) {
			q.setParameter("caseId", caseID);
		}
		List<PdmaDto> entities = q.getResultList();
		if (entities != null && entities.size() > 0) {
			return entities.get(0);
		}
		return null;
	}

	@Override
	public AdapterObjectDto getListPdmaObject(PdmaSearchDto searchDto) {
		// TODO Auto-generated method stub
		AdapterObjectDto dto = new AdapterObjectDto();
		List<PdmaDto> listData = new ArrayList<PdmaDto>();
		Page<PdmaDto> listHTSCaseDto = this.searchHTSCaseByPage(searchDto);
		Page<PdmaDto> listPNSCaseDto = this.searchPNSCaseByPage(searchDto);
		if(listHTSCaseDto != null && listHTSCaseDto.getContent() != null && listHTSCaseDto.getContent().size()>0) {
			listData.addAll(listHTSCaseDto.getContent());
		}
		if(listPNSCaseDto != null && listPNSCaseDto.getContent() != null && listPNSCaseDto.getContent().size()>0) {
			listData.addAll(listPNSCaseDto.getContent());
		}
		if(listData != null &&  listData.size()>0) {
			dto.setListData(listData);
			dto.setObjectType(AdapterObjectType.LIST_DATA);
			return dto;
		}
		return null;
	}

}
