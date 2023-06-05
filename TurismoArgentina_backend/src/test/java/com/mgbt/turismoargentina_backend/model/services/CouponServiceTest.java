package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import com.mgbt.turismoargentina_backend.model.repositories.ICouponRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.CouponService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CouponServiceTest {

    @Mock
    private ICouponRepository couponRepository;

    @InjectMocks
    private CouponService couponService;

    private Coupon coupon1;
    private Coupon coupon2;

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
    }

    @Test
    void getAll() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Coupon> page = new PageImpl<>(List.of(coupon1, coupon2));
        when(couponRepository.findAll(pageable)).thenReturn(page);
        Page<Coupon> pageFound = couponService.getAll(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertTrue(pageFound.getContent().contains(coupon1));
        assertTrue(pageFound.getContent().contains(coupon2));
    }

    @Test
    void getAllByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Coupon> page = new PageImpl<>(List.of(coupon1));
        when(couponRepository.findByNameContaining("INVIERNO", pageable)).thenReturn(page);
        Page<Coupon> pageFound = couponService.getAllByName("INVIERNO", pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertTrue(pageFound.getContent().contains(coupon1));
        assertFalse(pageFound.getContent().contains(coupon2));
    }

    @Test
    void save() {
        when(couponRepository.save(coupon1)).thenReturn(coupon1);
        Coupon couponSaved = couponService.save(coupon1);
        verify(couponRepository, times(1)).save(coupon1);
        assertNotNull(couponSaved);
    }

    @Test
    void delete() {
        coupon1.setIdCoupon(1L);
        willDoNothing().given(couponRepository).delete(coupon1);
        couponService.delete(coupon1);
        verify(couponRepository, times(1)).delete(coupon1);
    }

    @Test
    void findById() {
        coupon1.setIdCoupon(1L);
        when(couponRepository.findById(1L)).thenReturn(Optional.ofNullable(coupon1));
        when(couponRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        Coupon couponFound = couponService.findById(1L);
        assertNotNull(couponFound);
        assertEquals(couponFound, coupon1);
        assertThrows(EntityNotFoundException.class, () ->  couponService.findById(2L));
    }

    @Test
    void findByName() {
        when(couponRepository.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(couponRepository.findByName("INVIERNO2024")).thenThrow(EntityNotFoundException.class);
        Coupon couponFound = couponService.findByName("INVIERNO2023");
        assertNotNull(couponFound);
        assertEquals(couponFound, coupon1);
        assertThrows(EntityNotFoundException.class, () ->  couponService.findByName("INVIERNO2024"));
    }
}