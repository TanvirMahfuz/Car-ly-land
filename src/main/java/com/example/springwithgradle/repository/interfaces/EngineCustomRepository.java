package com.example.springwithgradle.repository.interfaces;

import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.dto.EngineDTO;

import java.util.List;

public interface EngineCustomRepository {
    Engine saveOneEngine(Engine engine);
    Engine updateOneEngine(Engine engine);
    void deleteOneEngine(Long id);
    Engine findEngineById(Long id);
    List<EngineDTO> findBySearchParams(EngineSearchParams params);
}
