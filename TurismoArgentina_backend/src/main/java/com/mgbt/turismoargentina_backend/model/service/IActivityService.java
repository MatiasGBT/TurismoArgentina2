package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Activity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IActivityService {
    List<Activity> getAll();
    Activity save(Activity entity);
    void delete(Activity entity);
    Activity findById(Long id);
    List<Activity> getFiveRandom();
}
