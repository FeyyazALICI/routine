package com.routine.mainapp.common.serviceResponse;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class ServiceResponseStandardized {
    
    public ServiceResponse OkResponse(Object data, HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(data);
        serviceResponse.setHttpStatus(HttpStatus.OK.toString());
	    serviceResponse.setStatusMessage(1);
        serviceResponse.setRecordDate(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse CreatedResponse(Object data, HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(data);
        serviceResponse.setHttpStatus(HttpStatus.CREATED.toString());
	    serviceResponse.setStatusMessage(1);
        serviceResponse.setRecordDate(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse IntervalServerErrorResponse(HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(null);
        serviceResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
	    serviceResponse.setStatusMessage(0);
        serviceResponse.setRecordDate(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }

    public ServiceResponse BadRequestResponse(HttpServletRequest request ){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setData(null);
        serviceResponse.setHttpStatus(HttpStatus.BAD_REQUEST.toString());
	    serviceResponse.setStatusMessage(0);
        serviceResponse.setRecordDate(new Timestamp(System.currentTimeMillis()));
		serviceResponse.setUser(request.getRemoteAddr());
        return serviceResponse;
    }
}
