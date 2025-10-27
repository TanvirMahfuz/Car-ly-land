package com.example.springwithgradle.repository.implementaions.CarCustomRepository;
import com.example.springwithgradle.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public class CarDeletionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void deleteById(Long id) {
        Car car = entityManager.find(Car.class, id);
        if (car != null) {
            entityManager.remove(car);
        }
    }
}
