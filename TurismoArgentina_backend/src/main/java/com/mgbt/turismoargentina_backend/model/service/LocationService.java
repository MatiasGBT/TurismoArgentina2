package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Location;
import com.mgbt.turismoargentina_backend.model.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    ILocationRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Location> getAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Location save(Location entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Location entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Location findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getFourRandom() {
        return repository.findFourRandom();
    }
}
