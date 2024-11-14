package com.routine.def.definitionOps.business.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SportDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public SportDTO(
        Long id,
        String name
    ){
        this.id = id;
        this.name = name;
    }

}
