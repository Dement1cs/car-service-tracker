package com.dima.carservicetracker.service;

import com.dima.carservicetracker.dto.ServiceRecordRequest;
import com.dima.carservicetracker.dto.ServiceRecordResponse;
import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.entity.ServiceRecord;
import com.dima.carservicetracker.exception.CarNotFoundException;
import com.dima.carservicetracker.exception.ServiceRecordNotFoundException;
import com.dima.carservicetracker.repository.CarRepository;
import com.dima.carservicetracker.repository.ServiceRecordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public ServiceRecordResponse createServiceRecord(
        Long carId,
        ServiceRecordRequest request
    ){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        //create new entity
        ServiceRecord record = new ServiceRecord();

        //copy DTO
        record.setDate(request.getDate());
        record.setMileage(request.getMileage());
        record.setDescription(request.getDescription());
        record.setCost(request.getCost());

        //link it to car
        record.setCar(car);

        ServiceRecord sevedRecord = serviceRecordRepository.save(record);
        return toServiceRecordResponse(sevedRecord);
    }

    public List<ServiceRecordResponse> getServiceRecordsByCarId(Long carId) {

        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException(carId));

        List<ServiceRecord> records = serviceRecordRepository.findAllByCarOrderByDateDesc(car);

        List<ServiceRecordResponse> responses = new ArrayList<>();

        for(ServiceRecord record : records){
            responses.add(toServiceRecordResponse(record));
        }

        return responses;
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

    public ServiceRecordResponse getServiceRecordResponseById(Long carId, Long serviceRecordId){

        ServiceRecord record = getServiceRecordById(carId, serviceRecordId);

        return toServiceRecordResponse(record);
    }

    public ServiceRecordResponse updateServiceRecord(
            Long carId,
            Long serviceRecordId,
            ServiceRecordRequest updatedRecord
    ){
        ServiceRecord existingRecord = getServiceRecordById(carId, serviceRecordId);

        existingRecord.setDate(updatedRecord.getDate());
        existingRecord.setMileage(updatedRecord.getMileage());
        existingRecord.setDescription(updatedRecord.getDescription());
        existingRecord.setCost(updatedRecord.getCost());

        ServiceRecord sevedServiceRecord = serviceRecordRepository.save(existingRecord);
        return toServiceRecordResponse(sevedServiceRecord);
    }

    public void deleteServiceRecord(Long carId, Long serviceRecordId){
        ServiceRecord serviceRecord = getServiceRecordById(carId,serviceRecordId);

        serviceRecordRepository.delete(serviceRecord);
    }

    private ServiceRecordResponse toServiceRecordResponse(
            ServiceRecord record
        ){
        ServiceRecordResponse response = new ServiceRecordResponse();

        response.setId(record.getId());
        response.setDate(record.getDate());
        response.setMileage(record.getMileage());
        response.setDescription(record.getDescription());
        response.setCost(record.getCost());

        return response;
    }
}
