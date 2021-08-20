package com.globits.adapter.pdma.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.globits.adapter.pdma.dto.AdapterObjectDto;
import com.globits.adapter.pdma.dto.PdmaSearchDto;
import com.globits.adapter.pdma.dto.PdmaDto;

public interface CaseService {
	public Page<PdmaDto> searchCaseByPage(PdmaSearchDto searchDto);

	public PdmaDto getItemByCaseId(Long caseID);

	public AdapterObjectDto getObjectById(Long caseID);

	public AdapterObjectDto getListObjectById(List<Long> ids);

	AdapterObjectDto getListObjectByIdAndLastSynDate(PdmaSearchDto searchDto);

	public ResponseEntity<String> splitListObject(PdmaSearchDto searchDto);
	
	public Page<PdmaDto> searchPNSCaseByPage(PdmaSearchDto searchDto);
	
	public Page<PdmaDto> searchHTSCaseByPage(PdmaSearchDto searchDto);
	
	public AdapterObjectDto getListPdmaObject(PdmaSearchDto searchDto);
	
	public AdapterObjectDto getListPdmaObjectById(List<Long> ids);
	
	public AdapterObjectDto getObjectPNSCaseById(Long caseID);
	
	public AdapterObjectDto getObjectHTSCaseById(Long caseID);
	
	public PdmaDto getItemPNSCaseById(Long caseID);
	
	public PdmaDto getItemHTSCaseById(Long caseID);

}
