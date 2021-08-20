package com.globits.adapter.opcassist.service.impl;

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

import com.globits.adapter.opcassist.domain.Case;
import com.globits.adapter.opcassist.dto.AdapterObjectDto;
import com.globits.adapter.opcassist.dto.OPCAssistSearchDto;
import com.globits.adapter.opcassist.dto.OpcAssistDto;
import com.globits.adapter.opcassist.service.CaseService;
import com.globits.adapter.opcassist.types.AdapterObjectType;
import com.globits.adapter.opcassist.utils.RestTemplateUtils;

@Service
public class CaseServiceImpl implements CaseService {
	public static final long EXECUTION_TIME = 5000L;
	public static int splitSize = 10;
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<OpcAssistDto> searchCaseByPage(OPCAssistSearchDto searchDto) {
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
		String sql = "select new com.globits.adapter.opcassist.dto.OpcAssistDto(c) from Case c where (1 = 1) ";

		
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
		Query q = entityManager.createQuery(sql, OpcAssistDto.class);
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
		List<OpcAssistDto> entities = q.getResultList();
		long count = (long) qCount.getSingleResult();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<OpcAssistDto> result = new PageImpl<>(entities, pageable, count);

		return result;
	}

//	@Override
//	public Page<OpcAssistDto> updateCaseByPost(OPCAssistSearchDto searchDto) {
//		if (searchDto == null) {
//			return null;
//		}
//		int pageIndex = searchDto.getPageIndex();
//		int pageSize = searchDto.getPageSize();
//
//		if (pageIndex > 0)
//			pageIndex--;
//		else
//			pageIndex = 0;
//
//		Page<OpcAssistDto> result = searchCaseByPage(searchDto);
//		return result;
//	}

	@Override
	public AdapterObjectDto getListObjectByIdAndLastSynDate(OPCAssistSearchDto searchDto) {
		AdapterObjectDto ret = new AdapterObjectDto();
		Page<OpcAssistDto> listResult = searchCaseByPage(searchDto);
		ret.setObjectType(AdapterObjectType.LIST_DATA);
		ret.setListData(listResult.getContent());
		return ret;
	}

	@Override
	public ResponseEntity<String> splitListObject(OPCAssistSearchDto searchDto) {
		AdapterObjectDto dto = getListObjectByIdAndLastSynDate(searchDto);

		if (dto.getListData() != null && dto.getListData().size() > 0) {
			if (dto.getListData().size() > splitSize) {
				if ((dto.getListData().size() % splitSize) == 0) {
					int part = ((int) dto.getListData().size() / splitSize);
					for (int i = 0; i < part; i++) {
						List<OpcAssistDto> subListOpcAssistDto = dto.getListData().subList(splitSize * i, splitSize * (i + 1));
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListOpcAssistDto);
						subDto.setObjectType(AdapterObjectType.LIST_DATA);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				} else {
					int part = ((int) dto.getListData().size() / splitSize) + 1;
					for (int i = 0; i < part; i++) {
						List<OpcAssistDto> subListOpcAssistDto = new ArrayList<OpcAssistDto>();
						if (i < (part - 1)) {
							subListOpcAssistDto = dto.getListData().subList(splitSize * i, splitSize * (i + 1));
						} else {
							subListOpcAssistDto = dto.getListData().subList(splitSize * i, dto.getListData().size());
						}
						AdapterObjectDto subDto = new AdapterObjectDto();
						subDto.setListData(subListOpcAssistDto);
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

//	@Override
//	public Page<OpcAssistDto> updateCaseByGet(LocalDateTime lastSynDate, int pageIndex, int pageSize) {
//		if (pageIndex > 0)
//			pageIndex--;
//		else
//			pageIndex = 0;
//
//		OPCAssistSearchDto searchDto = new OPCAssistSearchDto();
//		searchDto.setLastSynDate(lastSynDate);
//		searchDto.setPageIndex(pageIndex);
//		searchDto.setPageSize(pageSize);
//
//		Page<OpcAssistDto> result = searchCaseByPage(searchDto);
//		return result;
//	}

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
	public OpcAssistDto getItemByCaseId(Long caseID) {
		String whereClause = "";
		String sql = "select new com.globits.adapter.opcassist.dto.OpcAssistDto(c) from Case c where (1 = 1) ";

		if (caseID != null) {
			whereClause += "AND c.id =: caseId";
		}
		sql += whereClause;
		Query q = entityManager.createQuery(sql, OpcAssistDto.class);
		if (caseID != null) {
			q.setParameter("caseId", caseID);
		}
		List<OpcAssistDto> entities = q.getResultList();
		if (entities != null && entities.size() > 0) {
			return entities.get(0);
		}
		return null;

	}

	@Override
	public AdapterObjectDto getObjectById(Long caseID) {
		OpcAssistDto result = getItemByCaseId(caseID);

		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.SINGLE_DATA);
		ret.setSingleData(result);
		return ret;
	}

	@Override
	public AdapterObjectDto getListObjectById(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			AdapterObjectDto ret = new AdapterObjectDto();
			List<OpcAssistDto> listResult = new ArrayList<OpcAssistDto>();
			ret.setObjectType(AdapterObjectType.LIST_DATA);
			for (Long id : ids) {
				OpcAssistDto result = getItemByCaseId(id);
				listResult.add(result);
			}
			ret.setListData(listResult);
			return ret;
		}
		return null;
	}
}
