package com.globits.adapter.eclinica.data.rest;

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

import com.globits.adapter.eclinica.data.dto.AdapterObjectDto;
import com.globits.adapter.eclinica.data.dto.PatientDto;
import com.globits.adapter.eclinica.data.dto.PersonDto;
import com.globits.adapter.eclinica.data.dto.SearchObjectDto;
import com.globits.adapter.eclinica.data.service.PatientService;
import com.globits.adapter.eclinica.data.service.PersonService;
import com.globits.adapter.eclinica.data.types.AdapterObjectType;
import com.globits.adapter.eclinica.utils.RestTemplateUtils;

@RestController
@RequestMapping("/public/adapter")
@CrossOrigin("*")
public class RestAdapterController {

	@Autowired
	PersonService personService;
	@Autowired
	PatientService patientService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public PersonDto getById(@PathVariable Integer id) {
		Long patientId = id.longValue();
		return personService.getById(patientId);
	}

	@RequestMapping(value = "/getById/{patientId}", method = RequestMethod.GET)
	public PatientDto getPatientById(@PathVariable Integer patientId) {
		return patientService.getById(patientId);
	}

	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public List<PatientDto> getList(@RequestBody SearchObjectDto dto) {
		return patientService.getList(dto);
	}

	@RequestMapping(value = "/getPage", method = RequestMethod.POST)
	public Page<PatientDto> getPage(@RequestBody SearchObjectDto dto) {
		return patientService.getPage(dto);
	}

	@RequestMapping(value = "/eclinica/{patientId}", method = RequestMethod.GET)
	public void postSingleObject(@PathVariable Integer patientId) {
		PatientDto dto = patientService.getById(patientId);
		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(com.globits.adapter.eclinica.data.types.AdapterObjectType.SINGLE_DATA);
		ret.setSingleData(dto);

		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(ret);
	}

	@RequestMapping(value = "/listeclinica", method = RequestMethod.POST)
	public void postListObject(@RequestBody SearchObjectDto dto) throws IOException {
		List<PatientDto> list = patientService.getList(dto);
		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.LIST_DATA);
		ret.setListData( new ArrayList<PatientDto>());
		if(list!=null&& list.size()>0){
			ret.getListData().addAll(list);
		}
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(ret);
	}
	@RequestMapping(value = "/pageeclinica", method = RequestMethod.POST)
	public void postListObjectByPage(@RequestBody SearchObjectDto dto) throws IOException {
		Page<PatientDto> page = patientService.getPage(dto);
		List<PatientDto> list =page.getContent();
		AdapterObjectDto ret = new AdapterObjectDto();
		ret.setObjectType(AdapterObjectType.LIST_DATA);
		ret.setListData( new ArrayList<PatientDto>());
		if(list!=null&& list.size()>0){
			ret.getListData().addAll(list);
		}
		ResponseEntity<String> response = RestTemplateUtils.postObjectToOpenHim(ret);
	}
}
