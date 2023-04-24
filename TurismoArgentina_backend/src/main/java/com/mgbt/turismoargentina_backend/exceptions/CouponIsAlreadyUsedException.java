package com.mgbt.turismoargentina_backend.exceptions;

public class CouponIsAlreadyUsedException extends RuntimeException {
    public CouponIsAlreadyUsedException(String errorMessage) {
        super(errorMessage);
    }
}
