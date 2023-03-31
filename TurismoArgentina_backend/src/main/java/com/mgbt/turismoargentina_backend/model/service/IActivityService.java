package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.model.entity.Activity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IActivityService {
    Page<Activity> getAll(Pageable pageable);
    Activity save(Activity entity);
    void delete(Activity entity);
    Activity findById(Long id);
    List<Activity> getFiveRandom();
    Page<Activity> getByLocationName(Pageable pageable, String locationName);
    List<Activity> getByLocationId(Long idLocation);
    Long getCount();
}
