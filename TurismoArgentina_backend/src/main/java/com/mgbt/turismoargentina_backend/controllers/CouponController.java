package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.exceptions.*;
import com.mgbt.turismoargentina_backend.model.entities.*;
import com.mgbt.turismoargentina_backend.model.services.*;
import com.mgbt.turismoargentina_backend.utility_classes.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
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
    IValidateService validateService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/admin/{id}")
    @Operation(summary = "Gets a coupon by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coupon object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Coupon.class)) }),
            @ApiResponse(responseCode = "404", description = "Coupon not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@PathVariable Long id, Locale locale) {
        try {
            Coupon coupon = this.couponService.findById(id);
            return new ResponseEntity<>(coupon, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(locale);
        }
    }

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
            redeemedCouponService.validateCoupon(redeemedCoupon);
            redeemedCoupon = redeemedCouponService.save(redeemedCoupon);
            Map<String, Object> response = new HashMap<>();
            response.put("redeemedCoupon", redeemedCoupon);
            response.put("message", messageSource.getMessage("couponController.redeemed", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(locale);
        } catch (CouponIsAlreadyUsedException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponUsed.message", null, locale));
            response.put("error", messageSource.getMessage("error.couponUsed.error", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (CouponIsNotValidYetException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponNotValidYet.message", null, locale));
            response.put("error", messageSource.getMessage("error.couponNotValidYet.error", null, locale));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (CouponExpiredException ex) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("error.couponExpired.message", null, locale));
            response.put("error", messageSource.getMessage("error.couponExpired.error", null, locale));
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
            response.put("message", messageSource.getMessage("couponController.redeemedUpdated", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PutMapping("/admin")
    @Operation(summary = "Update a coupon with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Coupon updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Coupon is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> update(@Valid @RequestBody Coupon coupon, BindingResult result, Locale locale) {
        try {
            validateService.validateResult(result);
            Map<String, Object> response = new HashMap<>();
            couponService.save(coupon);
            response.put("message", messageSource.getMessage("couponController.updated", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        }
    }

    @PostMapping("/admin")
    @Operation(summary = "Creates a coupon with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Coupon created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CouponWithMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Coupon is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> create(@Valid @RequestBody Coupon coupon, BindingResult result, Locale locale) {
        try {
            validateService.validateResult(result);
            Map<String, Object> response = new HashMap<>();
            coupon = couponService.save(coupon);
            response.put("coupon", coupon);
            response.put("message", messageSource.getMessage("couponController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        } catch (Exception ex) {
            return this.exceptionService.throwNormalException(ex, locale);
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
            return this.exceptionService.throwEntityNotFoundException(locale);
        }
    }
}
