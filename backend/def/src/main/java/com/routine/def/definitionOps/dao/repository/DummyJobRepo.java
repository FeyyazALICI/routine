package com.routine.def.definitionOps.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.def.definitionOps.dao.entity.DummyJob;

public interface DummyJobRepo extends JpaRepository<DummyJob, Long>{
    
}
