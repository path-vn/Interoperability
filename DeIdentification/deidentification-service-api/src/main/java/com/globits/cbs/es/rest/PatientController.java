package com.globits.cbs.es.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.cbs.deidentification.dto.PatientDto;
import com.globits.cbs.deidentification.functiondto.EsSearchDto;
import com.globits.cbs.es.service.EsPatientService;

@RestController
@RequestMapping("/public/es-patient")
public class PatientController {
	@Autowired
	private EsPatientService esPatientService;
	
	@RequestMapping(value = "/patient/{theId}", method = RequestMethod.GET)
	public ResponseEntity<String> savePatientById(@PathVariable String theId) {
		String result = esPatientService.savePatientToESById(theId);
		return new ResponseEntity<String>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/patientdto/all", method = RequestMethod.GET)
	public ResponseEntity<List<PatientDto>> findAllPatientDto() throws Exception {
		List<PatientDto> result = esPatientService.findAllPatientDto();
		return new ResponseEntity<List<PatientDto>>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/patientdto/getbypage/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<PatientDto>> findByPage(@PathVariable int pageIndex,@PathVariable int pageSize) throws Exception {
		Page<PatientDto> result = esPatientService.findByPage(pageIndex, pageSize);
		return new ResponseEntity<Page<PatientDto>>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/patientdto/{theId}", method = RequestMethod.GET)
	public ResponseEntity<String> savePatientDtoById(@PathVariable String theId) {
		String result = esPatientService.savePatientDtoToESById(theId);
		return new ResponseEntity<String>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/patientdto/{theId}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deletePatientDtoById(@PathVariable String theId) throws Exception {
		String result = esPatientService.deletePatientDto(theId);
		return new ResponseEntity<String>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/index/{theIndex}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteIndex(@PathVariable String theIndex) throws Exception {
		Boolean result = esPatientService.deleteIndex(theIndex);
		return new ResponseEntity<Boolean>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/get/{theId}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<PatientDto> getPatientById(@PathVariable String theId) throws IOException {
		PatientDto result = esPatientService.getPatientFromESById(theId);
		return new ResponseEntity<PatientDto>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/get/{index}/{theId}", method = RequestMethod.GET, produces = { "text/plain; charset=utf-8" })
	public ResponseEntity<String> getPatientById(@PathVariable String index,@PathVariable String theId) throws IOException {
		String result = esPatientService.getPatientFromESById(index,theId);
		return new ResponseEntity<String>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/sync/{pageIndex}/{pageSize}/{fromDate}/{toDate}", method = RequestMethod.GET, produces = { "application/json; charset=utf-8" })
	public ResponseEntity<Integer> syncPatientByDate(@PathVariable int pageIndex, @PathVariable int pageSize, @PathVariable String fromDate, @PathVariable String toDate) throws IOException {
		Integer result = esPatientService.synPatientDtoByDate(pageIndex, pageSize, fromDate, toDate);
		return new ResponseEntity<Integer>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/patientdto/deidentification", method = RequestMethod.POST)
	public ResponseEntity<PatientDto> deIdentification(@RequestBody PatientDto dto) {
		PatientDto result = esPatientService.deIdentification(dto);
		return new ResponseEntity<PatientDto>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/patientdto/searchbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<PatientDto> searchById(@PathVariable String id) throws IOException {
		PatientDto result = esPatientService.searchPatientFromESById(id);
		return new ResponseEntity<PatientDto>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/patientdto/getbyid/{id}", method = RequestMethod.GET)
	public ResponseEntity<PatientDto> getById(@PathVariable String id) throws IOException {
		PatientDto result = esPatientService.getPatientFromESById(id);
		return new ResponseEntity<PatientDto>(result, (result !=null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
