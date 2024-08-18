package com.routine.mainapp.common.dao;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
public class TrippleAttrNumericDao {
    
    @JsonProperty("attr0")
    private Long attr0;
    @JsonProperty("attr1")
    private Long attr1;
    @JsonProperty("attr2")
    private int attr2;

    public TrippleAttrNumericDao(
        Long attr0, 
        Long attr1,
        int attr2){
        this.attr0 = attr0;
        this.attr1 = attr1;
        this.attr2 = attr2;
    }
}
