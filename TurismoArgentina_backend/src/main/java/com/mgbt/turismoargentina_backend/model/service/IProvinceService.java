package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Province;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProvinceService {
    Page<Province> getAll(Pageable pageable);
    Province save(Province entity);
    void delete(Province entity);
    Province findById(Long id);
    List<Province> getThreeRandom();
    List<String> getAllProvinceNames();
}
