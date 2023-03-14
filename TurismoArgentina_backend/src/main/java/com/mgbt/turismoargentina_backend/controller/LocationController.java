package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.entity.Location;
import com.mgbt.turismoargentina_backend.model.service.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("api/locations/")
public class LocationController {
    private final static String FINAL_DIRECTORY = "/locations";

    @Autowired
    ILocationService locationService;

    @Autowired
    IFileService fileService;

    @Autowired
    IExceptionService exceptionService;

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

    @Operation(summary = "Gets file from locations directory by filename")
    @ApiResponse(description = "Image file", content = { @Content(mediaType = "multipart/form-data") })
    @GetMapping("/img/{fileName:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) {
        return this.fileService.getPhoto(fileName, FINAL_DIRECTORY);
    }
}
