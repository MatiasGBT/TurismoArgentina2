package com.mgbt.turismoargentina_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.ActivityImageNumberException;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.FileNameTooLongException;
import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.entities.Activity;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.services.impl.ActivityService;
import com.mgbt.turismoargentina_backend.model.services.impl.FileService;
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
import org.springframework.http.MediaType;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ActivityControllerTest {

    @MockBean
    private ActivityService activityService;

    @MockBean
    private FileService fileService;

    @MockBean
    private ValidateService validateService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private Activity paseoBarcoTrenCamion;
    private Activity barcoMotor;
    private Activity vueloPanoramico;
    private Activity tirolinaRappel;
    private Activity recorridoCataratas;
    private Locale locale;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        paseoBarcoTrenCamion = Activity.builder()
                .idActivity(1L)
                .name("Paseo en barco, tren y camión de safari")
                .description("desc")
                .image1("paseobarcotrencamion1.jpg")
                .image2("paseobarcotrencamion2.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        barcoMotor = Activity.builder()
                .idActivity(2L)
                .name("Barco a motor bajo las cataratas")
                .description("desc")
                .image1("barcomotor.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        vueloPanoramico = Activity.builder()
                .idActivity(3L)
                .name("Vuelo panorámico en helicóptero por las Cataratas del Iguazú")
                .description("desc")
                .image1("vuelopanoramico.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        tirolinaRappel = Activity.builder()
                .idActivity(4L)
                .name("Aventura Privada de Tirolina y Rappel")
                .description("desc")
                .image1("tirolinarappel.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(new Date())
                .build();
        recorridoCataratas = Activity.builder()
                .idActivity(5L)
                .name("Recorrido por las Cataratas del Iguazú")
                .description("desc")
                .image1("recorridocataratas.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(new Date())
                .build();
        locale = new Locale("en_EN");
        pageable = PageRequest.of(0, 9);
    }

    @Test
    void getAllNonDeleted() throws Exception {
        Page<Activity> page = new PageImpl<>(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        when(activityService.getAllNonDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/activities?page=0&deleted=false"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idActivity", is(Math.toIntExact(paseoBarcoTrenCamion.getIdActivity()))))
                .andExpect(jsonPath("$.content[0].name", is(paseoBarcoTrenCamion.getName())))
                .andExpect(jsonPath("$.content[1].idActivity", is(Math.toIntExact(barcoMotor.getIdActivity()))))
                .andExpect(jsonPath("$.content[1].name", is(barcoMotor.getName())))
                .andExpect(jsonPath("$.content[2].idActivity", is(Math.toIntExact(vueloPanoramico.getIdActivity()))))
                .andExpect(jsonPath("$.content[2].name", is(vueloPanoramico.getName())));
    }

    @Test
    void getAllDeleted() throws Exception {
        Page<Activity> page = new PageImpl<>(List.of(tirolinaRappel, recorridoCataratas));
        when(activityService.getAllDeleted(pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/activities?page=0&deleted=true"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idActivity", is(Math.toIntExact(tirolinaRappel.getIdActivity()))))
                .andExpect(jsonPath("$.content[0].name", is(tirolinaRappel.getName())))
                .andExpect(jsonPath("$.content[1].idActivity", is(Math.toIntExact(recorridoCataratas.getIdActivity()))))
                .andExpect(jsonPath("$.content[1].name", is(recorridoCataratas.getName())));
    }

    @Test
    void getAllNonDeletedByName() throws Exception {
        Page<Activity> page = new PageImpl<>(List.of(barcoMotor, vueloPanoramico));
        when(activityService.getAllNonDeletedByName(pageable, "las")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/activities?page=0&deleted=false&name=las"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idActivity", is(Math.toIntExact(barcoMotor.getIdActivity()))))
                .andExpect(jsonPath("$.content[0].name", is(barcoMotor.getName())))
                .andExpect(jsonPath("$.content[1].idActivity", is(Math.toIntExact(vueloPanoramico.getIdActivity()))))
                .andExpect(jsonPath("$.content[1].name", is(vueloPanoramico.getName())));
    }

    @Test
    void getAllDeletedByName() throws Exception {
        Page<Activity> page = new PageImpl<>(List.of(tirolinaRappel));
        when(activityService.getAllDeletedByName(pageable, "Tirolina")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/activities?page=0&deleted=true&name=Tirolina"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idActivity", is(Math.toIntExact(tirolinaRappel.getIdActivity()))))
                .andExpect(jsonPath("$.content[0].name", is(tirolinaRappel.getName())));
    }

    @Test
    void getById() throws Exception {
        when(activityService.findById(1L)).thenReturn(paseoBarcoTrenCamion);
        ResultActions response = mockMvc.perform(get("/api/activities/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idActivity", is(Math.toIntExact(paseoBarcoTrenCamion.getIdActivity()))))
                .andExpect(jsonPath("$.name", is(paseoBarcoTrenCamion.getName())))
                .andExpect(jsonPath("$.description", is(paseoBarcoTrenCamion.getDescription())))
                .andExpect(jsonPath("$.image1", is(paseoBarcoTrenCamion.getImage1())));
    }

    @Test
    void getById_EntityNotFound() throws Exception {
        when(activityService.findById(4L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/activities/4"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    void getFiveRandom() throws Exception {
        tirolinaRappel.setDeletionDate(null);
        recorridoCataratas.setDeletionDate(null);
        when(activityService.getFiveRandom()).thenReturn(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico, tirolinaRappel, recorridoCataratas));
        ResultActions response = mockMvc.perform(get("/api/activities/random"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(5)))
                .andExpect(jsonPath("$.[0].idActivity", is(Math.toIntExact(paseoBarcoTrenCamion.getIdActivity()))))
                .andExpect(jsonPath("$.[0].name", is(paseoBarcoTrenCamion.getName())))
                .andExpect(jsonPath("$.[1].idActivity", is(Math.toIntExact(barcoMotor.getIdActivity()))))
                .andExpect(jsonPath("$.[1].name", is(barcoMotor.getName())))
                .andExpect(jsonPath("$.[2].idActivity", is(Math.toIntExact(vueloPanoramico.getIdActivity()))))
                .andExpect(jsonPath("$.[2].name", is(vueloPanoramico.getName())))
                .andExpect(jsonPath("$.[3].idActivity", is(Math.toIntExact(tirolinaRappel.getIdActivity()))))
                .andExpect(jsonPath("$.[3].name", is(tirolinaRappel.getName())))
                .andExpect(jsonPath("$.[4].idActivity", is(Math.toIntExact(recorridoCataratas.getIdActivity()))))
                .andExpect(jsonPath("$.[4].name", is(recorridoCataratas.getName())));
    }

    @Test
    void getByLocationName() throws Exception {
        Page<Activity> page = new PageImpl<>(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        when(activityService.getByLocationName(pageable, "Puerto Iguazú")).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/activities?locationName=Puerto Iguazú&page=0"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idActivity", is(Math.toIntExact(paseoBarcoTrenCamion.getIdActivity()))))
                .andExpect(jsonPath("$.content[0].name", is(paseoBarcoTrenCamion.getName())))
                .andExpect(jsonPath("$.content[1].idActivity", is(Math.toIntExact(barcoMotor.getIdActivity()))))
                .andExpect(jsonPath("$.content[1].name", is(barcoMotor.getName())))
                .andExpect(jsonPath("$.content[2].idActivity", is(Math.toIntExact(vueloPanoramico.getIdActivity()))))
                .andExpect(jsonPath("$.content[2].name", is(vueloPanoramico.getName())));
    }

    @Test
    void getByLocationId() throws Exception {
        when(activityService.getByLocationId(1L)).thenReturn(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        ResultActions response = mockMvc.perform(get("/api/activities?idLocation=1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(3)))
                .andExpect(jsonPath("$.[0].idActivity", is(Math.toIntExact(paseoBarcoTrenCamion.getIdActivity()))))
                .andExpect(jsonPath("$.[0].name", is(paseoBarcoTrenCamion.getName())))
                .andExpect(jsonPath("$.[1].idActivity", is(Math.toIntExact(barcoMotor.getIdActivity()))))
                .andExpect(jsonPath("$.[1].name", is(barcoMotor.getName())))
                .andExpect(jsonPath("$.[2].idActivity", is(Math.toIntExact(vueloPanoramico.getIdActivity()))))
                .andExpect(jsonPath("$.[2].name", is(vueloPanoramico.getName())));
    }

    @Test
    void getPhoto() throws Exception {
        //Simulated image
        byte[] imageContent = new byte[]{};
        Resource imageResource = new ByteArrayResource(imageContent);
        when(fileService.getPhoto(eq("paseobarcotrencamion1.jpg"), eq("/activities"))).thenReturn(imageResource);
        ResultActions response = mockMvc.perform(get("/api/activities/img/paseobarcotrencamion1.jpg"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().bytes(imageContent));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCount() throws Exception {
        when(activityService.getCount()).thenReturn(3);
        ResultActions response = mockMvc.perform(get("/api/activities/admin/count"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(3)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(paseoBarcoTrenCamion);
        when(activityService.save(paseoBarcoTrenCamion)).thenReturn(paseoBarcoTrenCamion);
        ResultActions response = mockMvc.perform(put("/api/activities/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("activityController.updated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void update_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(paseoBarcoTrenCamion);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(put("/api/activities/admin")
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
        String requestBody = objectMapper.writeValueAsString(paseoBarcoTrenCamion);
        when(activityService.save(paseoBarcoTrenCamion)).thenReturn(paseoBarcoTrenCamion);
        ResultActions response = mockMvc.perform(post("/api/activities/admin")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("activityController.created", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void create_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(paseoBarcoTrenCamion);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(post("/api/activities/admin")
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
        Long idActivity = 1L;
        Integer imageNumber = 1;
        when(fileService.save(multipartFile, "/activities")).thenReturn("new_image.jpg");
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/activities/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idActivity.toString())
                .param("imageNumber", imageNumber.toString())
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("image.upload", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void uploadPhoto_ActivityImageNumber() throws Exception {
        byte[] imageContent = new byte[]{};
        MultipartFile multipartFile = new MockMultipartFile("image", "new_image.jpeg", MediaType.IMAGE_JPEG_VALUE, imageContent);
        Long idActivity = 1L;
        Integer imageNumber = 4;
        when(activityService.findById(idActivity)).thenReturn(paseoBarcoTrenCamion);
        when(fileService.save(any(MultipartFile.class), eq("/activities"))).thenReturn("new_image.jpeg");
        doThrow(ActivityImageNumberException.class)
                .when(activityService)
                .updateImage(any(Activity.class), any(Integer.class), anyString(), eq("/activities"));
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/activities/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idActivity.toString())
                .param("imageNumber", imageNumber.toString())
                .with(csrf().asHeader()));
        verify(activityService).updateImage(any(Activity.class), eq(imageNumber), anyString(), eq("/activities"));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.imageNumber.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.imageNumber.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void uploadPhoto_FileNameTooLong() throws Exception {
        byte[] imageContent = new byte[]{};
        MultipartFile multipartFile = new MockMultipartFile("image", "phraseOverFortyCharactersWhichCausesAnError.jpeg", MediaType.IMAGE_JPEG_VALUE, imageContent);
        Long idActivity = 1L;
        Integer imageNumber = 1;
        when(fileService.save(any(MultipartFile.class), eq("/activities"))).thenThrow(FileNameTooLongException.class);
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/activities/admin/img")
                .file("image", multipartFile.getBytes())
                .param("id", idActivity.toString())
                .param("imageNumber", imageNumber.toString())
                .with(csrf().asHeader()));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.fileNameTooLong.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.fileNameTooLong.error", null, locale))));
    }
}