package com.globits.adapter.opcassist.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.globits.adapter.opcassist.dto.AdapterObjectDto;
import com.globits.adapter.opcassist.dto.OPCAssistSearchDto;
import com.globits.adapter.opcassist.dto.OpcAssistDto;

public interface CaseService {
	public Page<OpcAssistDto> searchCaseByPage(OPCAssistSearchDto searchDto);

	public OpcAssistDto getItemByCaseId(Long caseID);

	public AdapterObjectDto getObjectById(Long caseID);

	public AdapterObjectDto getListObjectById(List<Long> ids);

//	Page<OpcAssistDto> updateCaseByPost(OPCAssistSearchDto searchDto);

	AdapterObjectDto getListObjectByIdAndLastSynDate(OPCAssistSearchDto searchDto);

	public ResponseEntity<String> splitListObject(OPCAssistSearchDto searchDto);

//	Page<OpcAssistDto> updateCaseByGet(LocalDateTime lastSynDate, int pageIndex, int pageSize);
}
