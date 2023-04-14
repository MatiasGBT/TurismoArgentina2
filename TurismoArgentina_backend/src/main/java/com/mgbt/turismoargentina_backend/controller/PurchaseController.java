package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.entity.Purchase;
import com.mgbt.turismoargentina_backend.model.service.*;
import com.mgbt.turismoargentina_backend.utility_classes.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/purchases")
public class PurchaseController {

    @Autowired
    IPurchaseService purchaseService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/")
    @Operation(summary = "Creates a purchase with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Purchase created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = PurchaseWithMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Purchase is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> create(@Valid @RequestBody Purchase purchase, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            purchase = purchaseService.save(purchase);
            response.put("purchase", purchase);
            response.put("message", messageSource.getMessage("purchaseController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (Exception ex) {
            return this.exceptionService.throwNormalException(ex, locale);
        }
    }

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
