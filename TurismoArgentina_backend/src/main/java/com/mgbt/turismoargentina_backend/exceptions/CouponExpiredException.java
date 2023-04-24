package com.mgbt.turismoargentina_backend.exceptions;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(String errorMessage) {
        super(errorMessage);
    }
}
