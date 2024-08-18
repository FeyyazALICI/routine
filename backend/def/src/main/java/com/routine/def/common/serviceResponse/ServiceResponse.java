package com.routine.def.common.serviceResponse;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class ServiceResponse {
    
	private int status_message;
	private Timestamp record_date;
	private String http_status;
	private String request_type;
	private Object data;
	private String user;

    public ServiceResponse(
        int status_message,
        Timestamp record_date,
        String http_status,
        String request_type,
        Object data,
        String user
    ){
        this.status_message = status_message;
        this.record_date = record_date;
        this.http_status = http_status;
        this.request_type = request_type;
        this.data = data;
        this.user = user;
    }
}
