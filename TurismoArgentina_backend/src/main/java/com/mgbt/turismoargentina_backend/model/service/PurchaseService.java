package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Purchase;
import com.mgbt.turismoargentina_backend.model.repository.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService<Purchase> {

    @Autowired
    IPurchaseRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Purchase> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Purchase save(Purchase entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Purchase entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Purchase findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
