package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.User;
import com.mgbt.turismoargentina_backend.model.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<User> getAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public User save(User entity) {
        return this.repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(User entity) {
        this.repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(Long id) {
        User user = this.repository.findById(id).orElse(null);
        if (user == null || user.getDeletionDate() != null) throw new EntityNotFoundException("User not found");
        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        User user = this.repository.findByUsername(username);
        if (user == null || user.getDeletionDate() != null) throw new EntityNotFoundException("User not found");
        return user;
    }

    @Override
    @Transactional
    public User checkIfUserIsPersisted(User userFound, User userFromToken) {
        if (!userFound.getName().equals(userFromToken.getName()) ||
                !userFound.getLastName().equals(userFromToken.getLastName())) {
            userFound.setName(userFromToken.getName());
            userFound.setLastName(userFromToken.getLastName());
            this.save(userFound);
        }
        return userFound;
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return repository.findCount();
    }
}
