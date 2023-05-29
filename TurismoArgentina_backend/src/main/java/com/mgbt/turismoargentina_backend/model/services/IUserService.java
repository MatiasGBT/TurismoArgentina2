package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.model.entities.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IUserService {
    List<User> getAll();
    User save(User entity);
    void delete(User entity);
    User findById(Long id);
    User findByUsername(String username);
    void checkIfUserIsPersisted(User userFound, User userFromToken);
    Long getCount();
}
