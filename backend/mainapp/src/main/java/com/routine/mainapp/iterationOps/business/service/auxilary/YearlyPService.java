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
public class YearlyPService {
    
    private final DailyIterationREPO dailyIterationREPO;
    private final ServiceResponseStandardized spz;
    
    @Autowired
    public YearlyPService(
        DailyIterationREPO dailyIterationREPO,
        ServiceResponseStandardized spz
    ){
        this.dailyIterationREPO = dailyIterationREPO;
        this.spz = spz;
    }

    DateConverter dateConverter = new DateConverter();

    public ServiceResponse artYearlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctYears = null;
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty()) {
                for (Integer year : distinctYears) {
                        List<DailyIteration> yearlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndArtIdAndYearR(category, subCategory, year);
                        if(yearlyRows!=null && !yearlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertYearToDate(year)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : yearlyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse sportYearlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctYears = null;
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty()) {
                for (Integer year : distinctYears) {
                        List<DailyIteration> yearlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndSportIdAndYearR(category, subCategory, year);
                        if(yearlyRows!=null && !yearlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertYearToDate(year)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : yearlyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse projectYearlyP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<Integer> distinctYears = null;
            distinctYears = this.dailyIterationREPO.selectDistinctYearsArt(subCategory);
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            if (distinctYears != null && !distinctYears.isEmpty()) {
                for (Integer year : distinctYears) {
                        List<DailyIteration> yearlyRows = 
                            this.dailyIterationREPO.findByCategoryIdAndProjectIdAndYearR(category, subCategory, year);
                        if(yearlyRows!=null && !yearlyRows.isEmpty()){
                            DoubleAttrDTO itemToAdd = new DoubleAttrDTO();
                            itemToAdd.setAttr0(   dateConverter.convertYearToDate(year)   );

                            BigDecimal kpi = BigDecimal.ZERO; 
                            for (DailyIteration e : yearlyRows) {
                                if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                                    kpi=kpi.add(e.getMinOrPagesOrRep());
                                }
                            }
                            itemToAdd.setAttr1(kpi.toString());
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
    public ServiceResponse yearlyP(Long category, Long subCategory,  HttpServletRequest request){
        if(category==1){
            return this.artYearlyP(category, subCategory, request);
        }else if(category == 2){
            return this.sportYearlyP(category, subCategory, request);
        }else{
            return this.projectYearlyP(category, subCategory, request);
        }
    }
}
