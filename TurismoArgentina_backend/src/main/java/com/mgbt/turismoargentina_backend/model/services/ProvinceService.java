package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.repositories.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    IProvinceRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Province> getAllNonDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Province> getAllDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNotNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Province> getAllNonDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNullAndNameContaining(name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Province> getAllDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNotNullAndNameContaining(name, pageable);
    }

    @Override
    @Transactional
    public Province save(Province entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Province entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Province findById(Long id) {
        Province province = repository.findById(id).orElse(null);
        if (province == null || province.getDeletionDate() != null) throw new EntityNotFoundException("Province not found");
        return province;
    }

    @Override
    @Transactional(readOnly = true)
    public Province findByName(String name) {
        Province province = repository.findByName(name);
        if (province == null || province.getDeletionDate() != null) throw new EntityNotFoundException("Province not found");
        return province;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Province> getThreeRandom() {
        return repository.findThreeRandom();
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllProvinceNames() { return repository.findAllProvinceNames(); }

    @Override
    @Transactional(readOnly = true)
    public int getCount() {
        return repository.findCount();
    }
}
