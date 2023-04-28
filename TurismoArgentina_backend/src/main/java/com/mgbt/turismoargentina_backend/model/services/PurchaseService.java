package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.PurchaseIncompleteException;
import com.mgbt.turismoargentina_backend.model.entities.Purchase;
import com.mgbt.turismoargentina_backend.model.repositories.IPurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {

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
        Purchase purchase = repository.findById(id).orElse(null);
        if (purchase == null) throw new EntityNotFoundException("Purchase not found");
        return purchase;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Purchase> getByUser(Long idUser, Pageable pageable) {
        return repository.findByUserIdUserOrderByIdPurchaseDesc(idUser, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountIsNotRefunded() {
        return repository.findCountIsNotRefunded();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCountIsRefunded() {
        return repository.findCountIsRefunded();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMoneyNotRefunded() {
        return repository.findMoneyNotRefunded();
    }

    @Override
    @Transactional(readOnly = true)
    public Double getMoneyRefunded() {
        return repository.findMoneyRefunded();
    }

    @Override
    public void checkIfLocationsAndActivitiesAreEmpty(Purchase purchase) {
        if (purchase.getLocations().isEmpty() && purchase.getActivities().isEmpty()) {
            throw new PurchaseIncompleteException("");
        }
    }
}
