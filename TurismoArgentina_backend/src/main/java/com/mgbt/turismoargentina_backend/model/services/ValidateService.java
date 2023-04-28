package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class ValidateService implements IValidateService {
    @Override
    public void checkIfResultHasErrors(BindingResult result) {
        if (result.hasErrors()) throw new ResultHasErrorsException("Entity is not valid");
    }
}
