package com.dima.carservicetracker.service;

import com.dima.carservicetracker.repository.CarRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;
import com.dima.carservicetracker.entity.Car;

import java.sql.SQLOutput;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Car createCar(Car car){
        return carRepository.save(car);
    }
}

