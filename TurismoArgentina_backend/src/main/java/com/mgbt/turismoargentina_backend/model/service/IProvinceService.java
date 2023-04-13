package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Province;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IProvinceService {
    Page<Province> getAllNonDeleted(Pageable pageable);
    Page<Province> getAllDeleted(Pageable pageable);
    Page<Province> getAllNonDeletedByName(Pageable pageable, String name);
    Page<Province> getAllDeletedByName(Pageable pageable, String name);
    Province save(Province entity);
    void delete(Province entity);
    Province findById(Long id);
    Province findByName(String name);
    List<Province> getThreeRandom();
    List<String> getAllProvinceNames();
    Long getCount();
}
