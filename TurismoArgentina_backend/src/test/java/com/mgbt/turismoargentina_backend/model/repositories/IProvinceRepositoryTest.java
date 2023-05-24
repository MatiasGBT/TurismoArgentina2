package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Province;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IProvinceRepositoryTest {

    @Autowired
    private IProvinceRepository provinceRepository;

    private Province buenosAires;
    private Province salta;
    private Province misiones;
    private Province rioNegro;

    @BeforeEach
    void setUp() {
        buenosAires = Province.builder()
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        salta = Province.builder()
                .name("Salta")
                .description("desc")
                .image("salta.jpg")
                .deletionDate(null)
                .build();
        misiones = Province.builder()
                .name("Misiones")
                .description("desc")
                .image("misiones.jpg")
                .deletionDate(new Date())
                .build();
        rioNegro = Province.builder()
                .name("Río Negro")
                .description("desc")
                .image("rionegro.jpg")
                .deletionDate(new Date())
                .build();
        this.provinceRepository.save(buenosAires);
        this.provinceRepository.save(salta);
        this.provinceRepository.save(misiones);
        this.provinceRepository.save(rioNegro);
    }

    @Test
    void findByName() {
        Province province = provinceRepository.findByName("Buenos Aires");
        assertThat(province).isNotNull();
        assertEquals(province.getName(), "Buenos Aires");
    }

    @Test
    void findByDeletionDateIsNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> provinces = provinceRepository.findByDeletionDateIsNull(pageable);
        assertEquals(provinces.stream().toList().size(), 2);
        assertTrue(provinces.stream().toList().contains(buenosAires));
        assertTrue(provinces.stream().toList().contains(salta));
        assertFalse(provinces.stream().toList().contains(misiones));
        assertFalse(provinces.stream().toList().contains(rioNegro));
    }

    @Test
    void findByDeletionDateIsNotNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> provinces = provinceRepository.findByDeletionDateIsNotNull(pageable);
        assertEquals(provinces.stream().toList().size(), 2);
        assertFalse(provinces.stream().toList().contains(buenosAires));
        assertFalse(provinces.stream().toList().contains(salta));
        assertTrue(provinces.stream().toList().contains(misiones));
        assertTrue(provinces.stream().toList().contains(rioNegro));
    }

    @Test
    void findByDeletionDateIsNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> provinces = provinceRepository.findByDeletionDateIsNullAndNameContaining("e", pageable);
        assertEquals(provinces.stream().toList().size(), 1);
        assertTrue(provinces.stream().toList().contains(buenosAires));
        assertFalse(provinces.stream().toList().contains(salta));
        assertFalse(provinces.stream().toList().contains(misiones));
        assertFalse(provinces.stream().toList().contains(rioNegro));
    }

    @Test
    void findByDeletionDateIsNotNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> provinces = provinceRepository.findByDeletionDateIsNotNullAndNameContaining("e", pageable);
        assertEquals(provinces.stream().toList().size(), 2);
        assertFalse(provinces.stream().toList().contains(buenosAires));
        assertFalse(provinces.stream().toList().contains(salta));
        assertTrue(provinces.stream().toList().contains(misiones));
        assertTrue(provinces.stream().toList().contains(rioNegro));
    }

    @Test
    void findThreeRandom() {
        Province mendoza = Province.builder()
                .name("Mendoza")
                .description("desc")
                .image("mendoza.jpg")
                .deletionDate(null)
                .build();
        Province neuquen = Province.builder()
                .name("Neuquén")
                .description("desc")
                .image("neuquen.jpg")
                .deletionDate(null)
                .build();
        provinceRepository.save(mendoza);
        provinceRepository.save(neuquen);
        List<Province> provinces = provinceRepository.findThreeRandom();
        assertEquals(provinces.size(), 3);
    }

    @Test
    void findAllProvinceNames() {
        List<String> provinceNames = provinceRepository.findAllProvinceNames();
        assertTrue(provinceNames.contains("Buenos Aires"));
        assertTrue(provinceNames.contains("Salta"));
        assertFalse(provinceNames.contains("Misiones"));
        assertFalse(provinceNames.contains("Río Negro"));
    }

    @Test
    void findCount() {
        int provinceCount = provinceRepository.findCount();
        assertEquals(provinceCount, 2);
    }
}