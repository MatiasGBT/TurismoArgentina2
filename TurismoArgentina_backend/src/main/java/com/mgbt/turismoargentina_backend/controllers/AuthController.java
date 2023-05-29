package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.services.*;
import com.mgbt.turismoargentina_backend.utility_classes.JsonUserMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    IUserService userService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    MessageSource messageSource;

    @Operation(description = "Receives a user in the request body and, if it exists in the DB, checks if it is updated and returns it, if it does not exist, creates it")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonUserMessage.class)) }),
            @ApiResponse(responseCode = "201",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonUserMessage.class)) })
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, Locale locale) {
        try {
            User userFound = userService.findByUsername(user.getUsername());
            userService.checkIfUserIsPersisted(userFound, user); //Needed if the user updates their first and last name from Keycloak account dashboard
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("authController.userFound", null, locale));
            response.put("user", userFound);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            this.userService.save(user);
            Map<String, Object> response = new HashMap<>();
            response.put("message", messageSource.getMessage("authController.userCreated", null, locale));
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
