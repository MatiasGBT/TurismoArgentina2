package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Activity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {

    Page<Activity> findByDeletionDateIsNull(Pageable pageable);

    Page<Activity> findByDeletionDateIsNotNull(Pageable pageable);

    Page<Activity> findByDeletionDateIsNullAndNameContaining(String name, Pageable pageable);

    Page<Activity> findByDeletionDateIsNotNullAndNameContaining(String name, Pageable pageable);

    @Query(value = "SELECT * FROM turismo_argentina.activities a " +
            "WHERE a.deletion_date IS NULL " +
            "ORDER BY RANDOM() LIMIT 5",
            nativeQuery = true)
    List<Activity> findFiveRandom();

    Page<Activity> findByLocationNameAndDeletionDateIsNull(Pageable pageable, String locationName);

    List<Activity> findByLocationIdLocationAndDeletionDateIsNull(Long idLocation);

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.activities a " +
            "WHERE a.deletion_date IS NULL", nativeQuery = true)
    int findCount();
}
