package com.routine.def.definitionOps.job.scheduler;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.routine.def.definitionOps.job.jobs.DummyJob;
import com.routine.def.definitionOps.job.jobs.DummyJob2;

@Component
public class DummyScheduler implements CommandLineRunner {

    private final Scheduler scheduler;

    //  CRON exprressions:
    String everyMinute = "0 0/1 * 1/1 * ? *";
    
    @Autowired
    public DummyScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run(String... args) throws SchedulerException {
        // Schedule DummyJob
        JobDetail dummyJob = JobBuilder.newJob(DummyJob.class)
                .withIdentity("dummyJob", "group1")
                .build();

        Trigger dummyTrigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyTrigger", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(everyMinute))
                .build();

        scheduler.scheduleJob(dummyJob, dummyTrigger);

        // Schedule DummyJob2
        JobDetail dummyJob2 = JobBuilder.newJob(DummyJob2.class)
                .withIdentity("dummyJob2", "group1")
                .build();

        Trigger dummyJob2Trigger = TriggerBuilder.newTrigger()
                .withIdentity("dummyJob2Trigger", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(everyMinute))
                .build();

        scheduler.scheduleJob(dummyJob2, dummyJob2Trigger);

        // Start the scheduler
        scheduler.start();
    }
}

/*
 * Created lastly in fourth place
 * DummyScheduler: This component implements CommandLineRunner and will be executed after the application context is fully initialized. 
 * It schedules the job to run every minute. The scheduler bean is autowired into this class, ensuring it is created and available before the run method is called.
 */
