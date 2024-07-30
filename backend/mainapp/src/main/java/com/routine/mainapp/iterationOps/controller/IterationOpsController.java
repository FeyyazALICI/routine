package com.routine.mainapp.iterationOps.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.definitionOps.service.dao.DoubleAttrDao;
import com.routine.mainapp.definitionOps.service.dao.DoubleAttrNumericDao;
import com.routine.mainapp.iterationOps.entity.SPORTI;
import com.routine.mainapp.iterationOps.service.ItrationOpsService;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/i")   // /i->iteration
@RestController
public class IterationOpsController {
    
    @Autowired
    private ItrationOpsService iOService;

    // ------------------------------------------------------------------------------------------------
    @GetMapping("/sportAll")
    public ServiceResponse getAllSportI(HttpServletRequest request){
        return iOService.getAllSportI(request);
    }
    @PostMapping("/sport")      // save single entity
    public ServiceResponse insertSPORTI(@RequestBody SPORTI data ,HttpServletRequest request){
        return iOService.insertSPORTI(data, request);
    }
    @PostMapping("/sportAll")      // save list of entities
    public ServiceResponse insertListSPORTI(@RequestBody List<SPORTI> data ,HttpServletRequest request){
        return iOService.insertListSPORTI(data, request);
    }
    @PostMapping("/sportP")
    public ServiceResponse sportPerformance(@RequestBody DoubleAttrNumericDao data ,HttpServletRequest request){
        return iOService.sportPerformance(data, request);
    }


    @PostMapping("/dummy")
    public LocalDate dummy(@RequestBody DoubleAttrDao data){
        return iOService.dummy(data);
    }
    // ------------------------------------------------------------------------------------------------
}
