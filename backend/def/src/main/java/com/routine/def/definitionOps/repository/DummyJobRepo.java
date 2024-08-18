package com.routine.def.definitionOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.routine.def.definitionOps.entity.DummyJob;

public interface DummyJobRepo extends JpaRepository<DummyJob, Long>{
    
}
