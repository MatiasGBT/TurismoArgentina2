package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ICouponRepositoryTest {

    @Autowired
    private ICouponRepository couponRepository;

    private Coupon coupon1;
    private Coupon coupon2;
    private Coupon coupon3;

    @BeforeEach
    void setUp() {
        coupon1 = Coupon.builder()
                .name("INVIERNO2023")
                .discount(10)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        coupon2 = Coupon.builder()
                .name("VERANO2023")
                .discount(15)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        coupon3 = Coupon.builder()
                .name("INVIERNO2024")
                .discount(15)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        this.couponRepository.save(coupon1);
        this.couponRepository.save(coupon2);
        this.couponRepository.save(coupon3);
    }

    @Test
    void findByName() {
        Coupon couponFound = this.couponRepository.findByName(coupon1.getName());
        assertNotNull(couponFound);
        assertEquals(couponFound.getIdCoupon(), coupon1.getIdCoupon());
        assertEquals(couponFound.getName(), coupon1.getName());
    }

    @Test
    void findByNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Coupon> coupons = couponRepository.findByNameContaining("2023", pageable);
        assertEquals(coupons.stream().toList().size(), 2);
        assertTrue(coupons.stream().toList().contains(coupon1));
        assertTrue(coupons.stream().toList().contains(coupon2));
        assertFalse(coupons.stream().toList().contains(coupon3));
    }
}