package com.routine.mainapp.iterationOps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.mainapp.common.dto.TrippleAttrNumericDTO;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.iterationOps.business.dto.SaveSingleEntityAngularDTO;
import com.routine.mainapp.iterationOps.business.service.MainService;
import com.routine.mainapp.iterationOps.dao.entity.DailyIteration;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/main")
@RestController
public class MainController {
    
    private final MainService mainService;

    @Autowired
    public MainController(
        MainService mainService
    ){
        this.mainService = mainService;
    }

    // ------------------------------------------------------------------------------------------------
    @GetMapping
    public ServiceResponse getAllData(HttpServletRequest request){
        return mainService.getAllData(request);
    }
    @PostMapping                // save list of entities
    public ServiceResponse saveList(@RequestBody List<DailyIteration> data ,HttpServletRequest request){
        return mainService.saveList(data, request);
    }
    @PostMapping("/saveSingle")
    public ServiceResponse saveSingle(@RequestBody SaveSingleEntityAngularDTO data, HttpServletRequest request){
        return mainService.saveSingle(data, request);
    }
    @PostMapping("/p")
    public ServiceResponse performance(@RequestBody TrippleAttrNumericDTO data ,HttpServletRequest request){
        return mainService.performance(data, request);
    }
    // ------------------------------------------------------------------------------------------------
}
