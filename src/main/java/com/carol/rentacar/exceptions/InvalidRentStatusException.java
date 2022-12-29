package com.carol.rentacar.exceptions;

public class InvalidRentStatusException extends RuntimeException {

    public InvalidRentStatusException(String msg) {
        super(msg);
    }

    public InvalidRentStatusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
