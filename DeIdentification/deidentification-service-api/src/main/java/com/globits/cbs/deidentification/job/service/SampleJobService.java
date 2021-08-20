package com.globits.cbs.deidentification.job.service;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globits.cbs.deidentification.domain.LastSyncInfo;
import com.globits.cbs.deidentification.dto.SyncResultDto;
import com.globits.cbs.deidentification.service.LastSyncInfoService;
import com.globits.cbs.es.service.EsPatientService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SampleJobService {

    public static final long EXECUTION_TIME = 10000L;

    private Logger logger = LoggerFactory.getLogger(getClass());
    private int currentPage=0;
    private int pageSize=50;
    private AtomicInteger count = new AtomicInteger();
    @Autowired
    private EsPatientService esPatientService;
    @Autowired
    private LastSyncInfoService lastSyncInfoService;
    public void executeSampleJob() {

        try {
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        	String strFromDate ="2021-05-01";
        	//String strToDate ="2021-05-14";
        	Date fromDate = sdf.parse(strFromDate);
        	Date toDate =LocalDateTime.now().toDate();
        	
        	LastSyncInfo lastSyncInfo= lastSyncInfoService.getLastByCreatedTime();
        	if(lastSyncInfo!=null) {
        		if(lastSyncInfo.getCurrentPage()!=null)
        			currentPage = lastSyncInfo.getCurrentPage();
        		fromDate = lastSyncInfo.getLastSyncTime();
        	}
        	currentPage++;
        	logger.info("currentPage:"+currentPage + " fromDate:"+fromDate+" toDate:"+toDate);
        	SyncResultDto result = esPatientService.synPatientDto(currentPage, pageSize, fromDate, toDate);        	
        	LastSyncInfo info = new LastSyncInfo();
        	int numberPage = result.getTotal()/pageSize;
        	numberPage = numberPage+1;
        	info.setLastSyncNumberPatient(result.getSyncNumberPatient());
        	if(currentPage<numberPage) {
        		info.setLastSyncTime(fromDate);
        		info.setCurrentPage(currentPage);
        	}else {
        		currentPage=0;
        		info.setLastSyncTime(toDate);
        		info.setCurrentPage(0);
        	}
        		
        	info.setCreatedBy("sync");
        	info.setCreateDate(LocalDateTime.now());
        	info.setCurrentPage(currentPage);
        	info.setFromDate(fromDate);
        	info.setToDate(toDate);
        	info.setTotal(result.getTotal());
        	lastSyncInfoService.save(info);
            Thread.sleep(EXECUTION_TIME);
        } catch (Exception e) {
            logger.error("Error while executing sample job", e);
        } finally {
            count.incrementAndGet();
            logger.info("Sample job has finished...");
        }
    }

    public int getNumberOfInvocations() {
        return count.get();
    }
}
