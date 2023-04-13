package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Location;
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
@RequestMapping("api/locations")
public class LocationController {
    private final static String FINAL_DIRECTORY = "/locations";

    @Autowired
    ILocationService locationService;

    @Autowired
    IFileService fileService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/list/{page}/{deleted}")
    @Operation(summary = "Gets all locations paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null.")
    @ApiResponse(responseCode = "200", description = "Array of locations",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Integer page, @PathVariable Boolean deleted, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Location> locations;
            if (!deleted) locations = this.locationService.getAllNonDeleted(pageable);
            else locations = this.locationService.getAllDeleted(pageable);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/list/{page}/{deleted}/{name}")
    @Operation(summary = "Gets all locations paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null and name like inserted name.")
    @ApiResponse(responseCode = "200", description = "Array of locations",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAllByName(@PathVariable Integer page, @PathVariable Boolean deleted,
                                    @PathVariable String name, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Location> locations;
            if (!deleted) locations = this.locationService.getAllNonDeletedByName(pageable, name);
            else locations = this.locationService.getAllDeletedByName(pageable, name);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a location by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@PathVariable Long id, Locale locale) {
        try {
            Location location = this.locationService.findById(id);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        }
    }

    @GetMapping("")
    @Operation(summary = "Gets a location by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Location.class)) }),
            @ApiResponse(responseCode = "404", description = "Location not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@RequestParam String name, Locale locale) {
        try {
            Location location = this.locationService.findByName(name);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        }
    }

    @GetMapping("/random")
    @Operation(summary = "Gets four random locations.")
    @ApiResponse(responseCode = "200", description = "Array of locations",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Location.class))) })
    public ResponseEntity<?> getFourRandom(Locale locale) {
        try {
            List<Location> locations = this.locationService.getFourRandom();
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/names")
    @Operation(summary = "Get all the names of locations.")
    @ApiResponse(responseCode = "200", description = "Array of strings",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class))) })
    public ResponseEntity<?> getAllLocationNames(Locale locale) {
        try {
            List<String> locationNames = this.locationService.getAllLocationNames();
            return new ResponseEntity<>(locationNames, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/province/{provinceName}/{page}")
    @Operation(summary = "Gets all locations of a province (by name) paginated.")
    @ApiResponse(responseCode = "200", description = "Array of locations",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getByProvinceName(@PathVariable Integer page, @PathVariable String provinceName, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Location> locations = this.locationService.getByProvinceName(pageable, provinceName);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/province/{idProvince}")
    @Operation(summary = "Gets all locations of a province (by id).")
    @ApiResponse(responseCode = "200", description = "Array of locations",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Location.class)) })
    public ResponseEntity<?> getByProvinceId(@PathVariable Long idProvince, Locale locale) {
        try {
            List<Location> locations = this.locationService.getByProvinceId(idProvince);
            return new ResponseEntity<>(locations, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/img/{fileName:.+}")
    @Operation(summary = "Gets file from locations directory by filename")
    @ApiResponse(description = "Image file", content = { @Content(mediaType = "multipart/form-data") })
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) {
        return this.fileService.getPhoto(fileName, FINAL_DIRECTORY);
    }

    @GetMapping("/admin/count")
    @Operation(summary = "Gets number of registered locations.")
    @ApiResponse(responseCode = "200", description = "Registered locations",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getCount(Locale locale) {
        try {
            Long count = this.locationService.getCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PutMapping("/admin")
    @Operation(summary = "Modify a location with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Location is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> update(@Valid @RequestBody Location location, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            locationService.save(location);
            response.put("message", messageSource.getMessage("locationController.edited", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PostMapping("/admin")
    @Operation(summary = "Creates a location with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Location is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> create(@Valid @RequestBody Location location, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            location = locationService.save(location);
            response.put("location", location);
            response.put("message", messageSource.getMessage("locationController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PostMapping("/admin/img")
    @Operation(summary = "Upload an image of a location and removes the previous one if it had one.")
    @ApiResponse(responseCode = "200", description = "Image saved successfully",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class)) })
    public ResponseEntity<?> uploadPhoto(@RequestParam MultipartFile image,
                                         @RequestParam("id") Long idLocation,
                                         Locale locale) {
        try {
            Map<String, Object> response = new HashMap<>();
            Location location = locationService.findById(idLocation);
            String fileName = fileService.save(image, FINAL_DIRECTORY);
            String previousImage = location.getImage();
            if (previousImage != null) {
                fileService.delete(previousImage, FINAL_DIRECTORY);
            }
            location.setImage(fileName);
            locationService.save(location);
            response.put("message", messageSource.getMessage("image.upload", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException ex) {
            return this.exceptionService.throwIOException(ex, locale);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
