package com.example.springwithgradle.controller.car;

import com.example.springwithgradle.dto.CarDTO;
import com.example.springwithgradle.entity.Car;
import com.example.springwithgradle.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/car")
public class carController {

    @Autowired
    private CarService carService;

    @PostMapping("/create-car")
    public CarDTO createNewCar(@RequestBody Car car) {
        CarDTO newCar = carService.createNewCar(car);
        return newCar;
    }

    @GetMapping("/find/{carID}")
    public CarDTO findCarByID(@PathVariable Long carID) {
        CarDTO newCar = carService.findCarByID(carID);
        return newCar;
    }
}
