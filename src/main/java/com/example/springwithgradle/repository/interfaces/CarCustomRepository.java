package com.example.springwithgradle.repository.interfaces;

import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.dto.CarDTO;

import java.util.List;

public interface CarCustomRepository {
    Car saveOneCar(Car car);
    Car updateOneCar(Car car);
    void deleteOneCar(Long id);
    CarDTO findById(Long id);
    List<CarDTO> findCarsByUserId(Long userId);
}