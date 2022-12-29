package com.carol.rentacar.models;

import com.carol.rentacar.models.enums.RentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class RentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @NotNull
    private RentStatus status;

    @JsonInclude(Include.NON_NULL)
    private String startTime;

    @JsonInclude(Include.NON_NULL)
    private String finishTime;

    protected RentOrder() {
    }

    public RentOrder(Customer customer, Car car) {
        this.customer = customer;
        this.car = car;
        this.status = RentStatus.ACTIVE;
    }

    public RentOrder(Customer customer, Car car, RentStatus status) {
        this.customer = customer;
        this.car = car;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }
}
