package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Activity;
import com.mgbt.turismoargentina_backend.model.service.*;
import com.mgbt.turismoargentina_backend.utility_classes.*;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("api/activities")
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

    @GetMapping("/list/{page}/{deleted}")
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

    @GetMapping("/list/{page}/{deleted}/{name}")
    @Operation(summary = "Gets all activities paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null and name like inserted name.")
    @ApiResponse(responseCode = "200", description = "Array of activities",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAllByName(@PathVariable Integer page, @PathVariable Boolean deleted,
                                          @PathVariable String name, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Activity> activities;
            if (!deleted) activities = this.activityService.getAllNonDeletedByName(pageable, name);
            else activities = this.activityService.getAllDeletedByName(pageable, name);
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
    @Operation(summary = "Update an activity with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Activity is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> update(@Valid @RequestBody Activity activity, BindingResult result, Locale locale) {
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

    @PostMapping("/admin")
    @Operation(summary = "Creates an activity with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Activity created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Activity is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> create(@Valid @RequestBody Activity activity, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            activity = activityService.save(activity);
            response.put("activity", activity);
            response.put("message", messageSource.getMessage("activityController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PostMapping("/admin/img")
    @Operation(summary = "Upload an image of an activity and removes the previous one if it had one.")
    @ApiResponse(responseCode = "200", description = "Image saved successfully",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class)) })
    public ResponseEntity<?> uploadPhoto(@RequestParam MultipartFile image,
                                         @RequestParam("id") Long idActivity,
                                         @RequestParam("imageNumber") Integer imageNumber,
                                         Locale locale) {
        try {
            Map<String, Object> response = new HashMap<>();
            Activity activity = activityService.findById(idActivity);
            String fileName = fileService.save(image, FINAL_DIRECTORY);
            String previousImage;
            if (imageNumber == 1) {
                previousImage = activity.getImage1();
                activity.setImage1(fileName);
            } else if (imageNumber == 2) {
                previousImage = activity.getImage2();
                activity.setImage2(fileName);
            } else if (imageNumber == 3) {
                previousImage = activity.getImage3();
                activity.setImage3(fileName);
            } else {
                response.put("message", messageSource.getMessage("error.imageNumber.message", null, locale));
                response.put("error", messageSource.getMessage("error.imageNumber.error", null, locale));
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (previousImage != null && !previousImage.isBlank()) {
                fileService.delete(previousImage, FINAL_DIRECTORY);
            }
            activityService.save(activity);
            response.put("message", messageSource.getMessage("image.upload", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException ex) {
            return this.exceptionService.throwIOException(ex, locale);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
