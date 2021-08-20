package com.globits.hivimportcsadapter.rest;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globits.hivimportcsadapter.dto.DataFileDto;
import com.globits.hivimportcsadapter.functiondto.DataFileSearchDto;
import com.globits.hivimportcsadapter.service.DataFileService;

@Controller
@RequestMapping("/api/dataFile")
public class RestDataFileController {
	@Autowired
	DataFileService service;
	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<DataFileDto>> searchByDto(@RequestBody DataFileSearchDto dto) {
		Page<DataFileDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<DataFileDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DataFileDto> getById(@PathVariable UUID id) {
		DataFileDto result = service.getById(id);
		return new ResponseEntity<DataFileDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DataFileDto> save(@RequestBody DataFileDto dto) {
		DataFileDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<DataFileDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<DataFileDto> update(@RequestBody DataFileDto dto, @PathVariable("id") UUID id) {
		DataFileDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<DataFileDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
