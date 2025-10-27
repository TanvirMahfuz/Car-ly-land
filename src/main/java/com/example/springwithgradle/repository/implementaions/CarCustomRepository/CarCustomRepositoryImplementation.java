package com.example.springwithgradle.repository.implementaions.CarCustomRepository;

import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.repository.interfaces.CarCustomRepository;
import lombok.Data;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Data
public class CarCustomRepositoryImplementation implements CarCustomRepository {

    private final CarDeletionRepository carDeletionRepository;
    private final CarPersistenceRepository carPersistenceRepository;
    private final CarQueryRepository carQueryRepository;

    public CarCustomRepositoryImplementation(){
        this.carDeletionRepository = new CarDeletionRepository();
        this.carPersistenceRepository = new CarPersistenceRepository();
        this.carQueryRepository = new CarQueryRepository();
    }


    @Override
    public  Car updateOneCar(Car car){
        return carPersistenceRepository.update(car);
    }
    @Override
    public  Car saveOneCar(Car car){
        return carPersistenceRepository.save(car);
    }
    @Override
    public void deleteOneCar(Long id){
        carDeletionRepository.deleteById(id);
    }

    @Override
    public CarDTO findById(Long id){
        return carQueryRepository.findById(id);
    }

    @Override
    public List<CarDTO> findCarsByUserId(Long userId){
        return carQueryRepository.findCarsByUserId(userId);
    }
}
