package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Purchase;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IPurchaseService {
    List<Purchase> getAll();
    Purchase save(Purchase entity);
    void delete(Purchase entity);
    Purchase findById(Long id);
    Page<Purchase> getByUser(Long idUser, Pageable pageable);
    Long getCountIsNotRefunded();
    Long getCountIsRefunded();
    Double getMoneyNotRefunded();
    Double getMoneyRefunded();
}
