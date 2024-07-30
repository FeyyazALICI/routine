package com.routine.mainapp.definitionOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.mainapp.definitionOps.service.common.logStandart.Log;

public interface LogRepo extends JpaRepository<Log, Long>{
    
}
