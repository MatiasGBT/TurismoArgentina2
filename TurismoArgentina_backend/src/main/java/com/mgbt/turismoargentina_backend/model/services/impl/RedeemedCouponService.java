package com.mgbt.turismoargentina_backend.model.services.impl;

import com.mgbt.turismoargentina_backend.exceptions.CouponExpiredException;
import com.mgbt.turismoargentina_backend.exceptions.CouponIsAlreadyUsedException;
import com.mgbt.turismoargentina_backend.exceptions.CouponIsNotValidYetException;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.*;
import com.mgbt.turismoargentina_backend.model.repositories.IRedeemedCouponRepository;
import com.mgbt.turismoargentina_backend.model.services.IRedeemedCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RedeemedCouponService implements IRedeemedCouponService {

    @Autowired
    IRedeemedCouponRepository repository;

    @Override
    public List<RedeemedCoupon> getAll() {
        return repository.findAll();
    }

    @Override
    public RedeemedCoupon save(RedeemedCoupon entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(RedeemedCoupon entity) {
        repository.delete(entity);
    }

    @Override
    public RedeemedCoupon findById(Long id) {
        RedeemedCoupon redeemedCoupon = repository.findById(id).orElse(null);
        if (redeemedCoupon == null) throw new EntityNotFoundException("Redeemed coupon not found");
        return redeemedCoupon;
    }

    @Override
    public RedeemedCoupon findByCouponAndUser(Coupon coupon, User user) {
        RedeemedCoupon redeemedCoupon = repository.findByCouponAndUser(coupon, user);
        if (redeemedCoupon == null) redeemedCoupon = new RedeemedCoupon(user, coupon);
        return redeemedCoupon;
    }

    @Override
    public void validateCoupon(RedeemedCoupon redeemedCoupon) {
        if (redeemedCoupon.getIsUsed()) throw new CouponIsAlreadyUsedException("The coupon has already been used");
        long redeemedCouponDate = redeemedCoupon.getDate().getTime();
        long couponStartDate = redeemedCoupon.getCoupon().getStartDate().getTime();
        long couponFinishDate = redeemedCoupon.getCoupon().getFinishDate().getTime();
        if (redeemedCouponDate < couponStartDate) throw new CouponIsNotValidYetException("The coupon is not valid yet");
        if (redeemedCouponDate > couponFinishDate) throw new CouponExpiredException("The coupon expired");
    }
}
