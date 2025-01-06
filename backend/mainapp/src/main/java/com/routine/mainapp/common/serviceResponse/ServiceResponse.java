package com.routine.mainapp.common.serviceResponse;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class ServiceResponse {
    
	private int statusMessage;
	private Timestamp recordDate;
	private String httpStatus;
	private String requestType;
	private Object data;        
	private String user;

    public ServiceResponse(
        int statusMessage,
        Timestamp recordDate,
        String httpStatus,
        String requestType,
        Object data,
        String user
    ){
        this.statusMessage = statusMessage;
        this.recordDate = recordDate;
        this.httpStatus = httpStatus;
        this.requestType = requestType;
        this.data = data;
        this.user = user;
    }

}

// response-entity class ı içerisinde benzer bir yapı bulıunmakta
// settlenen nesnenin tipini kurgulayan bir yapı, generic class kullanımı ile yapılamamktadır, 