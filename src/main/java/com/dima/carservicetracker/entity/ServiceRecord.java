package com.dima.carservicetracker.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Entity //сущность
@Table(name = "service_records") //название таблицы
public class ServiceRecord {

    @Id // эта переменная это id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // сам енерирует id. стратегия -
    private Long id;
    @NotNull(message = "Date is required")
    @PastOrPresent(message = "Date can not be in the futre")
    private LocalDate date;
    @NotNull(message = "Mileage is required")
    @PositiveOrZero(message = "Mileage cannot be negative")
    private Integer mileage;
    @NotBlank(message = "Description mast be not blank")
    @Size(max = 255)
    private String description;
    @NotNull(message = "Cost is required")
    @PositiveOrZero(message = "Cost can not be negative")
    @Digits(integer = 8,fraction = 2)
    private BigDecimal cost;

    @ManyToOne // много записей к одной машине
    @JoinColumn(name = "car_id", nullable = false) //FK & если нет id, то и нет записи
    private Car car;

    public ServiceRecord(){
    }

    public ServiceRecord(Car car,
                         LocalDate date,
                         Integer mileage,
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

    public Integer getMileage() {
        return mileage;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCar(Car car){
        this.car = car;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
