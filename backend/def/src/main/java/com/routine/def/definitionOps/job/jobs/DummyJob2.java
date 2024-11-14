package com.routine.def.definitionOps.job.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.routine.def.definitionOps.business.service.DefService;


@Component
public class DummyJob2 implements Job {

    @Autowired
    private DefService defService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException{
        this.defService.insertDummyJob();
    }

}