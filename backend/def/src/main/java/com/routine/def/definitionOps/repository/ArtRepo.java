package com.routine.def.definitionOps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.routine.def.definitionOps.entity.Art;

public interface ArtRepo extends JpaRepository<Art, Long>{
    
    @Query("SELECT DISTINCT t.name FROM Art t")
    List<String> selectDistinctArtTypes();
}
