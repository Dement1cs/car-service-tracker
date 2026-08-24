package com.dima.carservicetracker.service;

import com.dima.carservicetracker.repository.CarRepository;
import org.springframework.stereotype.Service;
import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.exception.CarNotFoundException;

import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Car getCarById(Long id){
        return carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
    }

    public void deleteCar(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }
}

