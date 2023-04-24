package com.mgbt.turismoargentina_backend.exceptions;

public class CouponIsNotValidYetException extends RuntimeException {
    public CouponIsNotValidYetException(String errorMessage) {
        super(errorMessage);
    }
}
