package com.routine.mainapp.iterationOps.dao.entity;

import java.math.BigDecimal;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "dailyiteration")
public class DailyIteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;
    
    @Column(name = "category_id")
    @JsonProperty("category_id")
    private Long categoryId;
    @Column(name = "sport_id")
    @JsonProperty("sport_id")
    private Long sportId;
    @Column(name = "project_id")
    @JsonProperty("project_id")
    private Long projectId;   
    @Column(name = "art_id")
    @JsonProperty("art_id")
    private Long artId; 

    @Column(name = "min_or_pages_or_rep")
    @JsonProperty("min_or_pages_or_rep")
    private BigDecimal minOrPagesOrRep; 

    @Column(name = "registration")
    @JsonProperty("registration")
    private Date registration;
    @Column(name = "week_r")
    @JsonProperty("week_r")
    private int weekR;
    @Column(name = "month_r")
    @JsonProperty("month_r")
    private int monthR;
    @Column(name = "year_r")
    @JsonProperty("year_r")
    private int yearR;   

    public DailyIteration(
        Long id,

        Long categoryId,
        Long sportId,
        Long projectId,
        Long artId,

        BigDecimal minOrPagesOrRep,
        Date registration,

        int weekR,
        int monthR,
        int yearR
    ){
        this.id                 = id;

        this.categoryId         = categoryId;
        this.sportId            = sportId;
        this.projectId          = projectId;
        this.artId              = artId;

        this.minOrPagesOrRep    = minOrPagesOrRep;
        this.registration       = registration;

        this.weekR              = weekR;
        this.monthR             = monthR;
        this.yearR              = yearR;
    }
}
