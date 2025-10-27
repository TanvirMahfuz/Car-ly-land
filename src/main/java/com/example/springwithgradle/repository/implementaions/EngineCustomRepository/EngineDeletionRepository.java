package com.example.springwithgradle.repository.implementaions.EngineCustomRepository;
import com.example.springwithgradle.entity.Engine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
class EngineDeletionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void deleteById(Long id) {
        Engine engine = entityManager.find(Engine.class, id);
        if (engine != null) {
            entityManager.remove(engine);
        }
    }
}
