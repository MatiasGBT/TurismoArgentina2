package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.model.entities.Activity;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IActivityService {
    Page<Activity> getAllNonDeleted(Pageable pageable);
    Page<Activity> getAllDeleted(Pageable pageable);
    Page<Activity> getAllNonDeletedByName(Pageable pageable, String name);
    Page<Activity> getAllDeletedByName(Pageable pageable, String name);
    Activity save(Activity entity);
    void delete(Activity entity);
    Activity findById(Long id);
    List<Activity> getFiveRandom();
    Page<Activity> getByLocationName(Pageable pageable, String locationName);
    List<Activity> getByLocationId(Long idLocation);
    int getCount();
    void updateImage(Activity activity, Integer imageNumber, String fileName, String finalDirectory);
}
