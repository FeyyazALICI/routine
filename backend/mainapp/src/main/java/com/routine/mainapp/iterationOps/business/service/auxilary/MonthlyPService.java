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
public class MonthlyPService {
    
    private final DailyIterationREPO dailyIterationREPO;
    private final ServiceResponseStandardized spz;
    
    @Autowired
    public MonthlyPService(
        DailyIterationREPO dailyIterationREPO,
        ServiceResponseStandardized spz
    ){
        this.dailyIterationREPO = dailyIterationREPO;
        this.spz = spz;
    }


    DateConverter dateConverter = new DateConverter();

    public ServiceResponse artMonthlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctMonths = null;
            List<Integer> distinctYears = null;
            distinctMonths = this.dailyIterationREPO.selectDistinctMonthsArt(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctMonths != null && !distinctMonths.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer month : distinctMonths) {
                        List<DailyIteration> monthlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndArtIdAndMonthRAndYearR(category, subCategory, month, year);
                        if(monthlyRows!=null && !monthlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertMonthToDate(year, month)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : monthlyRows) {
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
    public ServiceResponse sportMonthlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctMonths = null;
            List<Integer> distinctYears = null;
            distinctMonths = this.dailyIterationREPO.selectDistinctMonthsArt(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctMonths != null && !distinctMonths.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer month : distinctMonths) {
                        List<DailyIteration> monthlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndSportIdAndMonthRAndYearR(category, subCategory, month, year);
                        if(monthlyRows!=null && !monthlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertMonthToDate(year, month)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : monthlyRows) {
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
    public ServiceResponse projectMonthlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctMonths = null;
            List<Integer> distinctYears = null;
            distinctMonths = this.dailyIterationREPO.selectDistinctMonthsArt(subCategory);
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty() && distinctMonths != null && !distinctMonths.isEmpty()) {
                for (Integer year : distinctYears) {
                    for (Integer month : distinctMonths) {
                        List<DailyIteration> monthlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndProjectIdAndMonthRAndYearR(category, subCategory, month, year);
                        if(monthlyRows!=null && !monthlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertMonthToDate(year, month)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : monthlyRows) {
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
    public ServiceResponse monthlyP(Long category, Long subCategory,  HttpServletRequest request){
        if(category==1){
            return this.artMonthlyP(category, subCategory, request);
        }else if(category == 2){
            return this.sportMonthlyP(category, subCategory, request);
        }else{
            return this.projectMonthlyP(category, subCategory, request);
        }
    }
}
