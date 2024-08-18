package com.routine.mainapp.iterationOps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.dao.TrippleAttrNumericDao;
import com.routine.mainapp.common.logStandart.interfaces.LogInterface;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.common.serviceResponse.ServiceResponseStandardized;
import com.routine.mainapp.iterationOps.entity.DailyIteration;
import com.routine.mainapp.iterationOps.repository.DailyIterationREPO;
import com.routine.mainapp.iterationOps.service.auxilary.DailyPService;
import com.routine.mainapp.iterationOps.service.auxilary.MonthlyPService;
import com.routine.mainapp.iterationOps.service.auxilary.SingleRegistrationService;
import com.routine.mainapp.iterationOps.service.auxilary.TotalPService;
import com.routine.mainapp.iterationOps.service.auxilary.WeeklyPService;
import com.routine.mainapp.iterationOps.service.auxilary.YearlyPService;
import com.routine.mainapp.iterationOps.service.dao.SaveSingleEntityAngularDao;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

// libraries
import sharedlibrary.Constants;

@Service
public class MainService {
    
    @Autowired
    private DailyIterationREPO dailyIterationREPO;
    @Autowired
    private DailyPService dailyPService;
    @Autowired
    private WeeklyPService weeklyPService;
    @Autowired
    private MonthlyPService monthlyPService;
    @Autowired
    private YearlyPService yearlyPService;
    @Autowired
    private TotalPService totalPService;
    @Autowired
    private SingleRegistrationService singleRegistrationService;
    @Autowired
    private ServiceResponseStandardized spz;


        // GET ALL
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse getAllData(HttpServletRequest request){
        try{
            List<DailyIteration> dataObtained = this.dailyIterationREPO.findAll();
            return this.spz.OkResponse(dataObtained, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

        // SAVE dailyIteration
    @Transactional
    public void saveOne(DailyIteration data){
        data.setId(null);
        if(data.getRegistration()==null){
            data.setRegistration(new Date(System.currentTimeMillis()));
        }

        // Convert java.sql.Date to java.util.Date
        java.util.Date utilDate = new java.util.Date(   data.getRegistration().getTime()   );
        // Set the calendar instance with the registration date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(utilDate);
    
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        data.setWeekR(week);
        data.setMonthR(month);
        data.setYearR(year);
        this.dailyIterationREPO.saveAndFlush(data);
    }

        // Insert single entity via angular
    @Transactional
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse saveSingle(SaveSingleEntityAngularDao data, HttpServletRequest request){
        try{
            this.singleRegistrationService.saveSingle(data);
            return this.spz.OkResponse(data, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
        
    }

        // INSERT List of DailyIteration
    @Transactional
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse saveList(List<DailyIteration> data, HttpServletRequest request){
        if(data == null || data.size()==0){
            return this.spz.BadRequestResponse(request);
        }
        try{
            for(DailyIteration e: data){
                this.saveOne(e);
            }
            return this.spz.OkResponse(data, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

        // Main  Performance Fucntion
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse performance(TrippleAttrNumericDao data, HttpServletRequest request){
        System.out.println("in main service");
        System.out.println(data);
        Long category       = data.getAttr0();
        Long subCategory    = data.getAttr1();
        int timeId          = data.getAttr2();
        if (timeId == Constants.DAILY) {
            return this.dailyPService.dailyP(category, subCategory, request);
        }else if (timeId == Constants.WEEKLY) {
            return this.weeklyPService.weeklyP(category, subCategory, request);
        }else if (timeId == Constants.MONTHLY) {
            return this.monthlyPService.monthlyP(category, subCategory, request);
        }else if (timeId == Constants.YEARLY) {
            return this.yearlyPService.yearlyP(category, subCategory, request);
        }else if (timeId == Constants.TOTAL) {
            return this.totalPService.totalP(category, subCategory, request);
        }
        return null;
    }



}

