package com.routine.mainapp.definitionOps.service.common.logStandart;

import java.sql.Timestamp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.routine.mainapp.definitionOps.repository.LogRepo;
import com.routine.mainapp.definitionOps.service.common.logStandart.interfaces.LogInterface;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogRepo logRepo;

    /*
     * A JoinPoint represents a point during the execution of the program, such as the execution of a method or the handling of an exception. 
     * It provides reflective access to both the state available at the join point and static information about the join point.
     */
    @AfterReturning(pointcut = "@annotation(logInterfaceAnnotation)", returning = "result")
    public void logAspectAfterReturning(JoinPoint joinPoint, LogInterface logInterfaceAnnotation, Object result) {
        logAspect(joinPoint, logInterfaceAnnotation, "OK", 1);
    }
    @AfterThrowing(pointcut = "@annotation(logInterfaceAnnotation)", throwing = "exception")
    public void logAspectAfterThrowing(JoinPoint joinPoint, LogInterface logInterfaceAnnotation, Throwable exception) {
        logAspect(joinPoint, logInterfaceAnnotation, "Internal Service Error", 0);
    }

    private void logAspect(JoinPoint joinPoint, LogInterface logInterfaceAnnotation, String httpStatus, int status_message) {
        // Extract method arguments
        Object[] args = joinPoint.getArgs();
        String requestType = logInterfaceAnnotation.request_type();
        String user = logInterfaceAnnotation.user();

        // Iterate through the method arguments to find the HttpServletRequest
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) arg;
                requestType = request.getMethod();
                user = request.getRemoteAddr();  // Extract the IP address
                break;
            }
        }

        // Log the information
        Log log = new Log();
        log.setStatus_message(1);
        log.setRecord_date(new Timestamp(System.currentTimeMillis()));
        log.setHttp_status(httpStatus);
        log.setRequest_type(requestType);
        log.setData(null);
        log.setUser(user);
        this.logRepo.saveAndFlush(log);
    }



}

