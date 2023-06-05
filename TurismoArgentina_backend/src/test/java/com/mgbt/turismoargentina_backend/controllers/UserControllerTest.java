package com.mgbt.turismoargentina_backend.controllers;

import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .idUser(1L)
                .username("albert")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        user2 = User.builder()
                .idUser(2L)
                .username("chris")
                .name("Chris")
                .lastName("Redfield")
                .creationDate(new Date())
                .build();
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = "admin")
    void getCount() throws Exception {
        when(userService.getCount()).thenReturn(2L);
        ResultActions response = mockMvc.perform(get("/api/users/admin/count"));
        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(2)));
    }
}