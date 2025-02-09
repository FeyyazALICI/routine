package com.routine.mainapp.iterationOps.business.service.auxilary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.dto.DoubleAttrDTO;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.common.serviceResponse.ServiceResponseStandardized;
import com.routine.mainapp.iterationOps.dao.entity.DailyIteration;
import com.routine.mainapp.iterationOps.dao.repository.DailyIterationREPO;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TotalPService {
    
    private final DailyIterationREPO dailyIterationREPO;
    private final ServiceResponseStandardized spz;
    
    @Autowired
    public TotalPService(
        DailyIterationREPO dailyIterationREPO,
        ServiceResponseStandardized spz
    ){
        this.dailyIterationREPO = dailyIterationREPO;
        this.spz = spz;
    }

    public ServiceResponse artTotalP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            List<DailyIteration> data = this.dailyIterationREPO.findByCategoryIdAndArtId(category, subCategory);
            DoubleAttrDTO item = new DoubleAttrDTO();
            item.setAttr0(   (new Date(System.currentTimeMillis())).toString()   );
            BigDecimal kpi = BigDecimal.ZERO;
            if(data!=null && !data.isEmpty()){
                for(DailyIteration e: data){
                    if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                        kpi = kpi.add(e.getMinOrPagesOrRep());
                    }
                }
            }
            item.setAttr1(kpi.toString());
            dataManupulated.add(item);
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
    public ServiceResponse sportTotalP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            List<DailyIteration> data = this.dailyIterationREPO.findByCategoryIdAndSportId(category, subCategory);
            DoubleAttrDTO item = new DoubleAttrDTO();
            item.setAttr0(   (new Date(System.currentTimeMillis())).toString()   );
            BigDecimal kpi = BigDecimal.ZERO;
            if(data!=null && !data.isEmpty()){
                for(DailyIteration e: data){
                    if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                        kpi = kpi.add(e.getMinOrPagesOrRep());
                    }
                }
            }
            item.setAttr1(kpi.toString());
            dataManupulated.add(item);
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
    public ServiceResponse projectTotalP(Long category, Long subCategory,  HttpServletRequest request){
        try{
            List<DoubleAttrDTO> dataManupulated = new ArrayList<>();
            List<DailyIteration> data = this.dailyIterationREPO.findByCategoryIdAndProjectId(category, subCategory);
            DoubleAttrDTO item = new DoubleAttrDTO();
            item.setAttr0(   (new Date(System.currentTimeMillis())).toString()   );
            BigDecimal kpi = BigDecimal.ZERO;
            if(data!=null && !data.isEmpty()){
                for(DailyIteration e: data){
                    if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                        kpi = kpi.add(e.getMinOrPagesOrRep());
                    }
                }
            }
            item.setAttr1(kpi.toString());
            dataManupulated.add(item);
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
    public ServiceResponse totalP(Long category, Long subCategory,  HttpServletRequest request){
        if(category==1){
            return this.artTotalP(category, subCategory, request);
        }else if(category == 2){
            return this.sportTotalP(category, subCategory, request);
        }else{
            return this.projectTotalP(category, subCategory, request);
        }
    }
}
