package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Coupon;
import com.mgbt.turismoargentina_backend.model.repository.ICouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CouponService implements ICouponService{

    @Autowired
    ICouponRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Coupon> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Coupon> getAllByName(String name, Pageable pageable) {
        return repository.findByNameContaining(name, pageable);
    }

    @Override
    @Transactional()
    public Coupon save(Coupon entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional()
    public void delete(Coupon entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Coupon findById(Long id) {
        Coupon coupon = repository.findById(id).orElse(null);
        if (coupon == null) throw new EntityNotFoundException("Coupon not found");
        return coupon;
    }

    @Override
    @Transactional(readOnly = true)
    public Coupon findByName(String name) {
        Coupon coupon = repository.findByName(name);
        if (coupon == null) throw new EntityNotFoundException("Coupon not found");
        return coupon;
    }
}
