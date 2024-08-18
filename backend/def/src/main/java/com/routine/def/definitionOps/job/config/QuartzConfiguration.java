package com.routine.def.definitionOps.job.config;

import org.quartz.spi.JobFactory;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfiguration {

    private final AutowireCapableBeanFactory capableBeanFactory;

    public QuartzConfiguration(AutowireCapableBeanFactory capableBeanFactory) {
        this.capableBeanFactory = capableBeanFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory());
        return factory;
    }

    // below 2 beans instentiates job factory
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        return factory.getScheduler();
    }

    @Bean
    public JobFactory jobFactory() {
        return new AdaptableJobFactory() {
            @Override
            protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
                Object jobInstance = super.createJobInstance(bundle);
                capableBeanFactory.autowireBean(jobInstance);
                return jobInstance;
            }
        };
    }
}



/*
 * This configuration class (QuartzConfig) remains unchanged regardless of the number of jobs or schedules you define.
 */


/*
 * QuartzConfig: This configuration class is processed first. Spring initializes the SchedulerFactoryBean and Scheduler beans. 
 * During this process, the JobFactory (in this case, SpringJobFactory) is also set up.
 */

 /*
  * @Bean
  * Managed by: 
  In Spring Boot, a bean is an object that is managed by the Spring IoC (Inversion of Control) container. 
Beans are central to the Spring framework and are typically instantiated, configured, and managed by Spring's IoC container.
  * Definition: 
  A bean is a Java object that is instantiated, assembled, and managed by a Spring IoC container. 
The container creates the bean, wires its dependencies, and manages its lifecycle from creation to destruction.
  */
