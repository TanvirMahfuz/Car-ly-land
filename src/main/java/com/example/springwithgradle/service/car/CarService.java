package com.example.springwithgradle.service.car;

import com.example.springwithgradle.dto.parameters.CarSearchParams;
import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.entity.Engine;
import com.example.springwithgradle.entity.User;
import com.example.springwithgradle.repository.interfaces.CarCustomRepository;
import com.example.springwithgradle.repository.interfaces.EngineCustomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CarService {
    @Autowired
    CarCustomRepository carCustomRepository;

    @Autowired
    EngineCustomRepository engineCustomRepository;

    @Transactional
    public CarDTO createNewCar(Car car) {
        Engine engine = engineCustomRepository.findEngineById(car.getEngineID());
        Car newCar = carCustomRepository.saveOneCar(car);
        return new CarDTO(newCar,new User(),engine);
    }

    @Transactional
    public CarDTO findCarByID(Long id) {
        Car car = carCustomRepository.findCarById(id);
        if(car != null){
            Engine engine = engineCustomRepository.findEngineById(car.getEngineID());
            return new CarDTO(car,new User(),engine);
        }
        return null;

    }
}
