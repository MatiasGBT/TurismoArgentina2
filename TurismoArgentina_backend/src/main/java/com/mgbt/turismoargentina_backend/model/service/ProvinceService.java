package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Province;
import com.mgbt.turismoargentina_backend.model.repository.IProvinceRepository;
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
    public Page<Province> getAll(Pageable pageable) {
        return repository.findAll(pageable);
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
        if (province == null) throw new EntityNotFoundException("Province not found");
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
}
