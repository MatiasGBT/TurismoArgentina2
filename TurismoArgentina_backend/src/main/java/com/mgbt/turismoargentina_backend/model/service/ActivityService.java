package com.mgbt.turismoargentina_backend.model.service;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entity.Activity;
import com.mgbt.turismoargentina_backend.model.repository.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ActivityService implements IActivityService {

    @Autowired
    IActivityRepository repository;

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Activity save(Activity entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Activity entity) {
        repository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Activity findById(Long id) {
        Activity activity = repository.findById(id).orElse(null);
        if (activity == null || activity.getDeletionDate() != null) throw new EntityNotFoundException("Activity not found");
        return activity;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> getFiveRandom() {
        return repository.findFiveRandom();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> getByLocationName(Pageable pageable, String locationName) {
        return repository.findByLocationName(pageable, locationName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Activity> getByLocationId(Long idLocation) {
        return repository.findByLocationIdLocation(idLocation);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount() {
        return repository.findCount();
    }
}
