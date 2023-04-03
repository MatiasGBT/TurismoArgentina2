package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.purchases p " +
            "WHERE p.refunded = false AND date_trunc('month', p.date) = date_trunc('month', now())", nativeQuery = true)
    Long findCountIsNotRefunded();

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.purchases p " +
            "WHERE p.refunded = true AND date_trunc('month', p.date) = date_trunc('month', now())", nativeQuery = true)
    Long findCountIsRefunded();

    @Query(value = "SELECT p.price FROM turismo_argentina.purchases p " +
            "WHERE p.refunded = false AND date_trunc('month', p.date) = date_trunc('month', now())",
            nativeQuery = true)
    Double findMoneyNotRefunded();

    @Query(value = "SELECT p.price FROM turismo_argentina.purchases p " +
            "WHERE p.refunded = true AND date_trunc('month', p.date) = date_trunc('month', now())",
            nativeQuery = true)
    Double findMoneyRefunded();
}
