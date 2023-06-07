package com.mgbt.turismoargentina_backend.model.services;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.io.IOException;
import java.util.*;

@Service
public interface IExceptionService {
    ResponseEntity<Map<String, Object>> throwDataAccessException(DataAccessException ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwEntityNotFoundException(Locale locale);
    ResponseEntity<Map<String, Object>> throwResultHasErrorsException(BindingResult result, Locale locale);
    ResponseEntity<Map<String, Object>> throwIOException(IOException ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwNormalException(Exception ex, Locale locale);
    ResponseEntity<Map<String, Object>> throwPurchaseIncompleteException(Locale locale);
    ResponseEntity<Map<String, Object>> throwFileNameTooLongException(Locale locale);
}
