package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.io.IOException;
import java.util.*;

@Service
public interface IExceptionService {
    ResponseEntity<Map<String, Object>> throwDataAccessException(DataAccessException ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwEntityNotFoundException(EntityNotFoundException ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwValidationErrorsException(BindingResult result, Locale locale);
    ResponseEntity<Map<String, Object>> throwIOException(IOException ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwNormalException(Exception ex, Locale locale);
}
