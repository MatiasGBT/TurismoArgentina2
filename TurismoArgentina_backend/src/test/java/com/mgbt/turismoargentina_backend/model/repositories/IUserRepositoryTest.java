package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IUserRepositoryTest {

    @Autowired
    private IUserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        this.userRepository.save(user);
    }

    @Test
    void findByUsername() {
        User userFound = this.userRepository.findByUsername(user.getUsername());
        assertNotNull(userFound);
        assertEquals(userFound, user);
    }

    @Test
    void findCount() {
        Long userCount = this.userRepository.findCount();
        assertEquals(userCount, 1);
    }
}