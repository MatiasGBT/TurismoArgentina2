package com.mgbt.turismoargentina_backend.model.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProvinceService<Province> {
    List<Province> getAll();
    Province save(Province entity);
    void delete(Province entity);
    Province findById(Long id);
    List<Province> getThreeRandom();
}
