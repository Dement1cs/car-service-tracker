package com.dima.carservicetracker.controller;

import com.dima.carservicetracker.entity.ServiceRecord;
import com.dima.carservicetracker.service.ServiceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars/{carId}/service-records")
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    public ServiceRecordController(ServiceRecordService serviceRecordService) {
        this.serviceRecordService = serviceRecordService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServiceRecord createServiceRecord(
            @PathVariable Long carId,
            @RequestBody ServiceRecord serviceRecord
    ) {
        return serviceRecordService.createServiceRecord(carId, serviceRecord);
    }

}
