package com.globits.adapter.eclinica.data.service.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.globits.adapter.eclinica.data.domain.Obs;
import com.globits.adapter.eclinica.data.domain.Person;
import com.globits.adapter.eclinica.data.dto.PatientDto;
import com.globits.adapter.eclinica.data.dto.PersonDto;
import com.globits.adapter.eclinica.data.service.ObsService;
import com.globits.adapter.eclinica.data.service.PersonService;

@Service
public class ObsServiceImpl implements ObsService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Obs> getListByPersonId(Integer personId) {
		String hql = "from Obs o where  o.person.id=:id";
		hql += " order by o.obsDatetime asc ";
		Query q = entityManager.createQuery(hql, Obs.class);
		q.setParameter("id", personId);
		List<Obs> page =q.getResultList();
				
		return page;
	}

}
