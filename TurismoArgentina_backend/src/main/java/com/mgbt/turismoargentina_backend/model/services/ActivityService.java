package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.ActivityImageNumberException;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Activity;
import com.mgbt.turismoargentina_backend.model.repositories.IActivityRepository;
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
    public Page<Activity> getAllNonDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> getAllDeleted(Pageable pageable) {
        return repository.findByDeletionDateIsNotNull(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> getAllNonDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNullAndNameContaining(name, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Activity> getAllDeletedByName(Pageable pageable, String name) {
        return repository.findByDeletionDateIsNotNullAndNameContaining(name, pageable);
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

    @Override
    public String getPreviousImage(Activity activity, Integer imageNumber, String fileName) {
        String previousImage;
        if (imageNumber == 1) {
            previousImage = activity.getImage1();
            activity.setImage1(fileName);
        } else if (imageNumber == 2) {
            previousImage = activity.getImage2();
            activity.setImage2(fileName);
        } else if (imageNumber == 3) {
            previousImage = activity.getImage3();
            activity.setImage3(fileName);
        } else {
            throw new ActivityImageNumberException("The activity can only have three images, so the image number must be from 1 to 3");
        }
        return previousImage;
    }
}
