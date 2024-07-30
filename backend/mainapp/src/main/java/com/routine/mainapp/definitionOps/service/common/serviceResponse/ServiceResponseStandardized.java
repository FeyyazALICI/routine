package com.routine.mainapp.definitionOps.service.common.serviceResponse;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ServiceResponseStandardized {
    
    public ServiceResponse OkResponse(Object data, HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(data);
        serviceResponse.setHttp_status(HttpStatus.OK.toString());
	    serviceResponse.setStatus_message(1);
        serviceResponse.setRecord_date(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse CreatedResponse(Object data, HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(data);
        serviceResponse.setHttp_status(HttpStatus.CREATED.toString());
	    serviceResponse.setStatus_message(1);
        serviceResponse.setRecord_date(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse IntervalServerErrorResponse(HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(null);
        serviceResponse.setHttp_status(HttpStatus.INTERNAL_SERVER_ERROR.toString());
	    serviceResponse.setStatus_message(0);
        serviceResponse.setRecord_date(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse BadRequestResponse(HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(null);
        serviceResponse.setHttp_status(HttpStatus.BAD_REQUEST.toString());
	    serviceResponse.setStatus_message(0);
        serviceResponse.setRecord_date(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }
}
