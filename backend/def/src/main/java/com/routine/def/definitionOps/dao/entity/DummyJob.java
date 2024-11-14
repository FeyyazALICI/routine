package com.routine.def.definitionOps.dao.entity;

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
@Table(name = "dummy_job")
@NoArgsConstructor
@Data
public class DummyJob {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "record_date")
    private Date recordDate;

    public DummyJob(Long id, Date recordDate){
        this.id = id;
        this.recordDate = recordDate;
    }
}
