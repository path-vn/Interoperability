package com.globits.adapter.eclinica.data.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.globits.adapter.eclinica.data.domain.Person;
import com.globits.adapter.eclinica.data.dto.PersonDto;
import com.globits.adapter.eclinica.data.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PersonDto getById(Long id) {
		String hql = "from Person p where p.id=:id";
		Query q = entityManager.createQuery(hql);
		q.setParameter("id", id.intValue());
		Object result = q.getSingleResult();
		if (result != null) {
			Person p = (Person) result;
			return new PersonDto(p);
		}

		return null;
	}

}
