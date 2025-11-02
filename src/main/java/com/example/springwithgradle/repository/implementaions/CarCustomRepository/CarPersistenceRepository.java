package com.example.springwithgradle.repository.implementaions.CarCustomRepository;

import com.example.springwithgradle.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CarPersistenceRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Car save(Car car) {
        try{
            entityManager.persist(car);
            return car;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public Car update(Car car) {
        return entityManager.merge(car);
    }
}

