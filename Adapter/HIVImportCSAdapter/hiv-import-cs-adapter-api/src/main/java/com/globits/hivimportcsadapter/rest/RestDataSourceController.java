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
import org.springframework.web.bind.annotation.RequestParam;

import com.globits.hivimportcsadapter.dto.DataSourceDto;
import com.globits.hivimportcsadapter.functiondto.SampleSearchDto;
import com.globits.hivimportcsadapter.service.DataSourceService;

@Controller
@RequestMapping("/api/dataSource")
public class RestDataSourceController {
	@Autowired
	DataSourceService service;
	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<DataSourceDto>> searchByDto(@RequestBody SampleSearchDto dto) {
		Page<DataSourceDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<DataSourceDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DataSourceDto> getById(@PathVariable UUID id) {
		DataSourceDto result = service.getById(id);
		return new ResponseEntity<DataSourceDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DataSourceDto> save(@RequestBody DataSourceDto dto) {
		DataSourceDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<DataSourceDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<DataSourceDto> update(@RequestBody DataSourceDto dto, @PathVariable("id") UUID id) {
		DataSourceDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<DataSourceDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/checkCode",method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required=false) UUID id, @RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/checkUrl",method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkUrl(@RequestParam(value = "id", required=false) UUID id, @RequestParam("channelUrl") String channelUrl) {
		Boolean result = service.checkUrl(id, channelUrl);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	
}
