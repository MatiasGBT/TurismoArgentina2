package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.Activity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = "SELECT * FROM turismo_argentina.activities a WHERE a.deletion_date IS NULL ORDER BY a.name",
            countQuery = "SELECT count(*) FROM turismo_argentina.activities a WHERE a.deletion_date IS NULL",
            nativeQuery = true)
    Page<Activity> findAllNonDeleted(Pageable pageable);

    @Query(value = "SELECT * FROM turismo_argentina.activities a WHERE a.deletion_date IS NOT NULL ORDER BY a.name",
            countQuery = "SELECT count(*) FROM turismo_argentina.activities a WHERE a.deletion_date IS NOT NULL",
            nativeQuery = true)
    Page<Activity> findAllDeleted(Pageable pageable);

    @Query(value = "SELECT * FROM turismo_argentina.activities a " +
            "WHERE a.deletion_date IS NULL " +
            "ORDER BY RANDOM() LIMIT 5",
            nativeQuery = true)
    List<Activity> findFiveRandom();

    Page<Activity> findByLocationName(Pageable pageable, String locationName);

    List<Activity> findByLocationIdLocation(Long idLocation);

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.activities a " +
            "WHERE a.deletion_date IS NULL", nativeQuery = true)
    Long findCount();
}
