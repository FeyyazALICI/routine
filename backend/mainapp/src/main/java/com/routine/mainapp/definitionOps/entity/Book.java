package com.routine.mainapp.definitionOps.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "pages")
    private BigDecimal pages;
    @Column(name = "publish_year")
    private int publish_year;
    @Column(name = "genre")
    private String genre;
    @Column(name = "start_date")
    private Timestamp start_date;
    @Column(name = "end_date")
    private Timestamp end_date;
    @Column(name = "repetition")
    private int repetition;

    public Book(
        Long id,
        String name,
        String author,
        BigDecimal pages,
        int publish_year,
        String genre,
        Timestamp start_date,
        Timestamp end_date,
        int repetition
    ){
        this.id = id;
        this.name = name;
        this.author = author;
        this.pages = pages;
        this.publish_year = publish_year;
        this.genre = genre;
        this.start_date = start_date;
        this.end_date = end_date;
        this.repetition = repetition;
    }

}
