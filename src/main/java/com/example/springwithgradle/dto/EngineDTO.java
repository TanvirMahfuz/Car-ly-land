package com.example.springwithgradle.dto;

import com.example.springwithgradle.entity.Engine;
import lombok.Data;

@Data
public class EngineDTO {
    private Engine engine;

    public EngineDTO(Engine engine) {
        if(engine != null){
            this.engine=engine;
        }
    }

}
