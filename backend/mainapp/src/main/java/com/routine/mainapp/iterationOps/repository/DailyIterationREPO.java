package com.routine.mainapp.iterationOps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.routine.mainapp.iterationOps.entity.DailyIteration;
import java.util.List;


public interface DailyIterationREPO extends JpaRepository<DailyIteration, Long>{

    // selecting distinct weeks-months-years --------------------------------------
    @Query("SELECT DISTINCT d.weekR FROM DailyIteration d WHERE d.artId = :artId")
    List<Integer> selectDistinctWeeksArt(@Param("artId") Long artId);
    @Query("SELECT DISTINCT d.weekR FROM DailyIteration d WHERE d.sportId = :sportId")
    List<Integer> selectDistinctWeeksSport(@Param("sportId") Long sportId);
    @Query("SELECT DISTINCT d.weekR FROM DailyIteration d WHERE d.projectId = :projectId")
    List<Integer> selectDistinctWeeksProject(@Param("projectId") Long projectId);

    @Query("SELECT DISTINCT d.yearR FROM DailyIteration d WHERE d.artId = :artId")
    List<Integer> selectDistinctYearsArt(@Param("artId") Long artId);
    @Query("SELECT DISTINCT d.yearR FROM DailyIteration d WHERE d.sportId = :sportId")
    List<Integer> selectDistinctYearsSport(@Param("sportId") Long sportId);
    @Query("SELECT DISTINCT d.yearR FROM DailyIteration d WHERE d.projectId = :projectId")
    List<Integer> selectDistinctYearsProject(@Param("projectId") Long projectId);

    @Query("SELECT DISTINCT d.monthR FROM DailyIteration d WHERE d.artId = :artId")
    List<Integer> selectDistinctMonthsArt(@Param("artId") Long artId);
    @Query("SELECT DISTINCT d.monthR FROM DailyIteration d WHERE d.sportId = :sportId")
    List<Integer> selectDistinctMonthsSport(@Param("sportId") Long sportId);
    @Query("SELECT DISTINCT d.monthR FROM DailyIteration d WHERE d.projectId = :projectId")
    List<Integer> selectDistinctMonthsProject(@Param("projectId") Long projectId);
    // ----------------------------------------------------------------------------

    // selectby category - subcategory -week - year -------------------------------
    List<DailyIteration> findByCategoryIdAndArtIdAndWeekRAndYearR(Long categoryId, Long artId, int weekR, int yearR);
    List<DailyIteration> findByCategoryIdAndSportIdAndWeekRAndYearR(Long categoryId, Long sportId, int weekR, int yearR);
    List<DailyIteration> findByCategoryIdAndProjectIdAndWeekRAndYearR(Long categoryId, Long projectId, int weekR, int yearR);
    // ----------------------------------------------------------------------------
  
    // selectby category - subcategory -month - year -------------------------------
    List<DailyIteration> findByCategoryIdAndArtIdAndMonthRAndYearR(Long categoryId, Long artId, int monthR, int yearR);
    List<DailyIteration> findByCategoryIdAndSportIdAndMonthRAndYearR(Long categoryId, Long sportId, int monthR, int yearR);
    List<DailyIteration> findByCategoryIdAndProjectIdAndMonthRAndYearR(Long categoryId, Long projectId, int monthR, int yearR);
    // ----------------------------------------------------------------------------

    // selectby category - subcategory  - year ------------------------------------
    List<DailyIteration> findByCategoryIdAndArtIdAndYearR(Long categoryId, Long artId, int yearR);
    List<DailyIteration> findByCategoryIdAndSportIdAndYearR(Long categoryId, Long sportId, int yearR);
    List<DailyIteration> findByCategoryIdAndProjectIdAndYearR(Long categoryId, Long projectId, int yearR);
    // ----------------------------------------------------------------------------

    // selectby category - subcategory   ------------------------------------------
    List<DailyIteration> findByCategoryIdAndArtId(Long categoryId, Long artId);
    List<DailyIteration> findByCategoryIdAndSportId(Long categoryId, Long sportId);
    List<DailyIteration> findByCategoryIdAndProjectId(Long categoryId, Long projectId);
    // ----------------------------------------------------------------------------
    
    List<DailyIteration> findByArtId(Long artId);
    List<DailyIteration> findBySportId(Long sportId);
    List<DailyIteration> findByProjectId(Long projectId);


    
}
