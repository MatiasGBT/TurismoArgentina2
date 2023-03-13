package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Province;
import com.mgbt.turismoargentina_backend.model.repository.IProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ProvinceService implements IProvinceService<Province> {

    @Autowired
    IProvinceRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Province> getAll() {
        return repository.findAll();
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
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Province> getThreeRandom() {
        return repository.findThreeRandom();
    }
}
