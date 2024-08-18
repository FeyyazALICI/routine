package com.routine.def.definitionOps.job.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
public class DummyJob implements Job{
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        System.out.println("Hello");
    }
}



/*
 * Created in 3rd place after SpringJobFactory.
 * DummyJob: The DummyJob class is a component managed by Spring. It will be instantiated as needed by the scheduler when a job is triggered.
 */
