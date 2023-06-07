package com.mgbt.turismoargentina_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.services.impl.FileService;
import com.mgbt.turismoargentina_backend.model.services.impl.LocationService;
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

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LocationControllerTest {

    @MockBean
    private LocationService locationService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ValidateService validateService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private Location caba;
    private Location marDelPlata;
    private Location carilo;
    private Location tigre;
    private Locale locale;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        caba = Location.builder()
                .idLocation(1L)
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        marDelPlata = Location.builder()
                .idLocation(2L)
                .name("Mar del Plata")
                .description("desc")
                .image("mdp.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        carilo = Location.builder()
                .idLocation(3L)
                .name("Cariló")
                .description("desc")
                .image("carilo.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(new Date())
                .build();
        tigre = Location.builder()
                .idLocation(4L)
                .name("Tigre")
                .description("desc")
                .image("tigre.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(new Date())
                .build();
        locale = new Locale("en_EN");
        pageable = PageRequest.of(0, 9);
    }

    @Test
    void getAllNonDeleted() throws Exception {
        Page<Location> page = new PageImpl<>(List.of(caba, marDelPlata));
        when(locationService.getAllNonDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/locations/list/0/false"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.content[0].name", is(caba.getName())))
                .andExpect(jsonPath("$.content[1].idLocation", is(Math.toIntExact(marDelPlata.getIdLocation()))))
                .andExpect(jsonPath("$.content[1].name", is(marDelPlata.getName())));
    }

    @Test
    void getAllDeleted() throws Exception {
        Page<Location> page = new PageImpl<>(List.of(carilo, tigre));
        when(locationService.getAllDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/locations/list/0/true"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idLocation", is(Math.toIntExact(carilo.getIdLocation()))))
                .andExpect(jsonPath("$.content[0].name", is(carilo.getName())))
                .andExpect(jsonPath("$.content[1].idLocation", is(Math.toIntExact(tigre.getIdLocation()))))
                .andExpect(jsonPath("$.content[1].name", is(tigre.getName())));
    }

    @Test
    void getAllNonDeletedByName() throws Exception {
        Page<Location> page = new PageImpl<>(List.of(caba));
        when(locationService.getAllNonDeletedByName(pageable, "Buenos")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/locations/list/0/false/Buenos"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.content[0].name", is(caba.getName())));
    }

    @Test
    void getAllDeletedByName() throws Exception {
        Page<Location> page = new PageImpl<>(List.of(carilo));
        when(locationService.getAllDeletedByName(pageable, "ariló")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/locations/list/0/true/ariló"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idLocation", is(Math.toIntExact(carilo.getIdLocation()))))
                .andExpect(jsonPath("$.content[0].name", is(carilo.getName())));
    }

    @Test
    void getById() throws Exception {
        when(locationService.findById(1L)).thenReturn(caba);
        ResultActions response = mockMvc.perform(get("/api/locations/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.name", is(caba.getName())))
                .andExpect(jsonPath("$.description", is(caba.getDescription())))
                .andExpect(jsonPath("$.image", is(caba.getImage())));
    }

    @Test
    void getById_EntityNotFound() throws Exception {
        when(locationService.findById(3L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/locations/3"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void getByName() throws Exception {
        when(locationService.findByName("Ciudad Autónoma de Buenos Aires")).thenReturn(tigre);
        ResultActions response = mockMvc.perform(get("/api/locations?name=Ciudad Autónoma de Buenos Aires"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idLocation", is(Math.toIntExact(tigre.getIdLocation()))))
                .andExpect(jsonPath("$.name", is(tigre.getName())))
                .andExpect(jsonPath("$.description", is(tigre.getDescription())))
                .andExpect(jsonPath("$.image", is(tigre.getImage())));
    }

    @Test
    void getByName_EntityNotFound() throws Exception {
        when(locationService.findByName("Tigre")).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/locations?name=Tigre"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void getFourRandom() throws Exception {
        tigre.setDeletionDate(null);
        carilo.setDeletionDate(null);
        when(locationService.getFourRandom()).thenReturn(List.of(caba, marDelPlata, tigre, carilo));
        ResultActions response = mockMvc.perform(get("/api/locations/random"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(4)))
                .andExpect(jsonPath("$.[0].idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.[0].name", is(caba.getName())))
                .andExpect(jsonPath("$.[1].idLocation", is(Math.toIntExact(marDelPlata.getIdLocation()))))
                .andExpect(jsonPath("$.[1].name", is(marDelPlata.getName())))
                .andExpect(jsonPath("$.[2].idLocation", is(Math.toIntExact(tigre.getIdLocation()))))
                .andExpect(jsonPath("$.[2].name", is(tigre.getName())))
                .andExpect(jsonPath("$.[3].idLocation", is(Math.toIntExact(carilo.getIdLocation()))))
                .andExpect(jsonPath("$.[3].name", is(carilo.getName())));
    }

    @Test
    void getAllLocationNames() throws Exception {
        when(locationService.getAllLocationNames()).thenReturn(List.of(caba.getName(), marDelPlata.getName()));
        ResultActions response = mockMvc.perform(get("/api/locations/names"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$.[0]", is(caba.getName())))
                .andExpect(jsonPath("$.[1]", is(marDelPlata.getName())));
    }

    @Test
    void getByProvinceName() throws Exception {
        Page<Location> page = new PageImpl<>(List.of(caba, marDelPlata));
        when(locationService.getByProvinceName(pageable, "Buenos Aires")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/locations/province/Buenos Aires/0"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.content[0].name", is(caba.getName())))
                .andExpect(jsonPath("$.content[1].idLocation", is(Math.toIntExact(marDelPlata.getIdLocation()))))
                .andExpect(jsonPath("$.content[1].name", is(marDelPlata.getName())));
    }

    @Test
    void getByProvinceId() throws Exception {
        when(locationService.getByProvinceId(1L)).thenReturn(List.of(caba, marDelPlata));
        ResultActions response = mockMvc.perform(get("/api/locations/province/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$.[0].idLocation", is(Math.toIntExact(caba.getIdLocation()))))
                .andExpect(jsonPath("$.[0].name", is(caba.getName())))
                .andExpect(jsonPath("$.[1].idLocation", is(Math.toIntExact(marDelPlata.getIdLocation()))))
                .andExpect(jsonPath("$.[1].name", is(marDelPlata.getName())));
    }

    @Test
    void getPhoto() throws Exception {
        //Simulated image
        byte[] imageContent = new byte[]{};
        Resource imageResource = new ByteArrayResource(imageContent);
        when(fileService.getPhoto(eq("caba.jpg"), eq("/locations"))).thenReturn(imageResource);
        ResultActions response = mockMvc.perform(get("/api/locations/img/caba.jpg"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageContent));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCount() throws Exception {
        when(locationService.getCount()).thenReturn(2);
        ResultActions response = mockMvc.perform(get("/api/locations/admin/count"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(caba);
        when(locationService.save(caba)).thenReturn(caba);
        ResultActions response = mockMvc.perform(put("/api/locations/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("locationController.updated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(caba);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(put("/api/locations/admin")
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
        String requestBody = objectMapper.writeValueAsString(caba);
        when(locationService.save(caba)).thenReturn(caba);
        ResultActions response = mockMvc.perform(post("/api/locations/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("locationController.created", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(caba);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(post("/api/locations/admin")
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
        Long idLocation = 1L;
        when(locationService.findById(idLocation)).thenReturn(caba);
        when(fileService.save(multipartFile, "/locations")).thenReturn("new_image.jpg");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/locations/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idLocation.toString())
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("image.upload", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void uploadPhoto_FileNameTooLong() throws Exception {
        byte[] imageContent = new byte[]{};
        MultipartFile multipartFile = new MockMultipartFile("image", "phraseOverFortyCharactersWhichCausesAnError.jpeg", MediaType.IMAGE_JPEG_VALUE, imageContent);
        Long idLocation = 1L;
        when(fileService.save(any(MultipartFile.class), eq("/locations"))).thenThrow(FileNameTooLongException.class);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/locations/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idLocation.toString())
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.fileNameTooLong.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.fileNameTooLong.error", null, locale))));
    }
}