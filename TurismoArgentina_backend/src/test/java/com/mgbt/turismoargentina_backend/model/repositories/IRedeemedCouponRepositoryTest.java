package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import com.mgbt.turismoargentina_backend.model.entities.RedeemedCoupon;
import com.mgbt.turismoargentina_backend.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IRedeemedCouponRepositoryTest {

    @Autowired
    private IRedeemedCouponRepository redeemedCouponRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICouponRepository couponRepository;

    private RedeemedCoupon redeemedCoupon;
    private User user;
    private Coupon coupon;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        this.userRepository.save(user);
        coupon = Coupon.builder()
                .name("INVIERNO2023")
                .discount(10)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        this.couponRepository.save(coupon);
        redeemedCoupon = RedeemedCoupon.builder()
                .user(user)
                .coupon(coupon)
                .date(new Date())
                .isUsed(true)
                .build();
        this.redeemedCouponRepository.save(redeemedCoupon);
    }

    @Test
    void findByCouponAndUser() {
        RedeemedCoupon redeemedCouponFound = this.redeemedCouponRepository.findByCouponAndUser(coupon, user);
        assertNotNull(redeemedCouponFound);
        assertEquals(redeemedCouponFound, redeemedCoupon);
    }
}