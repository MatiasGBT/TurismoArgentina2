package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.entity.Activity;
import com.mgbt.turismoargentina_backend.model.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.*;
import org.springframework.http.*;
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

    @GetMapping("/{page}")
    @Operation(summary = "Gets all activities paginated.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Integer page, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Activity> activities = this.activityService.getAll(pageable);
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
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

    @GetMapping("/{page}/{locationName}")
    @Operation(summary = "Gets all activities of a location (by name) paginated.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getByProvinceId(@PathVariable Integer page, @PathVariable String locationName, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Activity> activities = this.activityService.getByLocationName(pageable, locationName);
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
}
