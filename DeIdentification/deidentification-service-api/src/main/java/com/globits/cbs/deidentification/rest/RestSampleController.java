package com.globits.cbs.deidentification.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.globits.cbs.deidentification.dto.SampleDto;
import com.globits.cbs.deidentification.functiondto.SampleSearchDto;
import com.globits.cbs.deidentification.service.SampleService;

@RestController
@RequestMapping("/api/box")
public class RestSampleController {

	@Autowired
	private SampleService service;

	@RequestMapping(value = "searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<SampleDto>> searchByDto(@RequestBody SampleSearchDto dto) {
		Page<SampleDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<SampleDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SampleDto> getById(@PathVariable UUID id) {
		SampleDto result = service.getById(id);
		return new ResponseEntity<SampleDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SampleDto> save(@RequestBody SampleDto dto) {
		SampleDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<SampleDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SampleDto> update(@RequestBody SampleDto dto, @PathVariable("id") UUID id) {
		SampleDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<SampleDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
