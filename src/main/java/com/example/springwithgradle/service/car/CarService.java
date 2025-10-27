package com.example.springwithgradle.service.car;

import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.repository.interfaces.CarCustomRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {
    CarDTO carDTO;
    CarCustomRepository carCustomRepository;

    public Car CreateNewCar(CarDTO carDTO) {
        return  null;
    }
}
