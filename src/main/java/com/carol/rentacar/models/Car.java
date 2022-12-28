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
}
