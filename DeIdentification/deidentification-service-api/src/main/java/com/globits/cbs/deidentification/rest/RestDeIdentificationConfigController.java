package com.globits.cbs.deidentification.rest;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.globits.cbs.deidentification.dto.DeIdentificationConfigDto;
import com.globits.cbs.deidentification.functiondto.SearchDto;
import com.globits.cbs.deidentification.service.DeIdentificationConfigService;
@RestController
@RequestMapping("/api/deIdentificationConfig")
public class RestDeIdentificationConfigController {
	@Autowired
	DeIdentificationConfigService deIdentificationConfigService;

	@RequestMapping(value = "/{pageIndex}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<Page<DeIdentificationConfigDto>> getPage(@PathVariable int pageIndex, @PathVariable int pageSize) {
		Page<DeIdentificationConfigDto> results = deIdentificationConfigService.getPage(pageSize, pageIndex);
		return new ResponseEntity<Page<DeIdentificationConfigDto>>(results, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<DeIdentificationConfigDto> save(@RequestBody DeIdentificationConfigDto dto) {
		DeIdentificationConfigDto result = deIdentificationConfigService.saveOrUpdate(null,dto);
		return new ResponseEntity<DeIdentificationConfigDto>(result, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<DeIdentificationConfigDto> save(@RequestBody DeIdentificationConfigDto dto ,@PathVariable UUID id) {
		DeIdentificationConfigDto result = deIdentificationConfigService.saveOrUpdate(id,dto);
		return new ResponseEntity<DeIdentificationConfigDto>(result, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<DeIdentificationConfigDto> getList(@PathVariable UUID id) {
		DeIdentificationConfigDto result = deIdentificationConfigService.getById(id);
		return new ResponseEntity<DeIdentificationConfigDto>(result, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE)
	public ResponseEntity<Integer> deletes(@RequestBody List<DeIdentificationConfigDto> dtos) {
		int result = deIdentificationConfigService.deletes(dtos);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
		Boolean result = deIdentificationConfigService.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}


	@RequestMapping(value="/searchByPage", method = RequestMethod.POST)
	public ResponseEntity<Page<DeIdentificationConfigDto>> searchByPage(@RequestBody SearchDto searchDto) {
		Page<DeIdentificationConfigDto> page =  this.deIdentificationConfigService.searchByPage(searchDto);
		return new ResponseEntity<Page<DeIdentificationConfigDto>>(page, HttpStatus.OK);
	}

	@RequestMapping(value = "/checkCode",method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required=false) UUID id, @RequestParam("code") String code) {
		Boolean result = deIdentificationConfigService.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
