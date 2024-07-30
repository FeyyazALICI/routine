package com.routine.mainapp.definitionOps.service.dao;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class TripleAttrDao {
    
    @JsonProperty("attr0")
    private String attr0;
    @JsonProperty("attr1")
    private String attr1;
    @JsonProperty("attr2")
    private String attr2;

    public TripleAttrDao(
        String attr0, 
        String attr1,
        String attr2){
        this.attr0 = attr0;
        this.attr1 = attr1;
        this.attr2 = attr2;
    }
}
