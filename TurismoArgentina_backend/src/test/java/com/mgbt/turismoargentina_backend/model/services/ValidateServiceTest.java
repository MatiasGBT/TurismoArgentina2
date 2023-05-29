package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateServiceTest {

    @Mock
    private BindingResult result;

    @InjectMocks
    private ValidateService validateService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void checkIfResultHasErrors_true() {
        when(result.hasErrors()).thenReturn(true);
        assertThrows(ResultHasErrorsException.class, ()-> validateService.checkIfResultHasErrors(result));
    }

    @Test
    void checkIfResultHasErrors_false() {
        when(result.hasErrors()).thenReturn(false);
        assertDoesNotThrow(()-> validateService.checkIfResultHasErrors(result));
    }
}