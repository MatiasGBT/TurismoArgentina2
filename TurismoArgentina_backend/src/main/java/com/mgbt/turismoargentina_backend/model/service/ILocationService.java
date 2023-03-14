package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Location;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ILocationService {
    List<Location> getAll();
    Location save(Location entity);
    void delete(Location entity);
    Location findById(Long id);
    List<Location> getFourRandom();
}
