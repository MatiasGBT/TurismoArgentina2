package com.mgbt.turismoargentina_backend.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.services.impl.FileService;
import com.mgbt.turismoargentina_backend.model.services.impl.ProvinceService;
import com.mgbt.turismoargentina_backend.model.services.impl.ValidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@SpringBootTest
@AutoConfigureMockMvc
class ProvinceControllerTest {

    @MockBean
    private ProvinceService provinceService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ValidateService validateService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private Province buenosAires;
    private Province salta;
    private Province misiones;
    private Province rioNegro;
    private Locale locale;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        buenosAires = Province.builder()
                .idProvince(1L)
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        salta = Province.builder()
                .idProvince(2L)
                .name("Salta")
                .description("desc")
                .image("salta.jpg")
                .deletionDate(null)
                .build();
        misiones = Province.builder()
                .idProvince(3L)
                .name("Misiones")
                .description("desc")
                .image("misiones.jpg")
                .deletionDate(new Date())
                .build();
        rioNegro = Province.builder()
                .idProvince(4L)
                .name("Río Negro")
                .description("desc")
                .image("rionegro.jpg")
                .deletionDate(new Date())
                .build();
        locale = new Locale("en_EN");
        pageable = PageRequest.of(0, 9);
    }

    @Test
    void getAllNonDeleted() throws Exception {
        Page<Province> page = new PageImpl<>(List.of(buenosAires, salta));
        when(provinceService.getAllNonDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/provinces/list/0/false"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idProvince", is(Math.toIntExact(buenosAires.getIdProvince()))))
                .andExpect(jsonPath("$.content[0].name", is(buenosAires.getName())))
                .andExpect(jsonPath("$.content[1].idProvince", is(Math.toIntExact(salta.getIdProvince()))))
                .andExpect(jsonPath("$.content[1].name", is(salta.getName())));
    }

    @Test
    void getAllDeleted() throws Exception {
        Page<Province> page = new PageImpl<>(List.of(misiones, rioNegro));
        when(provinceService.getAllDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/provinces/list/0/true"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idProvince", is(Math.toIntExact(misiones.getIdProvince()))))
                .andExpect(jsonPath("$.content[0].name", is(misiones.getName())))
                .andExpect(jsonPath("$.content[1].idProvince", is(Math.toIntExact(rioNegro.getIdProvince()))))
                .andExpect(jsonPath("$.content[1].name", is(rioNegro.getName())));

    }

    @Test
    void getAllNonDeletedByName() throws Exception {
        Page<Province> page = new PageImpl<>(List.of(buenosAires));
        when(provinceService.getAllNonDeletedByName(pageable, "Buenos")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/provinces/list/0/false/Buenos"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idProvince", is(Math.toIntExact(buenosAires.getIdProvince()))))
                .andExpect(jsonPath("$.content[0].name", is(buenosAires.getName())));
    }

    @Test
    void getAllDeletedByName() throws Exception {
        Page<Province> page = new PageImpl<>(List.of(misiones));
        when(provinceService.getAllDeletedByName(pageable, "iones")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/provinces/list/0/true/iones"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idProvince", is(Math.toIntExact(misiones.getIdProvince()))))
                .andExpect(jsonPath("$.content[0].name", is(misiones.getName())));
    }

    @Test
    void getById() throws Exception {
        when(provinceService.findById(1L)).thenReturn(buenosAires);
        ResultActions response = mockMvc.perform(get("/api/provinces/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProvince", is(Math.toIntExact(buenosAires.getIdProvince()))))
                .andExpect(jsonPath("$.name", is(buenosAires.getName())))
                .andExpect(jsonPath("$.description", is(buenosAires.getDescription())))
                .andExpect(jsonPath("$.image", is(buenosAires.getImage())));
    }

    @Test
    void getById_EntityNotFound() throws Exception {
        when(provinceService.findById(3L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/provinces/3"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void getByName() throws Exception {
        when(provinceService.findByName("Salta")).thenReturn(salta);
        ResultActions response = mockMvc.perform(get("/api/provinces?name=Salta"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idProvince", is(Math.toIntExact(salta.getIdProvince()))))
                .andExpect(jsonPath("$.name", is(salta.getName())))
                .andExpect(jsonPath("$.description", is(salta.getDescription())))
                .andExpect(jsonPath("$.image", is(salta.getImage())));
    }

    @Test
    void getByName_EntityNotFound() throws Exception {
        when(provinceService.findByName("Misiones")).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/provinces?name=Misiones"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void getThreeRandom() throws Exception {
        misiones.setDeletionDate(null);
        when(provinceService.getThreeRandom()).thenReturn(List.of(buenosAires, salta, misiones));
        ResultActions response = mockMvc.perform(get("/api/provinces/random"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$.[0].idProvince", is(Math.toIntExact(buenosAires.getIdProvince()))))
                .andExpect(jsonPath("$.[0].name", is(buenosAires.getName())))
                .andExpect(jsonPath("$.[1].idProvince", is(Math.toIntExact(salta.getIdProvince()))))
                .andExpect(jsonPath("$.[1].name", is(salta.getName())))
                .andExpect(jsonPath("$.[2].idProvince", is(Math.toIntExact(misiones.getIdProvince()))))
                .andExpect(jsonPath("$.[2].name", is(misiones.getName())));
    }

    @Test
    void getAllProvinceNames() throws Exception {
        when(provinceService.getAllProvinceNames()).thenReturn(List.of(buenosAires.getName(), salta.getName()));
        ResultActions response = mockMvc.perform(get("/api/provinces/names"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$.[0]", is(buenosAires.getName())))
                .andExpect(jsonPath("$.[1]", is(salta.getName())));
    }

    @Test
    void getPhoto() throws Exception {
        //Simulated image
        byte[] imageContent = new byte[]{};
        Resource imageResource = new ByteArrayResource(imageContent);
        when(fileService.getPhoto(eq("bsas.jpg"), eq("/provinces"))).thenReturn(new ResponseEntity<>(imageResource, HttpStatus.OK));
        ResultActions response = mockMvc.perform(get("/api/provinces/img/bsas.jpg"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageContent));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCount() throws Exception {
        when(provinceService.getCount()).thenReturn(2);
        ResultActions response = mockMvc.perform(get("/api/provinces/admin/count"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buenosAires);
        when(provinceService.save(buenosAires)).thenReturn(buenosAires);
        ResultActions response = mockMvc.perform(put("/api/provinces/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("provinceController.updated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buenosAires);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(put("/api/provinces/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buenosAires);
        when(provinceService.save(buenosAires)).thenReturn(buenosAires);
        ResultActions response = mockMvc.perform(post("/api/provinces/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("provinceController.created", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(buenosAires);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(post("/api/provinces/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void uploadPhoto() throws Exception {
        byte[] imageContent = new byte[]{};
        MultipartFile multipartFile = new MockMultipartFile("image", "new_image.jpeg", MediaType.IMAGE_JPEG_VALUE, imageContent);
        Long idProvince = 1L;
        when(provinceService.findById(idProvince)).thenReturn(buenosAires);
        when(fileService.save(multipartFile, "/provinces")).thenReturn("new_image.jpg");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/provinces/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idProvince.toString())
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("image.upload", null, locale))));
    }
}