package com.routine.mainapp.iterationOps.business.service.auxilary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.dto.DoubleAttrDTO;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.common.serviceResponse.ServiceResponseStandardized;
import com.routine.mainapp.dateConverter.DateConverter;
import com.routine.mainapp.iterationOps.dao.entity.DailyIteration;
import com.routine.mainapp.iterationOps.dao.repository.DailyIterationREPO;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// importing libraries
// import sharedlibrary.DateConverter;

@Service
public class WeeklyPService {

    @Autowired
    private DailyIterationREPO dailyIterationREPO;
    @Autowired
    private ServiceResponseStandardized spz;
    
    DateConverter dateConverter = new DateConverter();
    public ServiceResponse artWeeklyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctWeeks = null;
            List<Integer> distinctYears = null;
            distinctWeeks = this.dailyIterationREPO.selectDistinctWeeksArt(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            System.out.println("distinctWeeks: " + distinctWeeks);
            System.out.println("distinctYears: " + distinctYears);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctWeeks != null && !distinctWeeks.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer week : distinctWeeks) {
                        List<DailyIteration> weeklyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndArtIdAndWeekRAndYearR(category, subCategory, week, year);
                        if(weeklyRows!=null && !weeklyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertWeekToDate(year, week)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : weeklyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse sportWeekly(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctWeeks = null;
            List<Integer> distinctYears = null;
            distinctWeeks = this.dailyIterationREPO.selectDistinctWeeksSport(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsSport(subCategory);
            
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctWeeks != null && !distinctWeeks.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer week : distinctWeeks) {
                        List<DailyIteration> weeklyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndSportIdAndWeekRAndYearR(category, subCategory, week, year);
                        if(weeklyRows!=null && !weeklyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertWeekToDate(year, week)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : weeklyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse projectWeekly(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctWeeks = null;
            List<Integer> distinctYears = null;
            distinctWeeks = this.dailyIterationREPO.selectDistinctWeeksProject(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsProject(subCategory);

            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctWeeks != null && !distinctWeeks.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer week : distinctWeeks) {
                        List<DailyIteration> weeklyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndProjectIdAndWeekRAndYearR(category, subCategory, week, year);
                        if(weeklyRows!=null && !weeklyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertWeekToDate(year, week)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : weeklyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse weeklyP(Long category, Long subCategory,  HttpServletRequest request){
        if(category==1){
            return this.artWeeklyP(category, subCategory, request);
        }else if(category == 2){
            return this.sportWeekly(category, subCategory, request);
        }else{
            return this.projectWeekly(category, subCategory, request);
        }
    }
}
