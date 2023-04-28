package com.mgbt.turismoargentina_backend.exceptions;

public class ResultHasErrorsException extends RuntimeException {
    public ResultHasErrorsException(String errorMessage) {
        super(errorMessage);
    }
}
