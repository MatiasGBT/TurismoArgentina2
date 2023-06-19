package com.mgbt.turismoargentina_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.PurchaseIncompleteException;
import com.mgbt.turismoargentina_backend.exceptions.ResultHasErrorsException;
import com.mgbt.turismoargentina_backend.model.entities.Purchase;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.services.impl.PurchaseService;
import com.mgbt.turismoargentina_backend.model.services.impl.ValidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PurchaseControllerTest {

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private ValidateService validateService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private User user;
    private Purchase purchase1;
    private Purchase purchase2;
    private Purchase purchase3;
    private Purchase purchase4;
    private Locale locale;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .idUser(1L)
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        purchase1 = Purchase.builder()
                .idPurchase(1L)
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(20000d)
                .isRefunded(false)
                .build();
        purchase2 = Purchase.builder()
                .idPurchase(2L)
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(15000d)
                .isRefunded(false)
                .build();
        purchase3 = Purchase.builder()
                .idPurchase(3L)
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(10000d)
                .isRefunded(false)
                .build();
        purchase4 = Purchase.builder()
                .idPurchase(4L)
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(5000d)
                .isRefunded(true)
                .build();
        locale = new Locale("en_EN");
        pageable = PageRequest.of(0, 9);
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void getAll() throws Exception {
        Page<Purchase> page = new PageImpl<>(List.of(purchase1, purchase2, purchase3, purchase4));
        when(purchaseService.getByUser(user.getIdUser(), pageable)).thenReturn(page);
        ResultActions response = mockMvc.perform(get("/api/purchases?idUser=1&page=0"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()", is(page.getContent().size())))
                .andExpect(jsonPath("$.content[0].idPurchase", is(Math.toIntExact(purchase1.getIdPurchase()))))
                .andExpect(jsonPath("$.content[1].idPurchase", is(Math.toIntExact(purchase2.getIdPurchase()))))
                .andExpect(jsonPath("$.content[2].idPurchase", is(Math.toIntExact(purchase3.getIdPurchase()))))
                .andExpect(jsonPath("$.content[3].idPurchase", is(Math.toIntExact(purchase4.getIdPurchase()))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void getById() throws Exception {
        when(purchaseService.findById(1L)).thenReturn(purchase1);
        ResultActions response = mockMvc.perform(get("/api/purchases/1"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPurchase", is(Math.toIntExact(purchase1.getIdPurchase()))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void getById_EntityNotFound() throws Exception {
        when(purchaseService.findById(5L)).thenThrow(EntityNotFoundException.class);
        ResultActions response = mockMvc.perform(get("/api/purchases/5"));
        response.andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.entityNotFound.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.entityNotFound.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void create() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        when(purchaseService.save(purchase1)).thenReturn(purchase1);
        ResultActions response = mockMvc.perform(post("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("purchaseController.created", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void create_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(post("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void create_Incomplete() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        doThrow(PurchaseIncompleteException.class).when(purchaseService).validateIfLocationsAndActivitiesAreEmpty(purchase1);
        ResultActions response = mockMvc.perform(post("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.purchaseIncomplete.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.purchaseIncomplete.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void update() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        when(purchaseService.save(purchase1)).thenReturn(purchase1);
        ResultActions response = mockMvc.perform(put("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("purchaseController.updated", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void update_ResultHasErrors() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        doThrow(ResultHasErrorsException.class).when(validateService).validateResult(any(BindingResult.class));
        ResultActions response = mockMvc.perform(put("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.validationError.message", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void update_Incomplete() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(purchase1);
        doThrow(PurchaseIncompleteException.class).when(purchaseService).validateIfLocationsAndActivitiesAreEmpty(purchase1);
        ResultActions response = mockMvc.perform(put("/api/purchases/")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("error.purchaseIncomplete.message", null, locale))))
                .andExpect(jsonPath("$.error", is(messageSource.getMessage("error.purchaseIncomplete.error", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCountNotRefunded() throws Exception {
        when(purchaseService.getCountIsNotRefunded()).thenReturn(3L);
        ResultActions response = mockMvc.perform(get("/api/purchases/admin/count?refunded=false"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(3)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCountRefunded() throws Exception {
        when(purchaseService.getCountIsRefunded()).thenReturn(1L);
        ResultActions response = mockMvc.perform(get("/api/purchases/admin/count?refunded=true"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getMoneyNotRefunded() throws Exception {
        Double moneyNotRefunded = purchase1.getPrice() + purchase2.getPrice() + purchase3.getPrice();
        when(purchaseService.getMoneyNotRefunded()).thenReturn(moneyNotRefunded);
        ResultActions response = mockMvc.perform(get("/api/purchases/admin/money?refunded=false"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(moneyNotRefunded)));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getMoneyRefunded() throws Exception {
        Double moneyRefunded = purchase4.getPrice();
        when(purchaseService.getMoneyRefunded()).thenReturn(moneyRefunded);
        ResultActions response = mockMvc.perform(get("/api/purchases/admin/money?refunded=true"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(moneyRefunded)));
    }
}