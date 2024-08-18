package com.routine.mainapp.iterationOps.service.auxilary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.common.dao.DoubleAttrDao;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.common.serviceResponse.ServiceResponseStandardized;
import com.routine.mainapp.iterationOps.entity.DailyIteration;
import com.routine.mainapp.iterationOps.repository.DailyIterationREPO;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class DailyPService {
    @Autowired
    private DailyIterationREPO dailyIterationREPO;
    @Autowired
    private ServiceResponseStandardized spz;

    public String dateAssign(DailyIteration data){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate registrationDate = data.getRegistration().toLocalDate();
        String formattedDate = registrationDate.format(df);
        return formattedDate;
    }
    public ServiceResponse dailyP(Long category, Long subCategory, HttpServletRequest request){
        try{
            List<DailyIteration> dataToSend = null;
            if(category==1){
                dataToSend = this.dailyIterationREPO.findByArtId(subCategory);
            }else if(category==2){
                dataToSend = this.dailyIterationREPO.findBySportId(subCategory);
            }else{
                dataToSend = this.dailyIterationREPO.findByProjectId(subCategory);
            }
            
            List<DoubleAttrDao> dataManupulated = new ArrayList<>();
            if(dataToSend!=null){
                for(DailyIteration e: dataToSend){
                    DoubleAttrDao item = new DoubleAttrDao();

                    item.setAttr0(   dateAssign(e)   );
                    BigDecimal kpi = BigDecimal.ZERO;
                    if(e.getMinOrPagesOrRep()!=null && e.getMinOrPagesOrRep() instanceof BigDecimal){
                        kpi = kpi.add(e.getMinOrPagesOrRep());
                    }
                    item.setAttr1(kpi.toString());
                    dataManupulated.add(item);
                }
            }
            return this.spz.OkResponse(dataManupulated, request);
        }catch(Exception e){
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
}
