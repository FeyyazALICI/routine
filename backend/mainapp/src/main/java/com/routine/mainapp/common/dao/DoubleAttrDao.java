package com.routine.mainapp.common.dao;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class DoubleAttrDao {
    
    @JsonProperty("attr0")
    private String attr0;
    @JsonProperty("attr1")
    private String attr1;

    public DoubleAttrDao(
        String attr0, 
        String attr1){
        this.attr0 = attr0;
        this.attr1 = attr1;
    }
}
