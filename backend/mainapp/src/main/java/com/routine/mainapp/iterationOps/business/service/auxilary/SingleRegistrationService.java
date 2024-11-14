package com.routine.mainapp.iterationOps.business.service.auxilary;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.constants.Constants;
import com.routine.mainapp.iterationOps.business.dto.SaveSingleEntityAngularDTO;
import com.routine.mainapp.iterationOps.dao.entity.DailyIteration;
import com.routine.mainapp.iterationOps.dao.repository.DailyIterationREPO;

import jakarta.transaction.Transactional;

// importing libraries
// import sharedlibrary.Constants;

@Service
public class SingleRegistrationService {
    @Autowired
    private DailyIterationREPO dailyIterationREPO;

    Constants constants = new Constants();

        // Insert single entity via angular
    @Transactional
    public void saveSingle(SaveSingleEntityAngularDTO data){
        int category = data.getCategory();
        int subcategory = data.getSubcategory();
        BigDecimal rep = data.getKpi();
        int timeLine = data.getTimeLine();
        System.out.println(timeLine);
        DailyIteration dataToSave = new DailyIteration();
        dataToSave.setId(null);
        dataToSave.setMinOrPagesOrRep(rep);

        Date today = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);     // setting today as current date
        cal.add(Calendar.DAY_OF_MONTH, -1); // yesterday
        Date yesterday = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DAY_OF_MONTH, -1); // day before
        Date dayBeforeYesterday = new Date(cal.getTimeInMillis());
        // deciding time
        if(timeLine==constants.TODAY){
            dataToSave.setRegistration(today);
        }else if(timeLine==constants.YESTERDAY){
            dataToSave.setRegistration(yesterday);
        }else{
            dataToSave.setRegistration(dayBeforeYesterday);
        }

        // Convert java.sql.Date to java.util.Date
        java.util.Date utilDate = new java.util.Date(   dataToSave.getRegistration().getTime()   );
        // Set the calendar instance with the registration date
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(utilDate);
    
        int week = calendar2.get(Calendar.WEEK_OF_YEAR);
        int month = calendar2.get(Calendar.MONTH) + 1;
        int year = calendar2.get(Calendar.YEAR);
        dataToSave.setWeekR(week);
        dataToSave.setMonthR(month);
        dataToSave.setYearR(year);

        // deciding category
        dataToSave.setCategoryId((long) category);

        // deciding subcategory
        if(category==1){
            dataToSave.setArtId((long) subcategory);
        }else if(category==2){
            dataToSave.setSportId((long) subcategory);
        }else{
            dataToSave.setProjectId((long) subcategory);
        }

        this.dailyIterationREPO.saveAndFlush(dataToSave);
    }
}
