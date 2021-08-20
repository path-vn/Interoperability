package com.globits.facility.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.facility.dto.SystemHealthOrganizationCodeDto;
import com.globits.facility.search.SystemHealthOrganizationCodeSearchDto;
import com.globits.facility.service.SystemHealthOrganizationCodeService;

@RestController
@RequestMapping("/api/systemHealthOrganizationCode")
public class RestSystemHealthOrganizationCodeController {

	@Autowired
	private SystemHealthOrganizationCodeService service;
	
	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<SystemHealthOrganizationCodeDto>> searchByDto(@RequestBody SystemHealthOrganizationCodeSearchDto dto) {
		Page<SystemHealthOrganizationCodeDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<SystemHealthOrganizationCodeDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SystemHealthOrganizationCodeDto> getById(@PathVariable UUID id) {
		SystemHealthOrganizationCodeDto result = service.getById(id);
		return new ResponseEntity<SystemHealthOrganizationCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SystemHealthOrganizationCodeDto> save(@RequestBody SystemHealthOrganizationCodeDto dto) {
		SystemHealthOrganizationCodeDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<SystemHealthOrganizationCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SystemHealthOrganizationCodeDto> update(@RequestBody SystemHealthOrganizationCodeDto dto, @PathVariable("id") UUID id) {
		SystemHealthOrganizationCodeDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<SystemHealthOrganizationCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	

}
