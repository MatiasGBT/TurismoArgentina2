package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.model.entity.Province;
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
@RequestMapping("api/provinces/")
public class ProvinceController {
    private final static String FINAL_DIRECTORY = "/provinces";

    @Autowired
    IProvinceService provinceService;

    @Autowired
    IFileService fileService;

    @Autowired
    IExceptionService exceptionService;


    @GetMapping("/random")
    @Operation(summary = "Gets three random provinces.")
    @ApiResponse(responseCode = "200", description = "Array of provinces",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Province.class))) })
    public ResponseEntity<?> getThreeRandom(Locale locale) {
        try {
            List<Province> provinces = this.provinceService.getThreeRandom();
            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @Operation(summary = "Gets file from provinces directory by filename")
    @ApiResponse(description = "Image file", content = { @Content(mediaType = "multipart/form-data") })
    @GetMapping("/img/{fileName:.+}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String fileName) {
        return this.fileService.getPhoto(fileName, FINAL_DIRECTORY);
    }
}
