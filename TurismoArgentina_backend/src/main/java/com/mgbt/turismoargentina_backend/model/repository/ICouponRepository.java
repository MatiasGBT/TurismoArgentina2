package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICouponRepository extends JpaRepository<Coupon, Long> {
    Coupon findByName(String name);
}
