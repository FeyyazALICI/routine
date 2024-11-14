package com.routine.def.definitionOps.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.def.definitionOps.business.service.auxilary.NameListService;
import com.routine.def.definitionOps.dao.entity.DummyJob;
import com.routine.def.definitionOps.dao.repository.DummyJobRepo;
import com.routine.def.common.logStandart.interfaces.LogInterface;
import com.routine.def.common.logStandart.logEntity.Log;
import com.routine.def.common.logStandart.logEntity.LogRepo;
import com.routine.def.common.serviceResponse.ServiceResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.sql.Date;
import java.util.List;


@Service
public class DefService {
    
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private NameListService nameListService;

    @Autowired
    private DummyJobRepo dummyJobRepo;


    // LOG ------------------------------------------------------------------------------------------------------------
    public List<Log> getAllLogs(){
        return logRepo.findAll();
    }
    // LOG ------------------------------------------------------------------------------------------------------------


    // DUMMY JOB ------------------------------------------------------------------------------------------------------
    @Transactional
    public void insertDummyJob(){
        DummyJob dummyJob = new DummyJob();
        dummyJob.setRecordDate(new Date(System.currentTimeMillis()));
        this.dummyJobRepo.saveAndFlush(dummyJob);
    }
    // DUMMY JOB ------------------------------------------------------------------------------------------------------

        // Sport Types
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse selectDistinctSportTypes(HttpServletRequest request){
        return this.nameListService.selectDistinctSportTypes(request);
    }

        // Project Types
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse selectDistinctProjects(HttpServletRequest request){
        return this.nameListService.selectDistinctProjects(request);
    }


        // Art Types
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse selectDistinctArtTypes(HttpServletRequest request){
        return this.nameListService.selectDistinctArtTypes(request);
    }

    
}
