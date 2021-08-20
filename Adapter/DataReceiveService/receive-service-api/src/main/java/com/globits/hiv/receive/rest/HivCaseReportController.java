package com.globits.hiv.receive.rest;

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
import org.springframework.web.bind.annotation.RestController;

import com.globits.hiv.receive.dto.AdapterObjectDto;
import com.globits.hiv.receive.dto.HivCaseReportDto;
import com.globits.hiv.receive.dto.ResultDto;
import com.globits.hiv.receive.service.HivCaseReportService;
import com.globits.hiv.receive.utilities.RestTemplateUtils;

@RestController
@RequestMapping("/api/case_report")

public class HivCaseReportController {

	@Autowired
	HivCaseReportService hivCaseReportService;

	@RequestMapping(method = RequestMethod.POST)
	public ResultDto<HivCaseReportDto> post(@RequestBody HivCaseReportDto caseReport) {
		HivCaseReportDto dto = null;
		try {
			dto = hivCaseReportService.saveOrUpdate(caseReport);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(dto!=null) {
			ResultDto<HivCaseReportDto> ret = new ResultDto<HivCaseReportDto>();
			ret.setCode("200");
			ret.setMesage("Thành công");
			ret.getResponeContent().add(dto);
			return ret;
		}
		else {
			ResultDto<HivCaseReportDto> ret = new ResultDto<HivCaseReportDto>();
			ret.setCode("500");
			ret.setMesage("Thất bại");
			return ret;
		}
	}
	
	@RequestMapping(path = "/post_list",method = RequestMethod.POST)
	public ResultDto<HivCaseReportDto> postList(@RequestBody  List<HivCaseReportDto> dtos) {
		List<HivCaseReportDto> retCases = null;
		try {
			retCases = hivCaseReportService.saveOrUpdateList(dtos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(retCases!=null && retCases.size()>0) {
			ResultDto<HivCaseReportDto> ret = new ResultDto<HivCaseReportDto>();
			ret.setCode("200");
			ret.setMesage("Thành công");
			ret.getResponeContent().addAll(retCases);
			return ret;
		}
		else {
			ResultDto<HivCaseReportDto> ret = new ResultDto<HivCaseReportDto>();
			ret.setCode("500");
			ret.setMesage("Thất bại");
			return ret;
		}
	}
	
	@RequestMapping(path = "/get_list/{pageIndex}/{pageSize}",method = RequestMethod.GET)
	public Page<HivCaseReportDto> getList(@PathVariable int pageIndex,@PathVariable int pageSize) {
		Page<HivCaseReportDto> ret = hivCaseReportService.getList(pageIndex,pageSize);
		return ret;
	}
	@RequestMapping(path = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<HivCaseReportDto> getById(@PathVariable UUID id) {
		HivCaseReportDto result = hivCaseReportService.getById(id);
		return new ResponseEntity<HivCaseReportDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "/postSingleObject/{patientId}", method = RequestMethod.GET)
	public void postSingleObject(@PathVariable UUID patientId) {
		HivCaseReportDto result = hivCaseReportService.getById(patientId);
//		AdapterObjectDto res = new AdapterObjectDto();
//		res.setSingleData(result);
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(result);
	}
}
