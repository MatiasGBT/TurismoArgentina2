package com.mgbt.turismoargentina_backend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ExceptionService implements IExceptionService {

    @Autowired
    MessageSource messageSource;

    @Override
    public ResponseEntity<Map<String, Object>> throwDataAccessException(DataAccessException ex, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", messageSource.getMessage("error.database", null, locale));
        response.put("error", ex.getMessage() + ": " + ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
