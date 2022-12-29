package com.carol.rentacar.models;

import com.carol.rentacar.models.enums.CarStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @NotBlank
    private String model;

    @NotBlank
    private String color;

    @NotNull
    private Integer year;

    @NotBlank
    private String plate;

    @NotNull
    private CarStatus status;

    protected Car() {
    }

    public Car(String model, String color, Integer year, String plate) {
        this.model = model;
        this.color = color;
        this.year = year;
        this.plate = plate;
        this.status = CarStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }
}
