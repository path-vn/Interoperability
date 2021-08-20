package com.globits.adapter.eclinica.data.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import com.globits.adapter.eclinica.data.dto.AdapterObjectDto;
import com.globits.adapter.eclinica.data.dto.PatientDto;
import com.globits.adapter.eclinica.data.dto.SearchObjectDto;

public interface PatientService {
	public PatientDto getById(Integer id);

	public List<PatientDto> getList(SearchObjectDto dto);

	public Page<PatientDto> getPage(SearchObjectDto dto);

	public AdapterObjectDto getListObjectByIdAndLastSynDate(SearchObjectDto searchDto);

	public ResponseEntity<String> splitListObject(SearchObjectDto searchDto);
}
