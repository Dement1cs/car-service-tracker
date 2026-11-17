package com.dima.carservicetracker.service;

import com.dima.carservicetracker.dto.CarRequest;
import com.dima.carservicetracker.dto.CarResponse;
import com.dima.carservicetracker.repository.CarRepository;
import org.springframework.stereotype.Service;
import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.exception.CarNotFoundException;
import com.dima.carservicetracker.dto.CarRequest;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public CarResponse createCar(CarRequest request){
        Car car = new Car();

        car.setMake(request.getMake());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setEngine(request.getEngine());
        car.setFuelType(request.getFuelType());
        car.setTransmission(request.getTransmission());
        car.setRegistrationNumber(request.getRegistrationNumber());
        car.setCurrentMileage(request.getCurrentMileage());

        Car sevedCar = carRepository.save(car);

        return toCarResponse(sevedCar);
    }

    public CarResponse updateCar(Long id, CarRequest request){
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        existingCar.setMake(request.getMake());
        existingCar.setModel(request.getModel());
        existingCar.setYear(request.getYear());
        existingCar.setEngine(request.getEngine());
        existingCar.setFuelType(request.getFuelType());
        existingCar.setTransmission(request.getTransmission());
        existingCar.setRegistrationNumber(request.getRegistrationNumber());
        existingCar.setCurrentMileage(request.getCurrentMileage());

        Car savedCar = carRepository.save(existingCar);
        return toCarResponse(savedCar);
    }

    public List<CarResponse> getAllCars(){
        List<Car> cars = carRepository.findAll();
        List<CarResponse> responses = new ArrayList<>();

        for ( Car car : cars){
            responses.add(toCarResponse(car));
        }
        return responses;
    }

    public CarResponse getCarById(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        return toCarResponse(car);
    }

    public void deleteCar(Long id){
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));
        carRepository.delete(car);
    }

    private CarResponse toCarResponse(Car car) {
        CarResponse response = new CarResponse();

        response.setId(car.getId());
        response.setMake(car.getMake());
        response.setModel(car.getModel());
        response.setYear(car.getYear());
        response.setEngine(car.getEngine());
        response.setFuelType(car.getFuelType());
        response.setTransmission(car.getTransmission());
        response.setRegistrationNumber(car.getRegistrationNumber());
        response.setCurrentMileage(car.getCurrentMileage());
        response.setCreatedAt(car.getCreatedAt());

        return response;
    }
}

