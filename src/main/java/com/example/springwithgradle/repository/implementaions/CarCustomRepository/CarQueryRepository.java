package com.example.springwithgradle.repository.implementaions.CarCustomRepository;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.entity.Car;
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

    public Car findById(Long id) {
        try{
            Car foundCar = entityManager.createQuery(
                            "SELECT c FROM Car c WHERE c.id = :id", Car.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return foundCar;
        }catch (Exception e){
            return null;
        }
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
