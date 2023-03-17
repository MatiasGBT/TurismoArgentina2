package com.mgbt.turismoargentina_backend.model.repository;

import com.mgbt.turismoargentina_backend.model.entity.Province;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProvinceRepository extends JpaRepository<Province, Long> {

    @Query(value = "SELECT * FROM turismo_argentina.provinces p " +
            "WHERE p.deletion_date IS NULL " +
            "ORDER BY RANDOM() LIMIT 3",
            nativeQuery = true)
    List<Province> findThreeRandom();

    @Query(value = "SELECT p.name FROM turismo_argentina.provinces p " +
            "WHERE p.deletion_date IS NULL", nativeQuery = true)
    List<String> findAllProvinceNames();
}
