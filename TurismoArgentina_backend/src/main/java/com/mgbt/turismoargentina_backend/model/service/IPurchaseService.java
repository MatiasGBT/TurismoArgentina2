package com.mgbt.turismoargentina_backend.model.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IPurchaseService<Purchase> {
    List<Purchase> getAll();
    Purchase save(Purchase entity);
    void delete(Purchase entity);
    Purchase findById(Long id);
}
