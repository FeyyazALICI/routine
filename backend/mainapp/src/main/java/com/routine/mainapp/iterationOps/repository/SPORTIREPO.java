package com.routine.mainapp.iterationOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routine.mainapp.iterationOps.entity.SPORTI;
import java.util.List;


public interface SPORTIREPO extends JpaRepository<SPORTI, Long>{
    
    List<SPORTI> findBySportId(Long sport_id);

    @Query("SELECT DISTINCT s.weekR FROM SPORTI s WHERE sportId = :sportId")
    List<Integer> selectDistinctWeeksOFSport(@Param("sportId") Long sportId);

    @Query("SELECT DISTINCT s.yearR FROM SPORTI s WHERE sportId = :sportId")
    List<Integer> selectDistinctYearsOFSport(@Param("sportId") Long sportId);

    @Query("SELECT DISTINCT s.monthR FROM SPORTI s WHERE sportId = :sportId")
    List<Integer> selectDistinctMonthsOFSport(@Param("sportId") Long sportId);

    @Query("SELECT s FROM SPORTI s WHERE sportId = :sportId AND weekR = :weekR AND yearR = :yearR")
    List<SPORTI> selectRowsBYSportAndWeekAndYear(@Param("sportId") Long sportId, @Param("weekR") int weekR, @Param("yearR") int yearR);

    @Query("SELECT s FROM SPORTI s WHERE sportId = :sportId AND monthR = :monthR AND yearR = :yearR")
    List<SPORTI> selectRowsBYSportAndMonthAndYear(@Param("sportId") Long sportId, @Param("monthR") int monthR, @Param("yearR") int yearR);

    @Query("SELECT s FROM SPORTI s WHERE sportId = :sportId AND yearR = :yearR")
    List<SPORTI> selectBYSportAndYear(@Param("sportId") Long sportId, @Param("yearR") int yearR);
}
