package com.globits.hivimportcsadapter.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globits.core.dto.AdministrativeUnitDto;
import com.globits.hivimportcsadapter.functiondto.AdministrativeUnitSearchDto;
import com.globits.hivimportcsadapter.service.AdministrativeUnitsService;

@RestController
@RequestMapping("/api/administrativeUnit")
public class RestAdministrativeUnitsController {

	@Autowired
	AdministrativeUnitsService administrativeUnitsService;

	@PostMapping("/searchByDto")
	public ResponseEntity<?> searchByDto(@RequestBody AdministrativeUnitSearchDto dto) {
		Page<AdministrativeUnitDto> result = administrativeUnitsService.searchByDto(dto);

		return new ResponseEntity<>(result, result != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
}
