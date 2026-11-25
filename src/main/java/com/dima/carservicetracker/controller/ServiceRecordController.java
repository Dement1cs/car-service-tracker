package com.dima.carservicetracker.controller;

import com.dima.carservicetracker.dto.ServiceRecordRequest;
import com.dima.carservicetracker.dto.ServiceRecordResponse;
import com.dima.carservicetracker.entity.ServiceRecord;
import com.dima.carservicetracker.service.ServiceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/cars/{carId}/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRecordResponse createServiceRecord(
            @PathVariable Long carId,
            @Valid @RequestBody ServiceRecordRequest request
    ) {
        return serviceRecordService.createServiceRecord(carId, request);
    }

    @GetMapping
    public List<ServiceRecordResponse> getServiceRecords(@PathVariable Long carId) {
        return serviceRecordService.getServiceRecordsByCarId(carId);
    }

    @GetMapping("/{serviceRecordId}")
    public ServiceRecordResponse getServiceRecordById(
            @PathVariable Long carId,
            @PathVariable Long serviceRecordId
    ){
        return serviceRecordService.getServiceRecordResponseById(carId, serviceRecordId);
    }

    @PutMapping("/{serviceRecordId}")
    public ServiceRecordResponse updateServiceRecord(
            @PathVariable Long carId,
            @PathVariable Long serviceRecordId,
            @Valid @RequestBody ServiceRecordRequest request
    ){
        return serviceRecordService.updateServiceRecord(
                carId,
                serviceRecordId,
                request);
    }

    @DeleteMapping("/{serviceRecordId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteServiceRecord(
        @PathVariable Long carId,
        @PathVariable Long serviceRecordId
    ){
        serviceRecordService.deleteServiceRecord(carId, serviceRecordId);
    }
}
