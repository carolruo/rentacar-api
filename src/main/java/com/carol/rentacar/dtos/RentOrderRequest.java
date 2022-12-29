package com.carol.rentacar.dtos;

import com.carol.rentacar.models.enums.RentStatus;
import jakarta.validation.constraints.NotNull;

public class RentOrderRequest {

    @NotNull
    private Long customerId;

    @NotNull
    private Long carId;

    private RentStatus status;

    public RentOrderRequest(Long customerId, Long carId) {
        this.customerId = customerId;
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getCarId() {
        return carId;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }
}
