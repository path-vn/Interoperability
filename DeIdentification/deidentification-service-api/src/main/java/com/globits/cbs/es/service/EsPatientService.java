package com.globits.cbs.es.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.Bundle;
import org.springframework.data.domain.Page;

import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.dto.SyncResultDto;
import com.globits.cbs.deidentification.functiondto.EsSearchDto;

public interface EsPatientService {
	
	public Bundle getPatientEverythingById(String theUrl);
	public PatientDto getPatientFromESById(String theId) throws IOException;
	public String savePatientDtoToESById(String theId);
	public String deletePatientDto(String theId) throws Exception;
	public Boolean deleteIndex(String indexName) throws Exception;

	public Bundle getPatientByLastUpdate(int pageIndex, int pageSize,Date fromDate, Date toDate);
	String savePatientBundleToES(Bundle patientEverything);
	String savePatientToESById(String theId);
	String synPatientByDate(int pageInde, int pageSize,String fromDate, String toDate);
	String getPatientFromESById(String index, String theId) throws IOException;
	List<PatientDto> findAllPatientDto() throws Exception;
	Integer synPatientDtoByDate(int pageIndex, int pageSize, String strFromDate, String strToDate);
	SyncResultDto synPatientDto(int pageIndex, int pageSize, Date fromDate, Date toDate);
	Page<PatientDto> findByPage(int pageIndex, int pageSize) throws Exception;
	PatientDto deIdentification(PatientDto patient);
	PatientDto searchPatientFromESById(String theId) throws IOException;
	Page<PatientDto> searchByPage(EsSearchDto dto) throws Exception;
	String savePatientDtoToES(PatientDto dto);	
}
