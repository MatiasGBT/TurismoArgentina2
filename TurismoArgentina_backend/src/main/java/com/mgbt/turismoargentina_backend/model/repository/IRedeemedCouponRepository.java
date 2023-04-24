package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRedeemedCouponRepository extends JpaRepository<RedeemedCoupon, Long> {
    RedeemedCoupon findByCouponAndUser(Coupon coupon, User user);
}
