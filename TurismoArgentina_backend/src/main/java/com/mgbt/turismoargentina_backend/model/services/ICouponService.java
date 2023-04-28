package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public interface ICouponService {
    Page<Coupon> getAll(Pageable pageable);
    Page<Coupon> getAllByName(String name, Pageable pageable);
    Coupon save(Coupon entity);
    void delete(Coupon entity);
    Coupon findById(Long id);
    Coupon findByName(String name);
}
