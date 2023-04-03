package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("api/purchases/")
public class PurchaseController {

    @Autowired
    IPurchaseService purchaseService;

    @Autowired
    IExceptionService exceptionService;

    @GetMapping("/admin/count/{refunded}")
    @Operation(summary = "Gets number of refunded or non refunded purchases.")
    @ApiResponse(responseCode = "200", description = "Refunded or non refunded purchases",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getCount(@PathVariable boolean refunded, Locale locale) {
        try {
            Long count;
            if (refunded) {
                count = this.purchaseService.getCountIsRefunded();
            } else {
                count = this.purchaseService.getCountIsNotRefunded();
            }
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/admin/money/{refunded}")
    @Operation(summary = "Gets number of refunded or non refunded purchases.")
    @ApiResponse(responseCode = "200", description = "Refunded or non refunded purchases",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getMoney(@PathVariable boolean refunded, Locale locale) {
        try {
            Double money;
            if (refunded) {
                money = this.purchaseService.getMoneyRefunded();
            } else {
                money = this.purchaseService.getMoneyNotRefunded();
            }
            if (money == null) { money = 0.0; }
            return new ResponseEntity<>(money, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
