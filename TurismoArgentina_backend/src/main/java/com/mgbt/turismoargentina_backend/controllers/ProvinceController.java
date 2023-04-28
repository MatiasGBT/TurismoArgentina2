package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.services.*;
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
@RequestMapping("api/provinces")
public class ProvinceController {
    private final static String FINAL_DIRECTORY = "/provinces";

    @Autowired
    IProvinceService provinceService;

    @Autowired
    IFileService fileService;

    @Autowired
    IExceptionService exceptionService;

    @Autowired
    IValidateService validateService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/list/{page}/{deleted}")
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

    @GetMapping("/list/{page}/{deleted}/{name}")
    @Operation(summary = "Gets all provinces paginated and filtered by deletionDate is (deleted=true) or not (deleted=false) null and name like inserted name.")
    @ApiResponse(responseCode = "200", description = "Array of provinces",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class)) })
    public ResponseEntity<?> getAllByName(@PathVariable Integer page, @PathVariable Boolean deleted,
                                    @PathVariable String name, Locale locale) {
        try {
            Pageable pageable = PageRequest.of(page, 9);
            Page<Province> provinces;
            if (!deleted) provinces = this.provinceService.getAllNonDeletedByName(pageable, name);
            else provinces = this.provinceService.getAllDeletedByName(pageable, name);
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
            return this.exceptionService.throwEntityNotFoundException(locale);
        }
    }

    @GetMapping("")
    @Operation(summary = "Gets a province by name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Province object",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Province.class)) }),
            @ApiResponse(responseCode = "404", description = "Province not found",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> getByName(@RequestParam String name, Locale locale) {
        try {
            Province province = this.provinceService.findByName(name);
            return new ResponseEntity<>(province, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (EntityNotFoundException ex) {
            return this.exceptionService.throwEntityNotFoundException(locale);
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
    @Operation(summary = "Update a province with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Province updated",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Province is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> update(@Valid @RequestBody Province province, BindingResult result, Locale locale) {
        try {
            validateService.checkIfResultHasErrors(result);
            Map<String, Object> response = new HashMap<>();
            provinceService.save(province);
            response.put("message", messageSource.getMessage("provinceController.updated", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        }
    }

    @PostMapping("/admin")
    @Operation(summary = "Creates a province with the request body.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Province created",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProvinceWithMessage.class))}),
            @ApiResponse(responseCode = "400", description = "Province is not valid",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = InternalServerError.class)) })
    })
    public ResponseEntity<?> create(@Valid @RequestBody Province province, BindingResult result, Locale locale) {
        try {
            validateService.checkIfResultHasErrors(result);
            Map<String, Object> response = new HashMap<>();
            province = provinceService.save(province);
            response.put("province", province);
            response.put("message", messageSource.getMessage("provinceController.created", null, locale));
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        } catch (ResultHasErrorsException ex) {
            return exceptionService.throwResultHasErrorsException(result, locale);
        } catch (Exception ex) {
            return this.exceptionService.throwNormalException(ex, locale);
        }
    }

    @PostMapping("/admin/img")
    @Operation(summary = "Upload an image of a province and removes the previous one if it had one.")
    @ApiResponse(responseCode = "200", description = "Image saved successfully",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = JsonMessage.class)) })
    public ResponseEntity<?> uploadPhoto(@RequestParam MultipartFile image,
                                         @RequestParam("id") Long idProvince,
                                         Locale locale) {
        try {
            Map<String, Object> response = new HashMap<>();
            Province province = provinceService.findById(idProvince);
            String fileName = fileService.save(image, FINAL_DIRECTORY);
            String previousImage = province.getImage();
            if (previousImage != null) {
                fileService.delete(previousImage, FINAL_DIRECTORY);
            }
            province.setImage(fileName);
            provinceService.save(province);
            response.put("message", messageSource.getMessage("image.upload", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IOException ex) {
            return this.exceptionService.throwIOException(ex, locale);
        } catch (DataAccessException ex) {
            return this.exceptionService.throwDataAccessException(ex, locale);
        }
    }
}
