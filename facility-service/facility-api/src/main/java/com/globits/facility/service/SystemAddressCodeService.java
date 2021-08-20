package com.globits.facility.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.globits.core.service.GenericService;
import com.globits.facility.domain.SystemAddressCode;
import com.globits.facility.dto.SystemAddressCodeDto;
import com.globits.facility.search.SystemAddressCodeSearchDto;
import com.globits.facility.search.SystemSearchDto;

public interface SystemAddressCodeService extends GenericService<SystemAddressCode, UUID> {

	Page<SystemAddressCodeDto> searchByDto(SystemAddressCodeSearchDto dto);

	SystemAddressCodeDto getById(UUID id);

	SystemAddressCodeDto saveOrUpdate(SystemAddressCodeDto dto, UUID id);

	Boolean deleteById(UUID id);
	
	void saveOrUpdateList(List<SystemAddressCodeDto> listFmsAdministrativeUnit);
	public SystemAddressCodeDto saveImport(SystemAddressCodeDto dto, UUID id);
	SystemAddressCodeDto getAddess(SystemSearchDto dto);

}
