package com.globits.cbs.deidentification.service;

import java.util.Date;

import org.hl7.fhir.r4.model.Bundle;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;

import ca.uhn.fhir.rest.api.MethodOutcome;
@Service
public interface PatientService {
//	public Bundle getPatientEverythingById(String theUrl);
	public Bundle getPatientByLastUpdate(int pageIndex, int pageSize,Date fromDate, Date toDate);
		
	public PatientDto getPatient (String patientId);
	public Page<PatientDto> getAllPatient(SearchDto dto);
	public MethodOutcome deleteById(String id);
}
