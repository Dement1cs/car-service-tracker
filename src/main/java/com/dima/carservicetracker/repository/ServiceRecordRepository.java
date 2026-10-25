package com.dima.carservicetracker.repository;

import com.dima.carservicetracker.entity.Car;
import com.dima.carservicetracker.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository
    extends JpaRepository<ServiceRecord, Long>{
    //<ServiceRecord, Long> ServiceRecord - какая сущность, Long - какой тип id

    List<ServiceRecord> findAllByCarOrderByDateDesc(Car car);
}
