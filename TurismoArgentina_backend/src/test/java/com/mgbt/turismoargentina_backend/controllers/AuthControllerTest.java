package com.mgbt.turismoargentina_backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    MessageSource messageSource;

    private User user1;
    private Locale locale;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .idUser(1L)
                .username("albert")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        locale = new Locale("en_EN");
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void login() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user1);
        when(userService.findByUsername("albert")).thenReturn(user1);
        ResultActions response = mockMvc.perform(post("/api/auth/login")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.user.idUser", is(Math.toIntExact(user1.getIdUser()))))
                .andExpect(jsonPath("$.user.name", is(user1.getName())))
                .andExpect(jsonPath("$.user.lastName", is(user1.getLastName())))
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("authController.userFound", null, locale))));
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "user")
    void login_EntityNotFound() throws Exception {
        User user2 = User.builder()
                .idUser(2L)
                .username("chris")
                .name("Chris")
                .lastName("Redfield")
                .creationDate(new Date())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(user2);
        when(userService.findByUsername("chris")).thenThrow(EntityNotFoundException.class);
        when(userService.save(user2)).thenReturn(user2);
        ResultActions response = mockMvc.perform(post("/api/auth/login")
                .with(csrf().asHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody));
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.user.idUser", is(Math.toIntExact(user2.getIdUser()))))
                .andExpect(jsonPath("$.user.name", is(user2.getName())))
                .andExpect(jsonPath("$.user.lastName", is(user2.getLastName())))
                .andExpect(jsonPath("$.message", is(messageSource.getMessage("authController.userCreated", null, locale))));
    }
}