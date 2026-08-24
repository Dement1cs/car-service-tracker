package com.dima.carservicetracker.controller;

import com.dima.carservicetracker.service.CarService;
import com.dima.carservicetracker.entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@RequestBody Car car){
        return carService.createCar(car);
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        return carService.getCarById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id){
        carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public Car updateCar(
        @PathVariable Long id,
        @RequestBody Car updatedCar
    ){
        return carService.updateCar(id, updatedCar);
    }
}
