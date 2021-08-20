package com.globits.adapter.eclinica.data.service;

import java.util.List;

import com.globits.adapter.eclinica.data.domain.Obs;
import com.globits.adapter.eclinica.data.dto.PersonDto;

public interface ObsService {
	public List<Obs> getListByPersonId(Integer personId) ;
}
