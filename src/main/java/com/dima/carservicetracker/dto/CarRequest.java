package com.dima.carservicetracker.dto;

import jakarta.validation.constraints.*;

public class CarRequest {
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

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }
}
