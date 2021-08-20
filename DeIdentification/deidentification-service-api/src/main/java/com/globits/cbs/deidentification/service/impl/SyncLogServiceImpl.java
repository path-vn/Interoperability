package com.globits.cbs.deidentification.service.impl;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.globits.cbs.deidentification.domain.Sample;
import com.globits.cbs.deidentification.domain.SyncLog;
import com.globits.cbs.deidentification.dto.SampleDto;
import com.globits.cbs.deidentification.functiondto.SampleSearchDto;
import com.globits.cbs.deidentification.repository.SampleRepository;
import com.globits.cbs.deidentification.repository.SyncLogRepository;
import com.globits.cbs.deidentification.service.SampleService;
import com.globits.cbs.deidentification.service.SyncLogService;

@Transactional
@Service
public class SyncLogServiceImpl extends GenericServiceImpl<SyncLog, UUID> implements SyncLogService {

	@Autowired
	private EntityManager manager;

	@Autowired
	public SyncLogRepository syncLogRepository;

}
