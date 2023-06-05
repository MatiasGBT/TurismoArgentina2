package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.repositories.IUserRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
    }

    @Test
    void getAll() {
        User user2 = User.builder()
                .username("chris")
                .name("Chris")
                .lastName("Redfield")
                .creationDate(new Date())
                .build();
        when(userRepository.findAll()).thenReturn(List.of(user, user2));
        List<User> users = userService.getAll();
        assertNotNull(users);
        assertEquals(users.size(), 2);
        assertTrue(users.contains(user));
        assertTrue(users.contains(user2));
    }

    @Test
    void save() {
        when(userRepository.save(user)).thenReturn(user);
        User userSaved = userService.save(user);
        verify(userRepository, times(1)).save(user);
        assertNotNull(userSaved);
    }

    @Test
    void delete() {
        user.setIdUser(1L);
        willDoNothing().given(userRepository).delete(user);
        userService.delete(user);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void findById() {
        user.setIdUser(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(userRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        User userFound = userService.findById(1L);
        assertNotNull(userFound);
        assertEquals(userFound, user);
        assertThrows(EntityNotFoundException.class, () ->  userService.findById(2L));
    }

    @Test
    void findByUsername() {
        when(userRepository.findByUsername("user")).thenReturn(user);
        when(userRepository.findByUsername("user2")).thenThrow(EntityNotFoundException.class);
        User userFound = userService.findByUsername("user");
        assertNotNull(userFound);
        assertEquals(userFound, user);
        assertThrows(EntityNotFoundException.class, () ->  userService.findByUsername("user2"));
    }

    @Test
    void checkIfUserIsPersistedTrueCase() {
        User user2 = User.builder()
                .username(user.getUsername())
                .name(user.getName())
                .lastName(user.getLastName())
                .creationDate(user.getCreationDate())
                .build();
        userService.validateIfUserIsPersisted(user, user2);
        assertEquals(user.getName(), "Albert");
        assertEquals(user.getLastName(), "Wesker");
        verify(userRepository, times(0)).save(user);
    }

    @Test
    void checkIfUserIsPersistedFalseCase() {
        User user2 = User.builder()
                .username(user.getUsername())
                .name("Chris")
                .lastName("Redfield")
                .creationDate(user.getCreationDate())
                .build();
        when(userRepository.save(user)).thenReturn(user);
        userService.validateIfUserIsPersisted(user, user2);
        assertEquals(user.getName(), user2.getName());
        assertEquals(user.getLastName(), user2.getLastName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getCount() {
        when(userRepository.findCount()).thenReturn(1L);
        Long userCount = userService.getCount();
        assertEquals(userCount, 1L);
    }
}