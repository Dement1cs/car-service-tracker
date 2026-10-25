package com.dima.carservicetracker.service;

import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.entity.ServiceRecord;
import com.dima.carservicetracker.exception.CarNotFoundException;
import com.dima.carservicetracker.exception.ServiceRecordNotFoundException;
import com.dima.carservicetracker.repository.CarRepository;
import com.dima.carservicetracker.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<ServiceRecord> getServiceRecordsByCarId(Long carId) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        return serviceRecordRepository.findAllByCarOrderByDateDesc(car);
    }

    public ServiceRecord getServiceRecordById(Long carId, Long serviceRecordId){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        ServiceRecord serviceRecord = serviceRecordRepository.findById(serviceRecordId)
                .orElseThrow(() -> new ServiceRecordNotFoundException(serviceRecordId));

        if(!serviceRecord.getCar().getId().equals(car.getId())){
            throw new ServiceRecordNotFoundException(serviceRecordId);
        }
        return serviceRecord;
    }

    public ServiceRecord updateServiceRecord(
            Long carId,
            Long serviceRecordId,
            ServiceRecord updatedRecord
    ){
        ServiceRecord existingRecord = getServiceRecordById(carId, serviceRecordId);

        existingRecord.setDate(updatedRecord.getDate());
        existingRecord.setMileage(updatedRecord.getMileage());
        existingRecord.setDescription(updatedRecord.getDescription());
        existingRecord.setCost(updatedRecord.getCost());
        return serviceRecordRepository.save(existingRecord);
    }

    public void deleteServiceRecord(Long carId, Long serviceRecordId){
        ServiceRecord serviceRecord = getServiceRecordById(carId,serviceRecordId);

        serviceRecordRepository.delete(serviceRecord);
    }
}
