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

import com.globits.facility.dto.SystemAddressCodeDto;
import com.globits.facility.search.SystemAddressCodeSearchDto;
import com.globits.facility.service.SystemAddressCodeService;

@RestController
@RequestMapping("/api/systemAddressCode")
public class RestSystemAddressCodeController {

	@Autowired
	private SystemAddressCodeService service;
	
	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<SystemAddressCodeDto>> searchByDto(@RequestBody SystemAddressCodeSearchDto dto) {
		Page<SystemAddressCodeDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<SystemAddressCodeDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SystemAddressCodeDto> getById(@PathVariable UUID id) {
		SystemAddressCodeDto result = service.getById(id);
		return new ResponseEntity<SystemAddressCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<SystemAddressCodeDto> save(@RequestBody SystemAddressCodeDto dto) {
		SystemAddressCodeDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<SystemAddressCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SystemAddressCodeDto> update(@RequestBody SystemAddressCodeDto dto, @PathVariable("id") UUID id) {
		SystemAddressCodeDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<SystemAddressCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	

}
