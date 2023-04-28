package com.mgbt.turismoargentina_backend.model.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface IValidateService {
    void checkIfResultHasErrors(BindingResult result);
}
