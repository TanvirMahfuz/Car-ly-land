package com.example.springwithgradle.repository.implementaions.EngineCustomRepository;

import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.dto.EngineDTO;
import com.example.springwithgradle.repository.interfaces.EngineCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class EngineCustomRepositoryImplementation implements EngineCustomRepository {

    private final EnginePersistenceRepository enginePersistenceRepository;
    private final EngineDeletionRepository engineDeletionRepository;
    private final EngineQueryRepository engineQueryRepository;

    @Override
    public EngineDTO saveOneEngine(Engine engine){
        Engine savedEngine = enginePersistenceRepository.save(engine);
        if(savedEngine == null) return null;
        return new EngineDTO(savedEngine);
    }


    @Override
    public Engine updateOneEngine(Engine engine){
        return enginePersistenceRepository.update(engine);
    }

    @Override
    public void deleteOneEngine(Long id){
        engineDeletionRepository.deleteById(id);
    }

    @Override
    public EngineDTO findById(Long id){
        return engineQueryRepository.findById(id);
    }
    @Override
    public List<EngineDTO> findBySearchParams(EngineSearchParams params){
        return engineQueryRepository.findBySearchParams(params);
    }
}
