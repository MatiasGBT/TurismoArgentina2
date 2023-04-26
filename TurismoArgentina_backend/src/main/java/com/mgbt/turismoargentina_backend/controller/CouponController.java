package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.exceptions.*;
import com.mgbt.turismoargentina_backend.model.entity.*;
import com.mgbt.turismoargentina_backend.model.service.*;
import com.mgbt.turismoargentina_backend.utility_classes.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/coupons")
public class CouponController {

    @Autowired
    ICouponService couponService;

    @Autowired
    IRedeemedCouponService redeemedCouponService;

    @Autowired
    IUserService userService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/admin/list/{page}")
    @Operation(summary = "Gets all coupons paginated.")
    @ApiResponse(responseCode = "200", description = "Array of coupons",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Integer page, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Coupon> coupons = this.couponService.getAll(pageable);
            return new ResponseEntity<>(coupons, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/admin/list/{page}/{name}")
    @Operation(summary = "Gets all coupons paginated and filtered by name.")
    @ApiResponse(responseCode = "200", description = "Array of coupons",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAllByName(@PathVariable Integer page, @PathVariable String name, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Coupon> coupons = this.couponService.getAllByName(name, pageable);
            return new ResponseEntity<>(coupons, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PostMapping("/{couponName}/{idUser}")
    @Operation(summary = "Redeems a coupon if it exists and if the user has not redeemed it previously")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coupon created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = RedeemedCouponWithMessage.class)) }),
            @ApiResponse(responseCode = "400", description = "Coupon is already used, expired or invalid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) }),
            @ApiResponse(responseCode = "404", description = "Coupon or user not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> redeemCoupon(@PathVariable String couponName,
                                          @PathVariable Long idUser, Locale locale) {
        try {
            Coupon coupon = couponService.findByName(couponName);
            User user = userService.findById(idUser);
            RedeemedCoupon redeemedCoupon = redeemedCouponService.findByCouponAndUser(coupon, user);
            redeemedCouponService.checkIfCouponIsValid(redeemedCoupon);
            redeemedCoupon = redeemedCouponService.save(redeemedCoupon);
            Map<String, Object> response = new HashMap<>();
            response.put("redeemedCoupon", redeemedCoupon);
            response.put("message", messageSource.getMessage("couponController.redeemed", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        } catch (CouponIsAlreadyUsedException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponUsed", null, locale));
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (CouponIsNotValidYetException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponNotValidYet", null, locale));
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (CouponExpiredException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponExpired", null, locale));
            response.put("error", ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/redeemed")
    @Operation(summary = "Modify a redeemed coupon with the request body.")
    @ApiResponse(responseCode = "200", description = "RedeemedCoupon updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = JsonMessage.class))})
    public ResponseEntity<?> updateRedeemedCoupon(@RequestBody RedeemedCoupon redeemedCoupon, Locale locale) {
        try {
            redeemedCouponService.save(redeemedCoupon);
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("couponController.updated", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @DeleteMapping("/admin/{id}")
    @Operation(summary = "Delete a coupon from the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coupon deleted",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class)) }),
            @ApiResponse(responseCode = "404", description = "Coupon not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> delete(@PathVariable Long id, Locale locale) {
        try {
            Coupon coupon = this.couponService.findById(id);
            this.couponService.delete(coupon);
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("couponController.deleted", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        }
    }
}
