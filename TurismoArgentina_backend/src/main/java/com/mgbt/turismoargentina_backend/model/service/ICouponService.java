package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Coupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICouponService {
    List<Coupon> getAll();
    Coupon save(Coupon entity);
    void delete(Coupon entity);
    Coupon findById(Long id);
    Coupon findByName(String name);
}
