package com.globits.adapter.eclinica.job.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.globits.adapter.eclinica.data.dto.AdapterObjectLogDto;
import com.globits.adapter.eclinica.data.dto.SearchObjectDto;
import com.globits.adapter.eclinica.data.service.PatientService;

@Service
public class SampleJobService {

	public static final long EXECUTION_TIME = 5000L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	private int currentPage = 0;
	private int pageSize = 40;
	private AtomicInteger count = new AtomicInteger();

    @Autowired
    private PatientService patientService;
//    @Autowired
//    private LastSyncInfoService lastSyncInfoService;
	public AdapterObjectLogDto executeSampleJob(SearchObjectDto dto) {
		int totalPages = 0;
		AdapterObjectLogDto logDto = new AdapterObjectLogDto();
		try {		
			ResponseEntity<String> response = patientService.splitListObject(dto);
			logDto.setStatus(response.getStatusCode().getReasonPhrase());
			totalPages = patientService.getPage(dto).getTotalPages();
			logDto.setTotalPages(totalPages);
			logDto.setTotalElements(patientService.getPage(dto).getTotalElements());
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String strFromDate = "2021-05-01";
//			// String strToDate ="2021-05-14";
//			Date fromDate = sdf.parse(strFromDate);
//			Date toDate = LocalDateTime.now().toDate();

//        	LastSyncInfo lastSyncInfo= lastSyncInfoService.getLastByCreatedTime();
//        	if(lastSyncInfo!=null) {
//        		if(lastSyncInfo.getCurrentPage()!=null)
//        			currentPage = lastSyncInfo.getCurrentPage();
//        		fromDate = lastSyncInfo.getLastSyncTime();
//        	}
//        	currentPage++;
//        	SyncResultDto result = esPatientService.synPatientDto(currentPage, pageSize, fromDate, toDate);        	
//        	LastSyncInfo info = new LastSyncInfo();
//        	int numberPage = result.getTotal()/pageSize;
//        	info.setLastSyncNumberPatient(result.getSyncNumberPatient());
//        	if(currentPage<numberPage) {
//        		info.setLastSyncTime(fromDate);
//        		info.setCurrentPage(currentPage);
//        	}else {
//        		info.setLastSyncTime(toDate);
//        		info.setCurrentPage(0);
//        	}
//        		
//        	info.setCreatedBy("sync");
//        	info.setCreateDate(LocalDateTime.now());
//        	info.setCurrentPage(currentPage);
//        	lastSyncInfoService.save(info);
			Thread.sleep(EXECUTION_TIME);
		} catch (Exception e) {
			logger.error("Error while executing sample job", e);
		} finally {
			count.incrementAndGet();
			logger.info("Sample job has finished...");
		}
		return logDto;
	}

	public int getNumberOfInvocations() {
		return count.get();
	}
}
