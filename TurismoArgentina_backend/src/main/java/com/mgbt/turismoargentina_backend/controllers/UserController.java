package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.model.services.IExceptionService;
import com.mgbt.turismoargentina_backend.model.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.Locale;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    IExceptionService exceptionService;

    @GetMapping("/admin/count")
    @Operation(summary = "Gets number of registered users.")
    @ApiResponse(responseCode = "200", description = "Registered users",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getCount(Locale locale) {
        try {
            Long count = this.userService.getCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
