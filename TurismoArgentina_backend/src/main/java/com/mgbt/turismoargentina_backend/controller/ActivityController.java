package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Activity;
import com.mgbt.turismoargentina_backend.model.service.*;
import com.mgbt.turismoargentina_backend.utility_classes.InternalServerError;
import com.mgbt.turismoargentina_backend.utility_classes.JsonMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/activities/")
public class ActivityController {
    private final static String FINAL_DIRECTORY = "/activities";

    @Autowired
    IActivityService activityService;

    @Autowired
    IFileService fileService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/list/{page}&{deleted}")
    @Operation(summary = "Gets all activities paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Integer page, @PathVariable Boolean deleted, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Activity> activities;
            if (!deleted) activities = this.activityService.getAllNonDeleted(pageable);
            else activities = this.activityService.getAllDeleted(pageable);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets an activity by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)) }),
            @ApiResponse(responseCode = "404", description = "Activity not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@PathVariable Long id, Locale locale) {
        try {
            Activity activity = this.activityService.findById(id);
            return new ResponseEntity<>(activity, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        }
    }

    @GetMapping("/random")
    @Operation(summary = "Gets five random activities.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Activity.class))) })
    public ResponseEntity<?> getFiveRandom(Locale locale) {
        try {
            List<Activity> activities = this.activityService.getFiveRandom();
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/location/{locationName}/{page}")
    @Operation(summary = "Gets all activities of a location (by name) paginated.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getByLocationName(@PathVariable Integer page, @PathVariable String locationName, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Activity> activities = this.activityService.getByLocationName(pageable, locationName);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/location/{idLocation}")
    @Operation(summary = "Gets all activities of a location (by id) paginated.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Activity.class)) })
    public ResponseEntity<?> getByLocationId(@PathVariable Long idLocation, Locale locale) {
        try {
            List<Activity> activities = this.activityService.getByLocationId(idLocation);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @Operation(summary = "Gets file from locations directory by filename")
    @ApiResponse(description = "Image file", content = { @Content(mediaType = "multipart/form-data") })
    @GetMapping("/img/{fileName:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) {
        return this.fileService.getPhoto(fileName, FINAL_DIRECTORY);
    }

    @GetMapping("/admin/count")
    @Operation(summary = "Gets number of registered activities.")
    @ApiResponse(responseCode = "200", description = "Registered activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getCount(Locale locale) {
        try {
            Long count = this.activityService.getCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PutMapping("/admin")
    @Operation(summary = "Modify an activity with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "String message",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Activity is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> modify(@Valid @RequestBody Activity activity, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            activityService.save(activity);
            response.put("message", messageSource.getMessage("activityController.edited", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
