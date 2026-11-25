package com.dima.carservicetracker.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ServiceRecordRequest {

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
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
