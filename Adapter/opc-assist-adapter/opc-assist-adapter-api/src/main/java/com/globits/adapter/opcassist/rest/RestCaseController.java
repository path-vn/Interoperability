package com.globits.adapter.opcassist.rest;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globits.adapter.opcassist.dto.AdapterObjectDto;
import com.globits.adapter.opcassist.dto.OPCAssistSearchDto;
import com.globits.adapter.opcassist.dto.OpcAssistDto;
import com.globits.adapter.opcassist.service.CaseService;
import com.globits.adapter.opcassist.utils.RestTemplateUtils;

@RestController
@RequestMapping("/public/opcassists/case")
@CrossOrigin("*")
public class RestCaseController {
	@Autowired
	CaseService caseService;

	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public Page<OpcAssistDto> searchOPCAssistByPage(@RequestBody OPCAssistSearchDto searchDto) {
		Page<OpcAssistDto> listCaseDto = caseService.searchCaseByPage(searchDto);

		return listCaseDto;
	}
	
	@RequestMapping(value = "/assist/detail/{caseId}", method = RequestMethod.GET)
	public AdapterObjectDto getById(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectById(caseId);
		return dto;
	}

	@RequestMapping(value = "/assist/{caseId}", method = RequestMethod.GET)
	public void postSingleObject(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectById(caseId);
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
	}

	@RequestMapping(value = "/listassist", method = RequestMethod.POST)
	public void postListObject(@RequestBody List<Long> request) throws IOException {
		AdapterObjectDto dto = caseService.getListObjectById(request);
		if (dto.getListData() != null && dto.getListData().size() > 0) {
			if (dto.getListData().size() > 100) {
				if ((dto.getListData().size() % 100) == 0) {
					int part = ((int) dto.getListData().size() / 100);
					for (int i = 0; i < part; i++) {
						AdapterObjectDto subDto = caseService.getListObjectById(request);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				} else {
					int part = ((int) dto.getListData().size() / 100) + 1;
					for (int i = 0; i < part; i++) {
						AdapterObjectDto subDto = caseService.getListObjectById(request);
						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
					}
				}
			} else {
				ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
			}
		}
	}

	@RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
	public Page<OpcAssistDto> getDataSourceByPost(@RequestBody OPCAssistSearchDto searchDto) {
		Page<OpcAssistDto> listCaseDto = caseService.searchCaseByPage(searchDto);

		return listCaseDto;
	}

	@RequestMapping(value = "/updateDataSource", method = RequestMethod.POST)
	public void updateDataSourceByPost(@RequestBody OPCAssistSearchDto searchDto) throws IOException {
		caseService.splitListObject(searchDto);
	}

//	@RequestMapping(value = "/updateDataSourceToFhir", method = RequestMethod.POST)
//	public void updateDataSource(@RequestBody int numberOfDate) throws IOException {
//		OPCAssistSearchDto searchDtoLow = new OPCAssistSearchDto();
//		searchDtoLow.setPageSize(numberOfDate);
//		AdapterObjectDto dto = caseService.getListObjectByIdAndLastSynDate(searchDtoLow);
//		if (numberOfDate > 100) {
//			if ((numberOfDate % 100) == 0) {
//				int part = ((int) numberOfDate / 100);
//				for (int i = 0; i < part; i++) {
//					OPCAssistSearchDto searchDto = new OPCAssistSearchDto();
//					searchDto.setPageSize(100);
//					searchDto.setPageIndex(i + 1);
//					AdapterObjectDto subDto = caseService.getListObjectByIdAndLastSynDate(searchDto);
//					ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
//				}
//			} else {
//				int part = ((int) numberOfDate / 100) + 1;
//				for (int i = 0; i < part; i++) {
//					OPCAssistSearchDto searchDto = new OPCAssistSearchDto();
//					searchDto.setPageSize(100);
//					searchDto.setPageIndex(i + 1);
//					AdapterObjectDto subDto = caseService.getListObjectByIdAndLastSynDate(searchDto);
//					ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
//				}
//			}
//		} else {
//			ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
//		}
//	}

}
