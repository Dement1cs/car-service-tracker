package com.dima.carservicetracker.repository;

import com.dima.carservicetracker.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRecordRepository
    extends JpaRepository<ServiceRecord, Long>{
    //<ServiceRecord, Long> ServiceRecord - какая сущность, Long - какой тип id
}
