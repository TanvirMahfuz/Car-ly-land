package com.example.springwithgradle.repository.implementaions.CarCustomRepository;
import com.example.springwithgradle.dto.CarDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CarQueryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public CarDTO findById(Long id) {
        return entityManager.createQuery(
                        "SELECT new com.example.springwithgradle.dto.CarDTO(" +
                                "c.id, c.maker, c.model, c.year, c.owner.firstName, c.engine.type, c.engine.horsepower) " +
                                "FROM Car c WHERE c.id = :id", CarDTO.class)
                .setParameter("id", id)
                .getSingleResult();
    }


    public List<CarDTO> findCarsByUserId(Long userId) {
        List<CarDTO> results = entityManager.createQuery(
                        "SELECT new com.example.springwithgradle.dto.CarDTO(" +
                                "c.id, c.maker, c.model, c.year, c.owner.firstName, c.engine.type, c.engine.horsepower) " +
                                "FROM Car c WHERE c.owner.id = :userId", CarDTO.class)
                .setParameter("userId", userId)
                .getResultList();
        return results;
    }
}
