package com.routine.mainapp.iterationOps.entity;

import java.math.BigDecimal;
import java.sql.Date;

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
@Table(name = "sport_i")
public class SPORTI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sport_id")
    private Long sportId;
    @Column(name = "rep_n")
    private BigDecimal repN;
    @Column(name = "set_n")
    private BigDecimal setN;
    @Column(name = "registration")
    private Date registration;
    @Column(name = "week_r")
    private int weekR;
    @Column(name = "month_r")
    private int monthR;
    @Column(name = "year_r")
    private int yearR;   

    public SPORTI(
        Long id,

        Long sportId,
        BigDecimal rep_n,
        BigDecimal set_n,
        Date registration,

        int weekR,
        int monthR,
        int yearR
    ){
        this.id = id;

        this.sportId = sportId;
        this.repN = rep_n;
        this.setN = set_n;
        this.registration = registration;

        this.weekR = weekR;
        this.monthR = monthR;
        this.yearR = yearR;
    }
}
