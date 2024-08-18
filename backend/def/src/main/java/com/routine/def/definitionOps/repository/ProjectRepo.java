package com.routine.def.definitionOps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routine.def.definitionOps.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{
    
    @Query("SELECT DISTINCT t.name FROM Project t")
    List<String> selectDistinctProjects();
}
