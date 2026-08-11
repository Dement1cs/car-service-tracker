package com.dima.carservicetracker.controller;

import com.dima.carservicetracker.service.CarService;
import com.dima.carservicetracker.entity.Car;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }
}
