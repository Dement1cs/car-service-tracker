package com.dima.carservicetracker.service;

import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.entity.ServiceRecord;
import com.dima.carservicetracker.exception.CarNotFoundException;
import com.dima.carservicetracker.repository.CarRepository;
import com.dima.carservicetracker.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final CarRepository carRepository;

    public ServiceRecordService(ServiceRecordRepository serviceRecordRepository,
                                CarRepository carRepository
    ){
        this.serviceRecordRepository = serviceRecordRepository;
        this.carRepository = carRepository;
    }

    public ServiceRecord createServiceRecord(
        Long carId,
        ServiceRecord serviceRecord
    ){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        serviceRecord.setCar(car);

        return serviceRecordRepository.save(serviceRecord);
    }
}
