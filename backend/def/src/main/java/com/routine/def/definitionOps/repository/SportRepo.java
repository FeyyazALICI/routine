package com.routine.def.definitionOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routine.def.definitionOps.entity.Sport;
import java.util.List;


public interface SportRepo extends JpaRepository<Sport, Long>{
    
    @Query("SELECT DISTINCT t.name FROM Sport t")
    List<String> selectDistinctSportTypes();
}
