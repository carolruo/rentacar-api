package com.carol.rentacar.exceptions;

public class DuplicateObjectException extends RuntimeException {

    public DuplicateObjectException(String msg) {
        super(msg);
    }

    public DuplicateObjectException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
