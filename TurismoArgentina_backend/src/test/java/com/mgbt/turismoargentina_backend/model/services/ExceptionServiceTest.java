package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.model.services.impl.ExceptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExceptionServiceTest {

    @Mock
    private MessageSource messageSource;

    @Mock
    private DataAccessException dataAccessException;

    @Mock
    private IOException ioException;

    @Mock
    private Exception exception;

    @Mock
    private BindingResult result;

    @InjectMocks
    private ExceptionService exceptionService;

    private Locale locale;

    @BeforeEach
    void setUp() {
        locale = new Locale.Builder().setLanguage("en").build();
    }

    @Test
    void throwDataAccessException() {
        when(messageSource.getMessage("error.database.message", null, locale)).thenReturn("Database error");
        when(dataAccessException.getMessage()).thenReturn("Error");
        when(dataAccessException.getMostSpecificCause()).thenReturn(new Throwable("Could not connect"));
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwDataAccessException(dataAccessException, locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "Database error");
        assertEquals(responseEntity.getBody().get("error"), "Error: Could not connect");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void throwEntityNotFoundException() {
        when(messageSource.getMessage("error.entityNotFound.message", null, locale)).thenReturn("Entity not found");
        when(messageSource.getMessage("error.entityNotFound.error", null, locale)).thenReturn("The entity does not exist");
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwEntityNotFoundException(locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "Entity not found");
        assertEquals(responseEntity.getBody().get("error"), "The entity does not exist");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void throwResultHasErrorsException() {
        when(messageSource.getMessage("error.validationError.message", null, locale)).thenReturn("The entity contains errors");
        when(result.getFieldErrors()).thenReturn(List.of(new FieldError("", "Name error", "Name is mandatory")));
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwResultHasErrorsException(result, locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "The entity contains errors");
        assertEquals(responseEntity.getBody().get("error"), "Name error: Name is mandatory");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void throwIOException() {
        when(messageSource.getMessage("error.file.message", null, locale)).thenReturn("File error");
        when(ioException.getMessage()).thenReturn("Error");
        when(ioException.getCause()).thenReturn(new Throwable("File must be an image"));
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwIOException(ioException, locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "File error");
        assertEquals(responseEntity.getBody().get("error"), "Error: File must be an image");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void throwNormalException() {
        when(messageSource.getMessage("error.file.message", null, locale)).thenReturn("File error");
        when(exception.getMessage()).thenReturn("Error");
        when(exception.getCause()).thenReturn(new Throwable("Some exception cause"));
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwNormalException(exception, locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "File error");
        assertEquals(responseEntity.getBody().get("error"), "Error: Some exception cause");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void throwPurchaseIncompleteException() {
        when(messageSource.getMessage("error.purchaseIncomplete.message", null, locale)).thenReturn("The purchase is incomplete");
        when(messageSource.getMessage("error.purchaseIncomplete.error", null, locale)).thenReturn("Must have at least one location or activity");
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwPurchaseIncompleteException(locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "The purchase is incomplete");
        assertEquals(responseEntity.getBody().get("error"), "Must have at least one location or activity");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void throwFileNameTooLongException() {
        when(messageSource.getMessage("error.fileNameTooLong.message", null, locale)).thenReturn("File name too long");
        when(messageSource.getMessage("error.fileNameTooLong.error", null, locale)).thenReturn("The name of the image must be a maximum of 40 characters");
        ResponseEntity<Map<String, Object>> responseEntity = exceptionService.throwFileNameTooLongException(locale);
        assertNotNull(responseEntity);
        assertEquals(responseEntity.getBody().get("message"), "File name too long");
        assertEquals(responseEntity.getBody().get("error"), "The name of the image must be a maximum of 40 characters");
        assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
    }
}