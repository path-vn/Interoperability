package com.globits.facility.rest;

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

import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.search.FacilityAdministrativeUnitSearchDto;
import com.globits.facility.service.FacilityAdministrativeUnitService;

@RestController
@RequestMapping("/api/facilityAdministrativeUnit")
public class RestFacilityAdministrativeUnitController {

	@Autowired
	private FacilityAdministrativeUnitService service;
	
	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public ResponseEntity<Page<FacilityAdministrativeUnitDto>> searchByDto(@RequestBody FacilityAdministrativeUnitSearchDto dto) {
		Page<FacilityAdministrativeUnitDto> result = service.searchByDto(dto);
		return new ResponseEntity<Page<FacilityAdministrativeUnitDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getListBasic", method = RequestMethod.GET)
	public ResponseEntity<List<FacilityAdministrativeUnitDto>> getListBasic() {
		List<FacilityAdministrativeUnitDto> result = service.getListBasic();
		return new ResponseEntity<List<FacilityAdministrativeUnitDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<FacilityAdministrativeUnitDto> getById(@PathVariable UUID id) {
		FacilityAdministrativeUnitDto result = service.getById(id);
		return new ResponseEntity<FacilityAdministrativeUnitDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<FacilityAdministrativeUnitDto> save(@RequestBody FacilityAdministrativeUnitDto dto) {
		FacilityAdministrativeUnitDto result = service.saveOrUpdate(dto, null);
		return new ResponseEntity<FacilityAdministrativeUnitDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<FacilityAdministrativeUnitDto> update(@RequestBody FacilityAdministrativeUnitDto dto, @PathVariable("id") UUID id) {
		FacilityAdministrativeUnitDto result = service.saveOrUpdate(dto, id);
		return new ResponseEntity<FacilityAdministrativeUnitDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") String id) {
		Boolean result = service.deleteById(UUID.fromString(id));
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/checkCode", method = RequestMethod.GET)
	public ResponseEntity<Boolean> checkCode(@RequestParam(value = "id", required = false) UUID id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(code, id);
		return new ResponseEntity<Boolean>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}

}
