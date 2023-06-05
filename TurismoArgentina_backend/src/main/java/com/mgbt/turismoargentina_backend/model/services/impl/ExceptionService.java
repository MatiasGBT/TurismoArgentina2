package com.mgbt.turismoargentina_backend.model.services.impl;

import com.mgbt.turismoargentina_backend.model.services.IExceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.io.IOException;
import java.util.*;

@Service
public class ExceptionService implements IExceptionService {

    @Autowired
    MessageSource messageSource;

    @Override
    public ResponseEntity<Map<String, Object>> throwDataAccessException(DataAccessException ex, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.database.message", null, locale));
        response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> throwEntityNotFoundException(Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.entityNotFound.message", null, locale));
        response.put("error", messageSource.getMessage("error.entityNotFound.error", null, locale));
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Map<String, Object>> throwResultHasErrorsException(BindingResult result, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = result.getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage()).toList();
        String error = errors.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "<br>");
        response.put("message", messageSource.getMessage("error.validationError.message", null, locale));
        response.put("error", error);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Map<String, Object>> throwIOException(IOException ex, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.file.message", null, locale));
        response.put("error", ex.getMessage() + ": " + ex.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> throwNormalException(Exception ex, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.file.message", null, locale));
        response.put("error", ex.getMessage() + ": " + ex.getCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Map<String, Object>> throwPurchaseIncompleteException(Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.purchaseIncomplete.message", null, locale));
        response.put("error", messageSource.getMessage("error.purchaseIncomplete.error", null, locale));
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
