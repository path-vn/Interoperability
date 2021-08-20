package com.globits.facility.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.globits.facility.dto.FacilityAdministrativeUnitDto;
import com.globits.facility.dto.SystemAddressCodeDto;
import com.globits.facility.service.FacilityAdministrativeUnitService;
import com.globits.facility.service.SystemAddressCodeService;
import com.globits.facility.utilities.FacilityConstant;
import com.globits.facility.utilities.ImportExcelUtil;

@RestController
@RequestMapping("/api/file/import")
public class RestFileImportController {

	@Autowired
	private FacilityAdministrativeUnitService service;
	@Autowired
	private SystemAddressCodeService systemAddressCodeService;
	@Secured({ FacilityConstant.ROLE_ADMIN })
	@RequestMapping(value = "/importAdministrativeUnit", method = RequestMethod.POST)
	public ResponseEntity<?> importAdministrativeUnitFile(@RequestParam("uploadfile") MultipartFile uploadfile)
			throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(uploadfile.getBytes());
		List<FacilityAdministrativeUnitDto> listFmsAdministrativeUnit = ImportExcelUtil.importAdministrativeUnitFromInputStream(bis);
		service.saveOrUpdateList(listFmsAdministrativeUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@Secured({ FacilityConstant.ROLE_ADMIN })
	@RequestMapping(value = "/OpcAssit/importAdministrativeUnit", method = RequestMethod.POST)
	public ResponseEntity<?> importOPCAdministrativeUnitFile(@RequestParam("uploadfile") MultipartFile uploadfile)
			throws IOException {
		ByteArrayInputStream bis = new ByteArrayInputStream(uploadfile.getBytes());
		List<SystemAddressCodeDto> listFmsAdministrativeUnit = ImportExcelUtil.importOpcAssitFromInputStream(bis);
		systemAddressCodeService.saveOrUpdateList(listFmsAdministrativeUnit);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
