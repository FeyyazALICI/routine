package com.routine.mainapp.iterationOps.service.dao;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class SaveSingleEntityAngularDao {
    @JsonProperty("category")
    private int category;
    @JsonProperty("subcategory")
    private int subcategory;
    @JsonProperty("kpi")
    private BigDecimal kpi;
    @JsonProperty("time_line")
    private int timeLine;

    public SaveSingleEntityAngularDao(
        int category,
        int subcategory,
        BigDecimal kpi,
        int timeLine
    ){
        this.category = category;
        this.subcategory = subcategory;
        this.kpi = kpi;
        this.timeLine = timeLine;
    }
}
