package com.example.springwithgradle.repository.implementaions.CarCustomRepository;

import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.repository.interfaces.CarCustomRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Data
public class CarCustomRepositoryImplementation implements CarCustomRepository {

    private final CarDeletionRepository carDeletionRepository;
    private final CarPersistenceRepository carPersistenceRepository;
    private final CarQueryRepository carQueryRepository;

    @Autowired
    public CarCustomRepositoryImplementation(
            CarDeletionRepository carDeletionRepository,
            CarPersistenceRepository carPersistenceRepository,
            CarQueryRepository carQueryRepository
    ) {
        this.carDeletionRepository = carDeletionRepository;
        this.carPersistenceRepository = carPersistenceRepository;
        this.carQueryRepository = carQueryRepository;
    }

    @Override
    public  Car saveOneCar(Car car){
        Car savedCar = carPersistenceRepository.save(car);
        if(car==null){
            return null;
        }
        return savedCar;
    }

    @Override
    public  Car updateOneCar(Car car){
        return carPersistenceRepository.update(car);
    }

    @Override
    public void deleteOneCar(Long id){
        carDeletionRepository.deleteById(id);
    }

    @Override
    public Car findCarById(Long id){
        return carQueryRepository.findById(id);
    }

    @Override
    public List<CarDTO> findCarsByUserId(Long userId){
        return carQueryRepository.findCarsByUserId(userId);
    }
}
