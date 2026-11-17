package com.dima.carservicetracker.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Make must not be blank")
    private String make;
    @NotBlank(message = "Model must not be blank")
    private String model;
    @NotNull (message = "Year is required")
    @Min(value = 1886, message = "Year must be 1886 or later")
    private Integer year;
    @NotBlank
    @Size(max = 50)
    private String engine;
    @NotBlank
    @Pattern(
            regexp = "(?i)^(Petrol|Diesel|Electric|Petrol Hybrid|Diesel Hybrid" +
                    "|Plug-in Petrol Hybrid|Plug-in Diesel Hybrid|LPG|CNG|Other)$",
            message = "Invalid fuel type"
    )
    private String fuelType;
    @NotBlank
    @Pattern(
            regexp = "(?i)^(Manual|Automatic|CVT|DCT|AMT)$",
            message = "Invalid transmission type"
    )
    private String transmission;
    @NotBlank (message = "Registration Number is required")
    @Size(max = 20, message = "Registration number cannot exceed 20 characters")
    private String registrationNumber;
    @NotNull
    @PositiveOrZero(message = "Current mileage cannot be negative")
    private Integer currentMileage;
    private LocalDateTime createdAt;

    public Car(){
    }

    public Car(
            String make,
            String model,
            Integer year,
            String engine,
            String fuelType,
            String transmission,
            String registrationNumber,
            Integer currentMileage
    ){
        this.make = make;
        this.model = model;
        this.year = year;
        this.engine = engine;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.registrationNumber = registrationNumber;
        this.currentMileage = currentMileage;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public String getEngine() {
        return engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }
}
