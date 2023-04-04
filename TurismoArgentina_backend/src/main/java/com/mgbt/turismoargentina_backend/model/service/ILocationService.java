package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Location;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ILocationService {
    Page<Location> getAllNonDeleted(Pageable pageable);
    Page<Location> getAllDeleted(Pageable pageable);
    Location save(Location entity);
    void delete(Location entity);
    Location findById(Long id);
    List<Location> getFourRandom();
    List<String> getAllLocationNames();
    Page<Location> getByProvinceName(Pageable pageable, String provinceName);
    List<Location> getByProvinceId(Long idProvince);
    Long getCount();
}
