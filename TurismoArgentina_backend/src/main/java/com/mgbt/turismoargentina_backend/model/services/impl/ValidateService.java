package com.mgbt.turismoargentina_backend.model.services.impl;

import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.services.IValidateService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidateService implements IValidateService {
    @Override
    public void validateResult(BindingResult result) {
        if (result.hasErrors()) throw new ResultHasErrorsException("Entity is not valid");
    }
}
