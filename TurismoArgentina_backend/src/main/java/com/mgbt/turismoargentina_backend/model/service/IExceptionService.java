package com.mgbt.turismoargentina_backend.model.service;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public interface IExceptionService {
    ResponseEntity<Map<String, Object>> throwDataAccessException(DataAccessException ex, Locale locale);
}
