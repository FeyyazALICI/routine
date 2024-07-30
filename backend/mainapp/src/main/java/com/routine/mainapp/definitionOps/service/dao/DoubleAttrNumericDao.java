package com.routine.mainapp.definitionOps.service.dao;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class DoubleAttrNumericDao {
    
    @JsonProperty("attr0")
    private Long attr0;
    @JsonProperty("attr1")
    private int attr1;

    public DoubleAttrNumericDao(
        Long attr0, 
        int attr1){
        this.attr0 = attr0;
        this.attr1 = attr1;
    }
}
