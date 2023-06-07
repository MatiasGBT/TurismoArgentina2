package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRedeemedCouponRepository extends JpaRepository<RedeemedCoupon, Long> {
    RedeemedCoupon findByCouponAndUser(Coupon coupon, User user);
}
