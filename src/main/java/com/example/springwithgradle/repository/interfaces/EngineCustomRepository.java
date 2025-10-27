package com.example.springwithgradle.repository.interfaces;

import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.dto.EngineDTO;

import java.util.List;

public interface EngineCustomRepository {
    EngineDTO saveOneEngine(Engine engine);
    Engine updateOneEngine(Engine engine);
    void deleteOneEngine(Long id);
    EngineDTO findById(Long id);
    List<EngineDTO> findBySearchParams(EngineSearchParams params);
}
