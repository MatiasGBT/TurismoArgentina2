package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.RedeemedCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IRedeemedCouponRepository extends JpaRepository<RedeemedCoupon, Long> {
    public List<RedeemedCoupon> findByUserIdUser(Long idUser);
}
