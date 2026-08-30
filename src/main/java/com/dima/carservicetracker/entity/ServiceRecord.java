package com.dima.carservicetracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity //сущность
@Table(name = "service_records") //название таблицы
public class ServiceRecord {

    @Id // эта переменная это id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // сам енерирует id. стратегия -
    private Long id;
    private LocalDate date;
    private int mileage;
    private String description;
    private BigDecimal cost;

    @ManyToOne // много записей к одной машине
    @JoinColumn(name = "car_id", nullable = false) //FK & если нет id, то и нет записи
    private Car car;

    public ServiceRecord(){
    }

    public ServiceRecord(Car car,
                         LocalDate date,
                         int mileage,
                         String description,
                         BigDecimal cost) {
        this.date = date;
        this.mileage = mileage;
        this.description = description;
        this.cost = cost;
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getMileage() {
        return mileage;
    }

    public void setCar(Car car){
        this.car = car;
    }
    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
