package com.mgbt.turismoargentina_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.*;
import com.mgbt.turismoargentina_backend.model.entities.Coupon;
import com.mgbt.turismoargentina_backend.model.entities.RedeemedCoupon;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.services.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindingResult;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CouponControllerTest {

    @MockBean
    private CouponService couponService;

    @MockBean
    private UserService userService;

    @MockBean
    private RedeemedCouponService redeemedCouponService;

    @MockBean
    private ValidateService validateService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private Coupon coupon1;
    private Coupon coupon2;
    private User user;
    private RedeemedCoupon redeemedCoupon;
    private Locale locale;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        coupon1 = Coupon.builder()
                .idCoupon(1L)
                .name("INVIERNO2023")
                .discount(10)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        coupon2 = Coupon.builder()
                .idCoupon(2L)
                .name("VERANO2023")
                .discount(15)
                .startDate(new Date())
                .finishDate(new Date())
                .build();
        user = User.builder()
                .idUser(1L)
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        redeemedCoupon = RedeemedCoupon.builder()
                .idRedeemedCoupon(1L)
                .coupon(coupon1)
                .user(user)
                .date(new Date())
                .isUsed(false)
                .build();
        locale = new Locale("en_EN");
        pageable = PageRequest.of(0, 9);
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getById() throws Exception {
        when(couponService.findById(1L)).thenReturn(coupon1);
        ResultActions response = mockMvc.perform(get("/api/coupons/admin/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCoupon", is(Math.toIntExact(coupon1.getIdCoupon()))))
                .andExpect(jsonPath("$.name", is(coupon1.getName())))
                .andExpect(jsonPath("$.discount", is(coupon1.getDiscount())));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getById_EntityNotFound() throws Exception {
        when(couponService.findById(3L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/coupons/admin/3"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getAll() throws Exception {
        Page<Coupon> page = new PageImpl<>(List.of(coupon1, coupon2));
        when(couponService.getAll(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/coupons/admin?page=0"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idCoupon", is(Math.toIntExact(coupon1.getIdCoupon()))))
                .andExpect(jsonPath("$.content[0].name", is(coupon1.getName())))
                .andExpect(jsonPath("$.content[1].idCoupon", is(Math.toIntExact(coupon2.getIdCoupon()))))
                .andExpect(jsonPath("$.content[1].name", is(coupon2.getName())));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getAllByName() throws Exception {
        Page<Coupon> page = new PageImpl<>(List.of(coupon1));
        when(couponService.getAllByName("INVIERNO", pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/coupons/admin?page=0&name=INVIERNO"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idCoupon", is(Math.toIntExact(coupon1.getIdCoupon()))))
                .andExpect(jsonPath("$.content[0].name", is(coupon1.getName())));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon() throws Exception {
        when(couponService.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(userService.findById(1L)).thenReturn(user);
        when(redeemedCouponService.findByCouponAndUser(coupon1, user)).thenReturn(redeemedCoupon);
        when(redeemedCouponService.save(redeemedCoupon)).thenReturn(redeemedCoupon);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2023&idUser=1")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.redeemedCoupon.coupon.idCoupon", is(Math.toIntExact(coupon1.getIdCoupon()))))
                .andExpect(jsonPath("$.redeemedCoupon.user.idUser", is(Math.toIntExact(user.getIdUser()))))
                .andExpect(jsonPath("$.redeemedCoupon.isUsed", is(false)))
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("couponController.redeemed", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon_CouponIsAlreadyUsed() throws Exception {
        redeemedCoupon.setIsUsed(true);
        when(couponService.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(userService.findById(1L)).thenReturn(user);
        when(redeemedCouponService.findByCouponAndUser(coupon1, user)).thenReturn(redeemedCoupon);
        doThrow(CouponIsAlreadyUsedException.class).when(redeemedCouponService).validateCoupon(redeemedCoupon);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2023&idUser=1")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.couponUsed.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.couponUsed.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon_CouponIsNotValidYet() throws Exception {
        redeemedCoupon.setDate(new Date(1687230000000L)); //June 20, 2023
        coupon1.setStartDate(new Date(1687316400000L)); //June 21, 2023
        coupon1.setFinishDate(new Date(1695438000000L)); //September 23, 2023
        when(couponService.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(userService.findById(1L)).thenReturn(user);
        when(redeemedCouponService.findByCouponAndUser(coupon1, user)).thenReturn(redeemedCoupon);
        doThrow(CouponIsNotValidYetException.class).when(redeemedCouponService).validateCoupon(redeemedCoupon);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2023&idUser=1")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.couponNotValidYet.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.couponNotValidYet.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon_CouponExpired() throws Exception {
        redeemedCoupon.setDate(new Date(1695524400000L)); //September 24, 2023
        coupon1.setStartDate(new Date(1687316400000L)); //June 21, 2023
        coupon1.setFinishDate(new Date(1695438000000L)); //September 23, 2023
        when(couponService.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(userService.findById(1L)).thenReturn(user);
        when(redeemedCouponService.findByCouponAndUser(coupon1, user)).thenReturn(redeemedCoupon);
        doThrow(CouponExpiredException.class).when(redeemedCouponService).validateCoupon(redeemedCoupon);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2023&idUser=1")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.couponExpired.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.couponExpired.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon_CouponNotFound() throws Exception {
        when(couponService.findByName("INVIERNO2024")).thenThrow(EntityNotFoundException.class);
        when(userService.findById(1L)).thenReturn(user);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2024&idUser=1")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void redeemCoupon_UserNotFound() throws Exception {
        when(couponService.findByName("INVIERNO2023")).thenReturn(coupon1);
        when(userService.findById(2L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(post("/api/coupons?couponName=INVIERNO2023&idUser=2")
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void updateRedeemedCoupon() throws Exception {
        redeemedCoupon.setIsUsed(true);
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(redeemedCoupon);
        when(redeemedCouponService.save(redeemedCoupon)).thenReturn(redeemedCoupon);
        ResultActions response = mockMvc.perform(put("/api/coupons/redeemed")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("couponController.redeemedUpdated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(coupon1);
        when(couponService.save(coupon1)).thenReturn(coupon1);
        ResultActions response = mockMvc.perform(put("/api/coupons/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("couponController.updated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(coupon1);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(put("/api/coupons/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(coupon1);
        when(couponService.save(coupon1)).thenReturn(coupon1);
        ResultActions response = mockMvc.perform(post("/api/coupons/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("couponController.created", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(coupon1);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(post("/api/coupons/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void delete_EntityExist() throws Exception {
        when(couponService.findById(1L)).thenReturn(coupon1);
        doNothing().when(couponService).delete(coupon1);
        ResultActions response = mockMvc.perform(delete("/api/coupons/admin/1")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("couponController.deleted", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void delete_EntityNotFound() throws Exception {
        when(couponService.findById(3L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(delete("/api/coupons/admin/3")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }
}