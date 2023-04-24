package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IRedeemedCouponService {
    List<RedeemedCoupon> getAll();
    RedeemedCoupon save(RedeemedCoupon entity);
    void delete(RedeemedCoupon entity);
    RedeemedCoupon findById(Long id);
    RedeemedCoupon findByCouponAndUser(Coupon coupon, User user);
    void checkIfCouponIsValid(RedeemedCoupon redeemedCoupon);
}
