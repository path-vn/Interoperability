package com.globits.hiv.receive.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globits.hiv.receive.domain.HivCaseReport;
import com.globits.hiv.receive.dto.HivCaseReportDto;
import com.globits.hiv.receive.dto.UserSystemCodeDto;
import com.globits.hiv.receive.repository.HivCaseReportRepository;
import com.globits.hiv.receive.repository.UserSystemCodeRepository;
import com.globits.hiv.receive.service.HivCaseReportService;
import com.globits.security.domain.User;
import com.google.gson.Gson;

@Service
public class HivCaseReportServiceImpl implements HivCaseReportService {
	@Autowired
	HivCaseReportRepository hivCaseReportRepository;
	@Autowired
	UserSystemCodeRepository userSystemCodeRepository;
	
	@Override
	public HivCaseReportDto saveOrUpdate(HivCaseReportDto dto) {
		if(dto!=null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User modifiedUser = null;
			String currentUserName = "Unknown User";
			if (authentication != null) {
				modifiedUser = (User) authentication.getPrincipal();
				if(modifiedUser!=null) {
					currentUserName = modifiedUser.getUsername();
				}
			}
			
			HivCaseReport entity = null;
			if(dto.getId()!=null && dto.getId()!=new UUID(0L, 0L)) {
				entity = hivCaseReportRepository.getOne(dto.getId());				
			}
			
			if(entity==null) {
				entity = new HivCaseReport();
				entity.setCreateDate(new Date());
				entity.setCreatedBy(currentUserName);
			}
			entity.setModifyDate(new Date());
			entity.setModifiedBy(currentUserName);
//			entity.setFacilityId(dto.getFacilityId());
			if(dto.getReportDate()!=null) {
				entity.setReportDate(dto.getReportDate());
			}
			else {
				entity.setReportDate(new Date());
			}
			if(modifiedUser!=null) {
				UserSystemCodeDto codeDto = userSystemCodeRepository.getDtoByUser(modifiedUser.getId());
				if(codeDto!=null) {
					entity.setSystemId(codeDto.getSystemCode());
				}
			}			
//			entity.setInstructions(dto.getInstructions());
			entity.setTitle(dto.getTitle());
			if(dto.getPatientDto()!=null) {
				ObjectMapper objectMapper = new ObjectMapper();
		        String jsonInString="";
				try {
					jsonInString = objectMapper.writeValueAsString(dto.getPatientDto());
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//		         System.out.println(jsonInString);				
			    entity.setContent(jsonInString);
			}
			entity = hivCaseReportRepository.save(entity);
			return new HivCaseReportDto(entity);
		}
		return null;
	}
	@Override
	public List<HivCaseReportDto> saveOrUpdateList(List<HivCaseReportDto> dtos) {
		List<HivCaseReportDto> ret = new ArrayList<HivCaseReportDto>();
		if(dtos!=null && dtos.size()>0) {
			for (HivCaseReportDto hivCaseReportDto : dtos) {
				hivCaseReportDto = this.saveOrUpdate(hivCaseReportDto);
				ret.add(hivCaseReportDto);
			}
		}
		return ret;
	}
	
	@Override
	public HivCaseReportDto getById(UUID id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User modifiedUser = null;
		String currentUserName = "Unknown User";
		if (authentication != null) {
			modifiedUser = (User) authentication.getPrincipal();
		}
		String systemCode="";
		if(modifiedUser!=null) {
			UserSystemCodeDto codeDto = userSystemCodeRepository.getDtoByUser(modifiedUser.getId());
			if(codeDto!=null) {
				systemCode = codeDto.getSystemCode();
			}
		}
		if(StringUtils.hasText(systemCode)) {
			HivCaseReport ret = hivCaseReportRepository.getOne(id);
			if(ret!=null && ret.getSystemId().equals(systemCode)) {		
				return new HivCaseReportDto(ret);
			}
		}
		
		return null;
	}
	
	@Override
	public Page<HivCaseReportDto> getList(int pageIndex, int pageSize){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User modifiedUser = null;
		String currentUserName = "Unknown User";
		if (authentication != null) {
			modifiedUser = (User) authentication.getPrincipal();
		}
		String systemCode="";
		if(modifiedUser!=null) {
			UserSystemCodeDto codeDto = userSystemCodeRepository.getDtoByUser(modifiedUser.getId());
			if(codeDto!=null) {
				systemCode = codeDto.getSystemCode();
			}
		}
		
		if(pageIndex<1) {
			pageIndex=0;
		}
		else {
			pageIndex-=1;
		}
		if(pageSize<1) {
			pageSize=25;
		}
		PageRequest page = PageRequest.of(pageIndex, pageSize);		
		if(StringUtils.hasText(systemCode)) {
			return hivCaseReportRepository.getListPage(systemCode,page);
		}
		else {
			return null;
		}
	}
}
