package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.exceptions.*;
import com.mgbt.turismoargentina_backend.model.entities.Purchase;
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
@RequestMapping("api/purchases")
public class PurchaseController {

    @Autowired
    IPurchaseService purchaseService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    IValidateService validateService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/list/{idUser}/{page}")
    @Operation(summary = "Gets all purchases paginated and filtered by user.")
    @ApiResponse(responseCode = "200", description = "Array of purchases",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Long idUser, @PathVariable Integer page, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Purchase> purchases = this.purchaseService.getByUser(idUser, pageable);
            return new ResponseEntity<>(purchases, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a purchase by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Purchase.class)) }),
            @ApiResponse(responseCode = "404", description = "Purchase not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@PathVariable Long id, Locale locale) {
        try {
            Purchase purchase = this.purchaseService.findById(id);
            return new ResponseEntity<>(purchase, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(locale);
        }
    }

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
            validateService.validateResult(result);
            purchaseService.validateIfLocationsAndActivitiesAreEmpty(purchase);
            Map<String, Object> response = new HashMap<>();
            purchase = purchaseService.save(purchase);
            response.put("purchase", purchase);
            response.put("message", messageSource.getMessage("purchaseController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        } catch (PurchaseIncompleteException ex) {
            return exceptionService.throwPurchaseIncompleteException(locale);
        } catch (Exception ex) {
            return this.exceptionService.throwNormalException(ex, locale);
        }
    }

    @PutMapping("/")
    @Operation(summary = "Updates a purchase with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Purchase is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> update(@Valid @RequestBody Purchase purchase, BindingResult result, Locale locale) {
        try {
            validateService.validateResult(result);
            purchaseService.validateIfLocationsAndActivitiesAreEmpty(purchase);
            Map<String, Object> response = new HashMap<>();
            purchaseService.save(purchase);
            response.put("message", messageSource.getMessage("purchaseController.updated", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        } catch (PurchaseIncompleteException ex) {
            return exceptionService.throwPurchaseIncompleteException(locale);
        } catch (Exception ex) {
            return this.exceptionService.throwNormalException(ex, locale);
        }
    }

    @GetMapping("/admin/count/{refunded}")
    @Operation(summary = "Gets number of refunded or non refunded purchases.")
    @ApiResponse(responseCode = "200", description = "Gets number of refunded or non refunded purchases",
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
    @Operation(summary = "Gets total money of refunded or non refunded purchases.")
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
