package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.repositories.ILocationRepository;
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
    public Page<Location> getAllNonDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Location> getAllDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNotNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Location> getAllNonDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNullAndNameContaining(name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Location> getAllDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNotNullAndNameContaining(name, pageable);
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
        if (location == null || location.getDeletionDate() != null) throw new EntityNotFoundException("Location not found");
        return location;
    }

    @Override
    @Transactional(readOnly = true)
    public Location findByName(String name) {
        Location location = repository.findByName(name);
        if (location == null || location.getDeletionDate() != null) throw new EntityNotFoundException("Location not found");
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
        return repository.findByProvinceNameAndDeletionDateIsNull(pageable, provinceName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Location> getByProvinceId(Long idProvince) {
        return repository.findByProvinceIdProvinceAndDeletionDateIsNull(idProvince);
    }

    @Override
    @Transactional(readOnly = true)
    public int getCount() {
        return repository.findCount();
    }
}
