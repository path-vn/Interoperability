package com.globits.scheduler;

import java.io.IOException;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.globits.adapter.opcassist.job.SampleJob;
import com.globits.scheduler.quartz.AutoWiringSpringBeanJobFactory;
@Configuration
@ConditionalOnExpression("'${using.spring.schedulerFactory}'=='routine'")
public class QrtzScheduler {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        logger.info("Your Data will be post routine automatically...");
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

        return JobBuilder.newJob().ofType(SampleJob.class).storeDurably().withIdentity(JobKey.jobKey("Qrtz_Job_Detail")).withDescription("Invoke Sample Job service...").build();
    }
    
    @Bean
    public Trigger trigger(JobDetail job) {

        int frequencyInSec = 200;
        logger.info("Configuring trigger to fire every {} seconds", frequencyInSec);

        //return newTrigger().forJob(job).withIdentity(TriggerKey.triggerKey("Qrtz_Trigger")).withDescription("Sample trigger").withSchedule(simpleSchedule().withIntervalInSeconds(frequencyInSec).repeatForever()).build();
        Trigger yourTrigger = TriggerBuilder
                .newTrigger()
                .withIdentity("TRIGGER-ID", "TRIGGER-GROUP")
                .withSchedule(
                    CronScheduleBuilder
                         .cronSchedule("0 40 17 * * ?")//Fire at 16:40 every day
                         .inTimeZone(TimeZone.getTimeZone("GMT+7"))).build();
         return yourTrigger;
    }
}
