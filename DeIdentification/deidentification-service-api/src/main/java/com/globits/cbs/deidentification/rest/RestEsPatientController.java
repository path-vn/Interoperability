package com.globits.cbs.deidentification.rest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.functiondto.EsSearchDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;
import com.globits.cbs.deidentification.service.PatientService;
import com.globits.cbs.es.service.EsPatientService;


@RestController
@RequestMapping("/api/patient-es")
public class RestEsPatientController {
	@Autowired
	private PatientService service;
	@Autowired
	EsPatientService esPatientService;
	@RequestMapping(value = "/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<PatientDto>> searchByPage(@RequestBody EsSearchDto searchByPage) {
//		int pageIndex = searchByPage.getPageIndex();
//		int pageSize =searchByPage.getPageSize();
		Page<PatientDto> result;
		try {
			result = esPatientService.searchByPage(searchByPage);
			return new ResponseEntity<Page<PatientDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<Page<PatientDto>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<PatientDto> getPatientById(@PathVariable String id) {
		PatientDto result = service.getPatient(id);
		return new ResponseEntity<PatientDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

		
	@RequestMapping(value = "/{pageIndex}/{pageSize}/{fromDate}/{toDate}", method = RequestMethod.GET)
	public Bundle getPatientByLastUpdate(@PathVariable int pageIndex,@PathVariable int pageSize,@PathVariable String fromDate,@PathVariable String toDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return service.getPatientByLastUpdate(pageIndex, pageSize, sdf.parse(fromDate), sdf.parse(toDate)); 
	 }
	
	@RequestMapping(value = "/Patient/{theId}", method = RequestMethod.GET)
	public ResponseEntity<String> savePatientById(@PathVariable String theId) {
		String result = esPatientService.savePatientDtoToESById(theId);
		return new ResponseEntity<String>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getPatientFromESById/{id}", method = RequestMethod.GET)
	public ResponseEntity<PatientDto> getPatientFromESById(@PathVariable String id) {
		PatientDto result = null;
		try {
			result = esPatientService.getPatientFromESById(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<PatientDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/Patient/searchPatientFromESById/{id}", method = RequestMethod.GET)
	public ResponseEntity<PatientDto> searchPatientFromESById(@PathVariable String id) {
		PatientDto result = null;
		try {
			result = esPatientService.searchPatientFromESById(id);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<PatientDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/save/savePatientToESById/{id}",method = RequestMethod.GET)
	public ResponseEntity<String> save(@PathVariable String id) {
		String result = esPatientService.savePatientDtoToESById(id);
		return new ResponseEntity<String>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/patientdto/searchbypage", method = RequestMethod.POST)
	public ResponseEntity<Page<PatientDto>> findByPage(@RequestBody EsSearchDto dto) throws Exception {
		Page<PatientDto> result = esPatientService.searchByPage(dto);
		return new ResponseEntity<Page<PatientDto>>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
