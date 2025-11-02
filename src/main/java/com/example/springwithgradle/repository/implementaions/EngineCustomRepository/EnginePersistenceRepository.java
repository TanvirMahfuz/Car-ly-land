package com.example.springwithgradle.repository.implementaions.EngineCustomRepository;

import com.example.springwithgradle.entity.Engine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
class EnginePersistenceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Engine save(Engine engine) {
        try{
            entityManager.persist(engine);
            return engine;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Engine update(Engine engine) {
        return entityManager.merge(engine);
    }
}

