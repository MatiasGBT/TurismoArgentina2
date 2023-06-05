package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.CouponExpiredException;
import com.mgbt.turismoargentina_backend.exceptions.CouponIsAlreadyUsedException;
import com.mgbt.turismoargentina_backend.exceptions.CouponIsNotValidYetException;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import com.mgbt.turismoargentina_backend.model.entities.RedeemedCoupon;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.repositories.IRedeemedCouponRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.RedeemedCouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RedeemedCouponServiceTest {

    @Mock
    private IRedeemedCouponRepository redeemedCouponRepository;

    @InjectMocks
    private RedeemedCouponService redeemedCouponService;

    private User user1;
    private User user2;
    private Coupon coupon;
    private RedeemedCoupon redeemedCoupon1;
    private RedeemedCoupon redeemedCoupon2;

    @BeforeEach
    void setUp() {
        coupon = Coupon.builder()
                .name("INVIERNO2023")
                .discount(10)
                .startDate(new Date(1687316400000L)) //June 21, 2023
                .finishDate(new Date(1695438000000L)) //September 23, 2023
                .build();
        user1 = User.builder()
                .username("albert")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        user2 = User.builder()
                .username("chris")
                .name("Chris")
                .lastName("Redfield")
                .creationDate(new Date())
                .build();
        redeemedCoupon1 = RedeemedCoupon.builder()
                .user(user1)
                .coupon(coupon)
                .date(new Date())
                .isUsed(true)
                .build();
        redeemedCoupon2 = RedeemedCoupon.builder()
                .user(user2)
                .coupon(coupon)
                .date(new Date())
                .isUsed(false)
                .build();
    }

    @Test
    void getAll() {
        when(redeemedCouponRepository.findAll()).thenReturn(List.of(redeemedCoupon1, redeemedCoupon2));
        List<RedeemedCoupon> redeemedCoupons = redeemedCouponService.getAll();
        assertNotNull(redeemedCoupons);
        assertEquals(redeemedCoupons.size(), 2);
        assertTrue(redeemedCoupons.contains(redeemedCoupon1));
        assertTrue(redeemedCoupons.contains(redeemedCoupon2));
    }

    @Test
    void save() {
        when(redeemedCouponRepository.save(redeemedCoupon1)).thenReturn(redeemedCoupon1);
        RedeemedCoupon redeemedCouponSaved = redeemedCouponService.save(redeemedCoupon1);
        verify(redeemedCouponRepository, times(1)).save(redeemedCoupon1);
        assertNotNull(redeemedCouponSaved);
    }

    @Test
    void delete() {
        redeemedCoupon1.setIdRedeemedCoupon(1L);
        willDoNothing().given(redeemedCouponRepository).delete(redeemedCoupon1);
        redeemedCouponService.delete(redeemedCoupon1);
        verify(redeemedCouponRepository, times(1)).delete(redeemedCoupon1);
    }

    @Test
    void findById() {
        redeemedCoupon1.setIdRedeemedCoupon(1L);
        when(redeemedCouponRepository.findById(1L)).thenReturn(Optional.ofNullable(redeemedCoupon1));
        when(redeemedCouponRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        RedeemedCoupon redeemedCouponFound = redeemedCouponService.findById(1L);
        assertNotNull(redeemedCouponFound);
        assertEquals(redeemedCouponFound, redeemedCoupon1);
        assertThrows(EntityNotFoundException.class, () ->  redeemedCouponService.findById(2L));
    }

    @Test
    void findByCouponAndUser() {
        redeemedCoupon1.setIdRedeemedCoupon(1L);
        when(redeemedCouponRepository.findByCouponAndUser(coupon, user1)).thenReturn(redeemedCoupon1);
        RedeemedCoupon redeemedCouponFound = redeemedCouponService.findByCouponAndUser(coupon, user1);
        assertNotNull(redeemedCouponFound);
        assertEquals(redeemedCouponFound, redeemedCoupon1);
        assertEquals(redeemedCouponFound.getCoupon(), redeemedCoupon1.getCoupon());
        assertEquals(redeemedCouponFound.getUser(), redeemedCoupon1.getUser());
    }

    @Test
    void checkIfCouponIsValid() {
        assertThrows(CouponIsAlreadyUsedException.class, () -> redeemedCouponService.validateCoupon(redeemedCoupon1));
        redeemedCoupon1.setDate(new Date(1684119600000L)); //May 15, 2023
        redeemedCoupon2.setDate(new Date(1701745200000L)); //December 5, 2023
        redeemedCoupon1.setIsUsed(false);
        assertThrows(CouponIsNotValidYetException.class, () -> redeemedCouponService.validateCoupon(redeemedCoupon1));
        assertThrows(CouponExpiredException.class, () -> redeemedCouponService.validateCoupon(redeemedCoupon2));
    }
}