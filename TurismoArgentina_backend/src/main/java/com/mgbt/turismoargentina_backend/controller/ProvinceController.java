package com.mgbt.turismoargentina_backend.controller;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Province;
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

    @Autowired
    MessageSource messageSource;

    @GetMapping("/list/{page}&{deleted}")
    @Operation(summary = "Gets all provinces paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null.")
    @ApiResponse(responseCode = "200", description = "Array of provinces",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAll(@PathVariable Integer page, @PathVariable Boolean deleted, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Province> provinces;
            if (!deleted) provinces = this.provinceService.getAllNonDeleted(pageable);
            else provinces = this.provinceService.getAllDeleted(pageable);
            return new ResponseEntity<>(provinces, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets a province by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Province object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Province.class)) }),
            @ApiResponse(responseCode = "404", description = "Province not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getById(@PathVariable Long id, Locale locale) {
        try {
            Province province = this.provinceService.findById(id);
            return new ResponseEntity<>(province, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(ex, locale);
        }
    }

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

    @GetMapping("/names")
    @Operation(summary = "Get all the names of provinces.")
    @ApiResponse(responseCode = "200", description = "Array of strings",
            content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = String.class))) })
    public ResponseEntity<?> getAllProvinceNames(Locale locale) {
        try {
            List<String> provinceNames = this.provinceService.getAllProvinceNames();
            return new ResponseEntity<>(provinceNames, HttpStatus.OK);
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

    @GetMapping("/admin/count")
    @Operation(summary = "Gets number of registered provinces.")
    @ApiResponse(responseCode = "200", description = "Registered provinces",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<?> getCount(Locale locale) {
        try {
            Long count = this.provinceService.getCount();
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }

    @PutMapping("/admin")
    @Operation(summary = "Modify a province with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "String message",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Province is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> modify(@Valid @RequestBody Province province, BindingResult result, Locale locale) {
        try {
            if (result.hasErrors())  return exceptionService.throwValidationErrorsException(result, locale);
            Map<String, Object> response = new HashMap<>();
            provinceService.save(province);
            response.put("message", messageSource.getMessage("provinceController.edited", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
