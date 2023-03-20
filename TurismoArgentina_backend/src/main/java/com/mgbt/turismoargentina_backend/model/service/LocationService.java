package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Location;
import com.mgbt.turismoargentina_backend.model.repository.ILocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class LocationService implements ILocationService {

    @Autowired
    ILocationRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Location> getAll(Pageable pageable) {
        return repository.findAll(pageable);
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
        Location location = repository.findById(id).orElse(null);
        if (location == null) throw new EntityNotFoundException("Location not found");
        return location;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getFourRandom() {
        return repository.findFourRandom();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllLocationNames() { return repository.findAllLocationNames(); }

    @Override
    @Transactional(readOnly = true)
    public Page<Location> getByProvinceName(Pageable pageable, String provinceName) {
        return repository.findByProvinceName(pageable, provinceName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getByProvinceId(Long idProvince) {
        return repository.findByProvinceIdProvince(idProvince);
    }
}
