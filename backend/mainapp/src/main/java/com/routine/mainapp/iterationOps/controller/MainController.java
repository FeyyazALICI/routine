package com.routine.mainapp.iterationOps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.mainapp.common.dao.TrippleAttrNumericDao;
import com.routine.mainapp.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.iterationOps.entity.DailyIteration;
import com.routine.mainapp.iterationOps.service.MainService;
import com.routine.mainapp.iterationOps.service.dao.SaveSingleEntityAngularDao;

import jakarta.servlet.http.HttpServletRequest;

@RequestMapping("/main")
@RestController
public class MainController {
    @Autowired
    private MainService mainService;

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
    public ServiceResponse saveSingle(@RequestBody SaveSingleEntityAngularDao data, HttpServletRequest request){
        return mainService.saveSingle(data, request);
    }
    @PostMapping("/p")
    public ServiceResponse performance(@RequestBody TrippleAttrNumericDao data ,HttpServletRequest request){
        return mainService.performance(data, request);
    }
    // ------------------------------------------------------------------------------------------------
}
