package com.routine.mainapp.iterationOps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.Shared;
import com.routine.mainapp.definitionOps.service.common.logStandart.interfaces.LogInterface;
import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponseStandardized;
import com.routine.mainapp.definitionOps.service.dao.DoubleAttrDao;
import com.routine.mainapp.definitionOps.service.dao.DoubleAttrNumericDao;
import com.routine.mainapp.iterationOps.entity.SPORTI;
import com.routine.mainapp.iterationOps.repository.SPORTIREPO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class ItrationOpsService {
    
    @Autowired
    private SPORTIREPO sportirepo;
    @Autowired
    private ServiceResponseStandardized spz;
    @Autowired
    private Shared shared;

    // SPORTI ---------------------------------------------------------------------------------------------------------
        // GET ALL
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse getAllSportI(HttpServletRequest request){
        try{
            List<SPORTI> dataObtained = sportirepo.findAll();
            return this.spz.OkResponse(dataObtained, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

        // SAVE sportI
    @Transactional
    public void saveSportI(SPORTI data){
        SPORTI item = new SPORTI();
        if(data.getRegistration()==null){
            item.setRegistration(new Date(System.currentTimeMillis()));
        }
        item.setRegistration(data.getRegistration());
        item.setRepN(data.getRepN());
        item.setSetN(data.getSetN());
        item.setSportId(data.getSportId());
        // Convert java.sql.Date to java.util.Date
        java.util.Date utilDate = new java.util.Date(   item.getRegistration().getTime()   );
        // Set the calendar instance with the registration date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(utilDate);
    
        int week = calendar.get(Calendar.WEEK_OF_YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        item.setWeekR(week);
        item.setMonthR(month);
        item.setYearR(year);
        this.sportirepo.saveAndFlush(item);
    }
        // INSERT sportI
    @Transactional
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse insertSPORTI(SPORTI data, HttpServletRequest request){
        if(data == null){
            return this.spz.BadRequestResponse(request);
        }
        try{
            this.saveSportI(data);
            return this.spz.OkResponse(data, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
        // INSERT List of sportI s
    @Transactional
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse insertListSPORTI(List<SPORTI> data, HttpServletRequest request){
        if(data == null || data.size()==0){
            return this.spz.BadRequestResponse(request);
        }
        try{
            for(SPORTI e: data){
                this.saveSportI(e);
            }
            return this.spz.OkResponse(data, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
        // sportPerformance - Main Fucntion
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse sportPerformance(DoubleAttrNumericDao data, HttpServletRequest request){
        Long sport_id = data.getAttr0();
        int time_id   = data.getAttr1();
        if (time_id == this.shared.daily) {
            return this.sportPDaily(sport_id, request);
        }else if (time_id == this.shared.weekly) {
            return this.sportPWeekly(sport_id, request);
        }else if (time_id == this.shared.monthly) {
            return this.sportPMonthly(sport_id, request);
        }else if (time_id == this.shared.yearly) {
            return this.sportPYearly(sport_id, request);
        }
        return null;
    }
        // sportPerformance - timeline daily
    public ServiceResponse sportPDaily(    Long sport_id, HttpServletRequest request    ){
        try{
            List<SPORTI> dataToSend = this.sportirepo.findBySportId(sport_id);
            List<DoubleAttrDao> dataManupulated = new ArrayList<>();
            if(dataToSend!=null){
                for(SPORTI e: dataToSend){
                    DoubleAttrDao item = new DoubleAttrDao();
                    DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                    LocalDate registrationDate = e.getRegistration().toLocalDate();
                    String formattedDate = registrationDate.format(df);
                    item.setAttr0(formattedDate);
                    BigDecimal totalRep = e.getRepN().multiply(e.getSetN());
                    item.setAttr1(totalRep.toString());
                    dataManupulated.add(item);
                }
            }
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
        // sportPerformance - timeline weekly
    public ServiceResponse sportPWeekly(    Long sport_id, HttpServletRequest request    ){
        try{
            List<Integer> distinctWeeks = this.sportirepo.selectDistinctWeeksOFSport(sport_id);
            List<Integer> distinctYears = this.sportirepo.selectDistinctYearsOFSport(sport_id);
            List<DoubleAttrDao> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctWeeks != null && !distinctWeeks.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer week : distinctWeeks) {
                        List<SPORTI> weeklySports = this.sportirepo.selectRowsBYSportAndWeekAndYear(sport_id, week, year);
                        if (weeklySports != null && !weeklySports.isEmpty()) {
                            DoubleAttrDao itemToAdd = new DoubleAttrDao();
                            String tarih= convertWeekToDate(year, week);
                            itemToAdd.setAttr0(tarih);
                            BigDecimal totalRep = BigDecimal.ZERO; 
                            for (SPORTI sport : weeklySports) {
                                totalRep = totalRep.add(sport.getRepN().multiply(sport.getSetN()));
                            }
                            itemToAdd.setAttr1(totalRep.toString());
                            dataManupulated.add(itemToAdd);
                        }
                    }
                }
            }
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
        // sportPerformance - timeline monthly
    public ServiceResponse sportPMonthly(    Long sport_id, HttpServletRequest request    ){
        try{
            List<Integer> distinctMonths = this.sportirepo.selectDistinctMonthsOFSport(sport_id);
            List<Integer> distinctYears = this.sportirepo.selectDistinctYearsOFSport(sport_id);
            List<DoubleAttrDao> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctMonths != null && !distinctMonths.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer month : distinctMonths) {
                        List<SPORTI> monthlySports = this.sportirepo.selectRowsBYSportAndMonthAndYear(sport_id, month, year);
                        if (monthlySports != null && !monthlySports.isEmpty()) {
                            DoubleAttrDao itemToAdd = new DoubleAttrDao();
                            String tarih= convertMonthToDate(year, month);
                            itemToAdd.setAttr0(tarih);
                            BigDecimal totalRep = BigDecimal.ZERO; 
                            for (SPORTI sport : monthlySports) {
                                totalRep = totalRep.add(sport.getRepN().multiply(sport.getSetN()));
                            }
                            itemToAdd.setAttr1(totalRep.toString());
                            dataManupulated.add(itemToAdd);
                        }
                    }
                }
            }
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
        // sportPerformance - timeline yearly
    public ServiceResponse sportPYearly(    Long sport_id, HttpServletRequest request    ){
        try{
            List<DoubleAttrDao> dataManupulated = new ArrayList<>();
            List<Integer> distinctYears = this.sportirepo.selectDistinctYearsOFSport(sport_id);
            if (distinctYears != null && !distinctYears.isEmpty()){
                for (Integer year : distinctYears) {
                    List<SPORTI> yearlySports = this.sportirepo.selectBYSportAndYear(sport_id, year);
                    if (yearlySports != null && !yearlySports.isEmpty()) {
                        DoubleAttrDao itemToAdd = new DoubleAttrDao();
                        String tarih= convertYearToDate(year);
                        itemToAdd.setAttr0(tarih);
                        BigDecimal totalRep = BigDecimal.ZERO; 
                        for (SPORTI e : yearlySports) {
                            totalRep = totalRep.add(e.getRepN().multiply(e.getSetN()));
                        }
                        itemToAdd.setAttr1(totalRep.toString());
                        dataManupulated.add(itemToAdd);
                    }
                }
            }
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }

    }
        // sportPerformance - convertWeekToDate
    public static String convertWeekToDate(int year, int week) {
        // Create a LocalDate for the first day of the specified week in the given year
        Calendar cld = Calendar.getInstance();
        cld.set(Calendar.YEAR, year);
        cld.set(Calendar.WEEK_OF_YEAR, week);
        LocalDate tarih = LocalDate.ofInstant(cld.toInstant(), ZoneId.systemDefault());
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String tarihStr = tarih.format(df);
        return tarihStr;
    }
        // sportPerformance - convertMonthToDate
    public static String convertMonthToDate(int year, int month) {
        // Create a LocalDate for the first day of the specified month in the given year
        LocalDate tarih = LocalDate.of(year, month, 1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tarih.format(df);
    }

    public static String convertYearToDate(int year){
        LocalDate tarih = LocalDate.of(year, 0, 1);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return tarih.format(df);
    }
    

    public LocalDate dummy(DoubleAttrDao data){
        int year = Integer.parseInt(data.getAttr0());
        int month = Integer.parseInt(data.getAttr1());
        LocalDate tarih = LocalDate.of(year, month, 1);
        return tarih;
    }
    // SPORTI ---------------------------------------------------------------------------------------------------------
}
