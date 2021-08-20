package com.globits.adapter.pdma.job;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.globits.adapter.pdma.Constants;
import com.globits.adapter.pdma.dto.AdapterObjectLogDto;
import com.globits.adapter.pdma.dto.PdmaSearchDto;
import com.globits.adapter.pdma.dto.SerializeObjectDto;
import com.globits.adapter.pdma.job.service.SampleJobService;
import com.globits.adapter.pdma.service.CaseService;
@Component
public class SampleJob implements Job {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SampleJobService jobService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private Environment env;
	static public int pageIndex = Constants.pageIndex;
	static public int pageSize = Constants.pageSize;
//	static public LocalDateTime lastSynDate = LocalDateTime.of(2021, 01, 01, 00, 00, 00, 00);
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Job ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
//		logger.info("CaseService:" + caseService);
		logger.info("Page Index:" + pageIndex);
		
		PdmaSearchDto searchDto = new PdmaSearchDto();
		searchDto.setPageIndex(pageIndex);
		searchDto.setPageSize(pageSize);
//		searchDto.setLastSynDate(lastSynDate);
		Date nowDate = new Date();
		AdapterObjectLogDto adapterObjectLogDto = jobService.executeSampleJob(searchDto);
		int totalPages = adapterObjectLogDto.getTotalPages();
		pageIndex++;

		if(pageIndex > totalPages) {
			try {
				String path = "";
				if (env.getProperty("opc.file.folder") != null) {
					path = env.getProperty("opc.file.folder");
				}
				FileOutputStream fileOut = null;
				File file = new File(path+"SerializeObject.txt");
				SerializeObjectDto serializeObjectDto = new SerializeObjectDto();
//				Date date = Date.from( lastSynDate.atZone( ZoneId.systemDefault()).toInstant());
//				serializeObjectDto.setDateGetData(date);
				serializeObjectDto.setTimeStartJob(nowDate);
				serializeObjectDto.setTotalRecord(adapterObjectLogDto.getTotalElements());
				if(!file.exists()) {
	                //Create new file answer.txt with absoluatePath
	                fileOut = new FileOutputStream(path +"SerializeObject.txt");
	                file.createNewFile();
	                ObjectOutputStream out = new ObjectOutputStream(fileOut);
			        out.writeObject(serializeObjectDto);
			        out.close();
			        fileOut.close();

	            }else {
	            	fileOut = new FileOutputStream(path +"SerializeObject.txt");
	                ObjectOutputStream out = new ObjectOutputStream(fileOut);
			        out.writeObject(serializeObjectDto);
			        out.close();
			        fileOut.close();
	            }
				context.getScheduler().shutdown();
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			logger.info("Complete!!");
			
		} else {
			logger.info("Next job scheduled @ {}", context.getNextFireTime());
		}
		
	}
}
