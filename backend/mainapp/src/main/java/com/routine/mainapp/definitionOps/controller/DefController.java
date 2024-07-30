package com.routine.mainapp.definitionOps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routine.mainapp.definitionOps.entity.Book;
import com.routine.mainapp.definitionOps.service.DefService;
import com.routine.mainapp.definitionOps.service.common.logStandart.Log;
import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponse;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/d")   // /d->def
public class DefController {
    
    @Autowired
    private DefService defService;

    @GetMapping("/log")
    public List<Log> getAllLogs(){
        return this.defService.getAllLogs();
    }

    @GetMapping("/book")
    public ServiceResponse getAllBooks( HttpServletRequest request ){
        return this.defService.getAllBooks(request);
    }

    @PostMapping("/book")
    public ServiceResponse insertBook(@RequestBody Book data, HttpServletRequest request){
        return this.defService.insertBook(data, request);
    }
}
