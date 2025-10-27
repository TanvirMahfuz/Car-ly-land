package com.example.springwithgradle.repository.implementaions.EngineCustomRepository;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.dto.EngineDTO;
import com.example.springwithgradle.dto.parameters.EngineSearchParams;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.utils.ReflectionUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
class EngineQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public EngineDTO findById(Long id) {
        Engine engine = entityManager.createQuery("SELECT e FROM Engine e WHERE e.id = :id", Engine.class)
                    .setParameter("id", id)
                    .getSingleResult();
        return new  EngineDTO(engine);
    }

    public List<EngineDTO> findBySearchParams(EngineSearchParams params) {
        StringBuilder jpql = new StringBuilder("SELECT e FROM Engine e WHERE 1=1");

        Map<String, Object> paramMap = ReflectionUtils.toHashMap(params);

        for (String key : paramMap.keySet()) {
            jpql.append(" AND e.").append(key).append(" = :").append(key);
        }

        TypedQuery<Engine> query = entityManager.createQuery(jpql.toString(), Engine.class);

        // Set parameters safely
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<Engine> engines = query.getResultList();
        return engines.stream().map(EngineDTO::new).toList();
    }

}
