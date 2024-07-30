package com.routine.mainapp.definitionOps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.routine.mainapp.definitionOps.entity.Book;
import com.routine.mainapp.definitionOps.repository.BookRepo;
import com.routine.mainapp.definitionOps.repository.LogRepo;
import com.routine.mainapp.definitionOps.service.common.logStandart.Log;
import com.routine.mainapp.definitionOps.service.common.logStandart.interfaces.LogInterface;
import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponse;
import com.routine.mainapp.definitionOps.service.common.serviceResponse.ServiceResponseStandardized;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.util.List;


@Service
public class DefService {
    
    @Autowired
    private LogRepo logRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ServiceResponseStandardized spz;

    // LOG ------------------------------------------------------------------------------------------------------------
    public List<Log> getAllLogs(){
        return logRepo.findAll();
    }
    // LOG ------------------------------------------------------------------------------------------------------------


    // BOOK ------------------------------------------------------------------------------------------------------------
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message = 0)
    public ServiceResponse getAllBooks(   HttpServletRequest request  ){
        try{
            List<Book> dataObtained = bookRepo.findAll();
            return this.spz.OkResponse(dataObtained, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }

    @Transactional
    @LogInterface(request_type = "Default Error Message", user = "default", http_status = "BAD REQUEST", status_message  = 0)
    public ServiceResponse insertBook(Book data, HttpServletRequest request){
        if(data == null){
            return this.spz.BadRequestResponse(request);
        }
        try{
            Book item = new Book();
            item.setName(data.getName());
            item.setAuthor(data.getAuthor());
            item.setPages(data.getPages());
            item.setPublish_year(data.getPublish_year());
            item.setGenre(data.getGenre());
            item.setStart_date(data.getStart_date());
            item.setEnd_date(data.getEnd_date());
            item.setRepetition(data.getRepetition());
            this.bookRepo.saveAndFlush(item);
            return this.spz.OkResponse(item, request);
        }catch(Exception e){
            e.printStackTrace();
            return this.spz.IntervalServerErrorResponse(request);
        }
    }
    // BOOK ------------------------------------------------------------------------------------------------------------

    



}
