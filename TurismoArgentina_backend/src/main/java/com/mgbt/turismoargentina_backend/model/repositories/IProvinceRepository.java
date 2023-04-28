package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Province;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Long> {

    Province findByName(String name);

    Page<Province> findByDeletionDateIsNull(Pageable pageable);

    Page<Province> findByDeletionDateIsNotNull(Pageable pageable);

    Page<Province> findByDeletionDateIsNullAndNameContaining(String name, Pageable pageable);

    Page<Province> findByDeletionDateIsNotNullAndNameContaining(String name, Pageable pageable);

    @Query(value = "SELECT * FROM turismo_argentina.provinces p " +
            "WHERE p.deletion_date IS NULL " +
            "ORDER BY RANDOM() LIMIT 3",
            nativeQuery = true)
    List<Province> findThreeRandom();

    @Query(value = "SELECT p.name FROM turismo_argentina.provinces p " +
            "WHERE p.deletion_date IS NULL", nativeQuery = true)
    List<String> findAllProvinceNames();

    @Query(value = "SELECT COUNT(*) FROM turismo_argentina.provinces p " +
            "WHERE p.deletion_date IS NULL", nativeQuery = true)
    Long findCount();
}
