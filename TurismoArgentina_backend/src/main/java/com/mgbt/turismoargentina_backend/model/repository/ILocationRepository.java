package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.Location;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ILocationRepository extends JpaRepository<Location, Long> {

    @Query(value = "SELECT * FROM turismo_argentina.locations l " +
            "WHERE l.deletion_date IS NULL " +
            "ORDER BY RANDOM() LIMIT 4",
            nativeQuery = true)
    List<Location> findFourRandom();

    @Query(value = "SELECT l.name FROM turismo_argentina.locations l " +
            "WHERE l.deletion_date IS NULL", nativeQuery = true)
    List<String> findAllLocationNames();

    Page<Location> findByProvinceName(Pageable pageable, String provinceName);

    List<Location> findByProvinceIdProvince(Long idProvince);

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.locations l " +
            "WHERE l.deletion_date IS NULL", nativeQuery = true)
    Long findCount();
}
