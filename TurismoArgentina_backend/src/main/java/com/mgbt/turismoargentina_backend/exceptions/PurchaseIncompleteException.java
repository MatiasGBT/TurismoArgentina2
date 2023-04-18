package com.mgbt.turismoargentina_backend.exceptions;

public class PurchaseIncompleteException extends RuntimeException {
    public PurchaseIncompleteException(String errorMessage) {
        super(errorMessage);
    }
}
