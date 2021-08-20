package com.globits.facility.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.facility.dto.SystemAddressCodeDto;
import com.globits.facility.search.SystemSearchDto;
import com.globits.facility.service.SystemAddressCodeService;

@RestController
@RequestMapping("/public/address")
public class RestPublicController {
	@Autowired
	private SystemAddressCodeService service;
	
	@RequestMapping(value = "/system", method = RequestMethod.POST)
	public ResponseEntity<SystemAddressCodeDto> getById(@RequestBody SystemSearchDto search) {
		SystemAddressCodeDto result = service.getAddess(search);
		return new ResponseEntity<SystemAddressCodeDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
}
