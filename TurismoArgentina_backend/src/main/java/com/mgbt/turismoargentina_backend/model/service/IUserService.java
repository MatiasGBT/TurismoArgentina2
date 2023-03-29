package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.User;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IUserService {
    List<User> getAll();
    User save(User entity);
    void delete(User entity);
    User findById(Long id);
    User findByUsername(String username);
    User checkIfUserIsPersisted(User userFound, User userFromToken);
}
