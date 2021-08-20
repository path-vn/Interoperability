package com.globits.cbs.deidentification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.domain.LastSyncInfo;
import com.globits.cbs.es.service.EsPatientService;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public interface LastSyncInfoService extends GenericService<LastSyncInfo,UUID>{

    public static final long EXECUTION_TIME = 5000L;

	public LastSyncInfo getLastByCreatedTime();


}
