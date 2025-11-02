package com.example.springwithgradle.service.engine;

import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.dto.EngineDTO;
import com.example.springwithgradle.repository.interfaces.EngineCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineService {

    @Autowired()
    private EngineCustomRepository engineCustomRepository;

    public EngineDTO createNewEngine(Engine engine){
        Engine newEngine = engineCustomRepository.saveOneEngine(engine);
        return new EngineDTO(newEngine);
    }


    public EngineDTO getOneEngine(Long id){
        Engine engine = engineCustomRepository.findEngineById(id);
        return new EngineDTO(engine);
    }

    public List<EngineDTO> getEnginesByParams(EngineSearchParams params){
        return engineCustomRepository.findBySearchParams(params);
    }

}
