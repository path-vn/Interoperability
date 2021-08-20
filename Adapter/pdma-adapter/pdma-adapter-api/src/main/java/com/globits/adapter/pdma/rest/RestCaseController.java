package com.globits.adapter.pdma.rest;

import java.io.IOException;
import java.util.ArrayList;
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

import com.globits.adapter.pdma.dto.AdapterObjectDto;
import com.globits.adapter.pdma.dto.PdmaDto;
import com.globits.adapter.pdma.dto.PdmaSearchDto;
import com.globits.adapter.pdma.service.CaseService;
import com.globits.adapter.pdma.types.AdapterObjectType;
import com.globits.adapter.pdma.utils.RestTemplateUtils;

@RestController
@RequestMapping("/public/pdma/case")
@CrossOrigin("*")
public class RestCaseController {
	@Autowired
	CaseService caseService;

	@RequestMapping(value = "/searchByDto", method = RequestMethod.POST)
	public Page<PdmaDto> searchOPCAssistByPage(@RequestBody PdmaSearchDto searchDto) {
		Page<PdmaDto> listCaseDto = caseService.searchPNSCaseByPage(searchDto);

		return listCaseDto;
	}

	@RequestMapping(value = "/assist/detail/{caseId}", method = RequestMethod.GET)
	public AdapterObjectDto getById(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectPNSCaseById(caseId);
		return dto;
	}

	@RequestMapping(value = "/pdma/{caseId}", method = RequestMethod.GET)
	public void postSingleObject(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectById(caseId);
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
	}
	@RequestMapping(value = "/pdma/pns/{caseId}", method = RequestMethod.GET)
	public void postSinglePNSObject(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectPNSCaseById(caseId);
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
	}
	
	@RequestMapping(value = "/pdma/hts/{caseId}", method = RequestMethod.GET)
	public void postSingleHTSObject(@PathVariable Long caseId) throws IOException {
		AdapterObjectDto dto = caseService.getObjectHTSCaseById(caseId);
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
	}

	@RequestMapping(value = "/listpdma", method = RequestMethod.POST)
	public void postListObject(@RequestBody PdmaSearchDto searchDto) throws IOException {
		AdapterObjectDto dto = caseService.getListPdmaObject(searchDto);
		AdapterObjectDto adapterObjectDto = new AdapterObjectDto();
		List<PdmaDto> list = new ArrayList<PdmaDto>();
		if (dto.getListData() != null && dto.getListData().size() > 0) {
			list =dto.getListData();
		}
		int splitSize = 100;
//		if (dto.getListData() != null && dto.getListData().size() > 0) {
//			if (dto.getListData().size() > 100) {
//				if ((dto.getListData().size() % 100) == 0) {
//					int part = ((int) dto.getListData().size() / 100);
//					for (int i = 0; i < part; i++) {
//						AdapterObjectDto subDto = caseService.getListObjectById(request);
//						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
//					}
//				} else {
//					int part = ((int) dto.getListData().size() / 100) + 1;
//					for (int i = 0; i < part; i++) {
//						AdapterObjectDto subDto = caseService.getListObjectById(request);
//						ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(subDto);
//					}
//				}
//			} else {
//				ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(dto);
//			}
//		}
		if (list != null && list.size() > 0) {
 			if (list.size() > splitSize) {
 				if ((list.size() % splitSize) == 0) {
 					int part = ((int) list.size() / splitSize);
 					for (int i = 0; i < part; i++) {
 						List<PdmaDto> subList = list.subList(splitSize * i,
 								splitSize * (i + 1));
 						 adapterObjectDto.setListData(subList);
 						 adapterObjectDto.setObjectType(AdapterObjectType.LIST_DATA);
 			        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);



 					}
 				} else {
 					int part = ((int) list.size() / splitSize) + 1;
 					for (int i = 0; i < part; i++) {
 						List<PdmaDto> subList = new ArrayList<PdmaDto>();
 						if (i < (part - 1)) {
 							subList = list.subList(splitSize * i, splitSize * (i + 1));
 						} else {
 							subList = list.subList(splitSize * i, list.size());
 						}
 							adapterObjectDto.setListData(subList);
 						 adapterObjectDto.setObjectType(AdapterObjectType.LIST_DATA);
 			        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);


 					}
 				}
 			} else {
 				
 				 adapterObjectDto.setListData(list);
 				 adapterObjectDto.setObjectType(AdapterObjectType.LIST_DATA);
	        	 ResponseEntity<String> responseData = RestTemplateUtils.postObjectToOpenHim(adapterObjectDto);

 			}
 		}
	}

	@RequestMapping(value = "/getDataSource", method = RequestMethod.POST)
	public Page<PdmaDto> getDataSourceByPost(@RequestBody PdmaSearchDto searchDto) {
		Page<PdmaDto> listCaseDto = caseService.searchCaseByPage(searchDto);

		return listCaseDto;
	}

	@RequestMapping(value = "/updateDataSource", method = RequestMethod.POST)
	public void updateDataSourceByPost(@RequestBody PdmaSearchDto searchDto) throws IOException {
		caseService.splitListObject(searchDto);
	}

}
