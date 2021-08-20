package com.globits.adapter.pdma.job.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.globits.adapter.pdma.dto.AdapterObjectLogDto;
import com.globits.adapter.pdma.dto.PdmaSearchDto;
import com.globits.adapter.pdma.service.CaseService;

@Service
public class SampleJobService {

	public static final long EXECUTION_TIME = 5000L;

	private Logger logger = LoggerFactory.getLogger(getClass());

	private AtomicInteger count = new AtomicInteger();
	@Autowired
	CaseService caseService;

	public AdapterObjectLogDto executeSampleJob(PdmaSearchDto searchDto) {
		int totalPages = 0;
		AdapterObjectLogDto adapterObjectLogDto = new AdapterObjectLogDto();
		try {
			ResponseEntity<String> response = caseService.splitListObject(searchDto);
			adapterObjectLogDto.setStatus(response.getStatusCode().getReasonPhrase());
			totalPages = caseService.searchCaseByPage(searchDto).getTotalPages();
			adapterObjectLogDto.setTotalPages(totalPages);
			adapterObjectLogDto.setTotalElements(caseService.searchCaseByPage(searchDto).getTotalElements());
			Thread.sleep(EXECUTION_TIME);
		} catch (InterruptedException e) {
			logger.error("Error while executing sample job", e);
		} finally {
			count.incrementAndGet();
			logger.info("Sample job has finished...");
		}
		return adapterObjectLogDto;
	}

	public int getNumberOfInvocations() {
		return count.get();
	}
}
