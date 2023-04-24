package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Coupon;
import com.mgbt.turismoargentina_backend.model.repository.ICouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CouponService implements ICouponService{

    @Autowired
    ICouponRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Coupon> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional()
    public Coupon save(Coupon entity) {
        return save(entity);
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
