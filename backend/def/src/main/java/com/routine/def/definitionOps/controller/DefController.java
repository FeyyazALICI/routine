package com.routine.def.definitionOps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.def.definitionOps.service.DefService;
import com.routine.def.common.logStandart.logEntity.Log;
import com.routine.def.common.serviceResponse.ServiceResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/d")   // /d->def
public class DefController {
    
    @Autowired
    private DefService defService;

    @GetMapping("/log")
    public List<Log> getAllLogs(){
        return this.defService.getAllLogs();
    }


    // distinct types --------------------------------------------------------
    @GetMapping("/type/sport")
    public ServiceResponse selectDistinctSportTypes( HttpServletRequest request ){
        return this.defService.selectDistinctSportTypes(request);
    }
    @GetMapping("/type/art")
    public ServiceResponse selectDistinctArtTypes( HttpServletRequest request ){
        return this.defService.selectDistinctArtTypes(request);
    }
    @GetMapping("/type/project")
    public ServiceResponse selectDistinctProjects( HttpServletRequest request ){
        return this.defService.selectDistinctProjects(request);
    }
    // -----------------------------------------------------------------------
}
