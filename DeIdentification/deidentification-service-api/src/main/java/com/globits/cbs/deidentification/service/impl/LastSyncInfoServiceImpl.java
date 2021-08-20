package com.globits.cbs.deidentification.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.globits.cbs.deidentification.domain.LastSyncInfo;
import com.globits.cbs.deidentification.service.LastSyncInfoService;

@Transactional
@Service
public class LastSyncInfoServiceImpl extends GenericServiceImpl<LastSyncInfo, UUID> implements LastSyncInfoService
{
	@Autowired
	EntityManager entityManager;
	@Override
	public LastSyncInfo getLastByCreatedTime() {
		Query query = entityManager.createQuery("from LastSyncInfo order by createDate DESC");
		query.setMaxResults(1);
		List<LastSyncInfo> list = query.getResultList();
		if(list!=null && list.size()>0) {
			return list.get(0);
		}
		return null;
	}
}
