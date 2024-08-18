package com.routine.def.definitionOps.service.dao;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SportDao {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public SportDao(
        Long id,
        String name
    ){
        this.id = id;
        this.name = name;
    }

}
