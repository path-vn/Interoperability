package com.globits.cbs.deidentification.job;
import static org.quartz.JobBuilder.newJob;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.globits.cbs.deidentification.job.config.AutoWiringSpringBeanJobFactory;
import com.globits.cbs.es.util.Constant;
import com.globits.fhir.hiv.service.impl.HAPIFhirHivPatientService;
import com.globits.fhir.hiv.utils.HivConst;

import java.util.Date;
import java.util.TimeZone;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='true'")
public class QrtzScheduler {
	public static Date LastSyncDate;
	public static int frequencyInSec = 600;//10 minutes
    Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private Environment env;
    
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
		HivConst.serverFhirUrl =env.getProperty("serverBaseUrl");
		if(env.getProperty("frequencyInSec")!=null) {
			frequencyInSec = Integer.parseInt(env.getProperty("frequencyInSec"));	
		}
//		HAPIFhirHivPatientService.serverBaseUrl=HivConst.serverFhirUrl;
		Constant.PATIENT_INDEX =env.getProperty("patient_index");    	
        logger.info("Hello world from Quartz...");
    }

    @Bean
    public SpringBeanJobFactory springBeanJobFactory() {
        AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
        logger.debug("Configuring Job factory");

        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public Scheduler scheduler(Trigger trigger, JobDetail job, SchedulerFactoryBean factory) throws SchedulerException {
        logger.debug("Getting a handle to the Scheduler");
        Scheduler scheduler = factory.getScheduler();
        scheduler.scheduleJob(job, trigger);

        logger.debug("Starting Scheduler threads");
        scheduler.start();
        return scheduler;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(springBeanJobFactory());
        //factory.setQuartzProperties(quartzProperties());
        return factory;
    }
    @Bean
    public JobDetail jobDetail() {

        return newJob().ofType(SampleJob.class).storeDurably().withIdentity(JobKey.jobKey("Qrtz_Job_Detail")).withDescription("Invoke Sample Job service...").build();
    }

    @Bean
    public Trigger trigger(JobDetail job) {

        
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("Qrtz_Trigger")).withDescription("Sample trigger").withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever()).build();
//        Trigger yourTrigger = TriggerBuilder
//                .newTrigger()
//                .withIdentity("TRIGGER-ID", "TRIGGER-GROUP")
//                .withSchedule(CronScheduleBuilder
//                         .cronSchedule("0 40 16 * * ?")//Fire at 16:40 every day
//                         .inTimeZone(TimeZone.getTimeZone("GMT+7"))).build();
//         return yourTrigger;
    }    
}
