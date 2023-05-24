package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ILocationRepositoryTest {

    @Autowired
    private IProvinceRepository provinceRepository;

    @Autowired
    private ILocationRepository locationRepository;

    private Province buenosAires;
    private Location caba;
    private Location marDelPlata;
    private Location carilo;
    private Location tigre;

    @BeforeEach
    void setUp() {
        buenosAires = Province.builder()
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        this.provinceRepository.save(buenosAires);
        caba = Location.builder()
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(null)
                .build();
        marDelPlata = Location.builder()
                .name("Mar del Plata")
                .description("desc")
                .image("mdp.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(null)
                .build();
        carilo = Location.builder()
                .name("Cariló")
                .description("desc")
                .image("carilo.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(new Date())
                .build();
        tigre = Location.builder()
                .name("Tigre")
                .description("desc")
                .image("tigre.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(new Date())
                .build();
        this.locationRepository.save(caba);
        this.locationRepository.save(marDelPlata);
        this.locationRepository.save(carilo);
        this.locationRepository.save(tigre);
    }

    @Test
    void findByName() {
        Location location = locationRepository.findByName("Ciudad Autónoma de Buenos Aires");
        assertThat(location).isNotNull();
        assertEquals(location.getName(), "Ciudad Autónoma de Buenos Aires");
    }

    @Test
    void findByDeletionDateIsNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> locations = locationRepository.findByDeletionDateIsNull(pageable);
        assertEquals(locations.stream().toList().size(), 2);
        assertTrue(locations.stream().toList().contains(caba));
        assertTrue(locations.stream().toList().contains(marDelPlata));
        assertFalse(locations.stream().toList().contains(carilo));
        assertFalse(locations.stream().toList().contains(tigre));
    }

    @Test
    void findByDeletionDateIsNotNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> locations = locationRepository.findByDeletionDateIsNotNull(pageable);
        assertEquals(locations.stream().toList().size(), 2);
        assertFalse(locations.stream().toList().contains(caba));
        assertFalse(locations.stream().toList().contains(marDelPlata));
        assertTrue(locations.stream().toList().contains(carilo));
        assertTrue(locations.stream().toList().contains(tigre));
    }

    @Test
    void findByDeletionDateIsNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> locations = locationRepository.findByDeletionDateIsNullAndNameContaining("Buenos Aires", pageable);
        assertEquals(locations.stream().toList().size(), 1);
        assertTrue(locations.stream().toList().contains(caba));
        assertFalse(locations.stream().toList().contains(marDelPlata));
        assertFalse(locations.stream().toList().contains(carilo));
        assertFalse(locations.stream().toList().contains(tigre));
    }

    @Test
    void findByDeletionDateIsNotNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> locations = locationRepository.findByDeletionDateIsNotNullAndNameContaining("igre", pageable);
        assertEquals(locations.stream().toList().size(), 1);
        assertFalse(locations.stream().toList().contains(caba));
        assertFalse(locations.stream().toList().contains(marDelPlata));
        assertFalse(locations.stream().toList().contains(carilo));
        assertTrue(locations.stream().toList().contains(tigre));
    }

    @Test
    void findFourRandom() {
        Province salta = Province.builder()
                .name("Salta")
                .description("desc")
                .image("salta.jpg")
                .deletionDate(null)
                .build();
        provinceRepository.save(salta);
        Location cayafate = Location.builder()
                .name("Cayafate")
                .description("desc")
                .image("cayafate.jpg")
                .price(1d)
                .province(salta)
                .deletionDate(null)
                .build();
        Location molinos = Location.builder()
                .name("Molinos")
                .description("desc")
                .image("molinos.jpg")
                .price(1d)
                .province(salta)
                .deletionDate(null)
                .build();
        Location cuevasDeAcsibi = Location.builder()
                .name("Cuevas de Acsibi")
                .description("desc")
                .image("cuevas.jpg")
                .price(1d)
                .province(salta)
                .deletionDate(null)
                .build();
        locationRepository.save(cayafate);
        locationRepository.save(molinos);
        locationRepository.save(cuevasDeAcsibi);
        List<Location> locations = locationRepository.findFourRandom();
        assertEquals(locations.size(), 4);
    }

    @Test
    void findAllLocationNames() {
        List<String> locationNames = locationRepository.findAllLocationNames();
        assertTrue(locationNames.contains("Ciudad Autónoma de Buenos Aires"));
        assertTrue(locationNames.contains("Mar del Plata"));
        assertFalse(locationNames.contains("Cariló"));
        assertFalse(locationNames.contains("Tigre"));
    }

    @Test
    void findByProvinceName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> locations = locationRepository.findByProvinceNameAndDeletionDateIsNull(pageable, buenosAires.getName());
        assertEquals(locations.stream().toList().size(), 2);
        assertTrue(locations.stream().toList().contains(caba));
        assertTrue(locations.stream().toList().contains(marDelPlata));
        assertFalse(locations.stream().toList().contains(carilo));
        assertFalse(locations.stream().toList().contains(tigre));
    }

    @Test
    void findByProvinceIdProvince() {
        List<Location> locations = locationRepository.findByProvinceIdProvinceAndDeletionDateIsNull(buenosAires.getIdProvince());
        assertEquals(locations.size(), 2);
        assertTrue(locations.stream().toList().contains(caba));
        assertTrue(locations.stream().toList().contains(marDelPlata));
        assertFalse(locations.stream().toList().contains(carilo));
        assertFalse(locations.stream().toList().contains(tigre));
    }

    @Test
    void findCount() {
        int locationCount = locationRepository.findCount();
        assertEquals(locationCount, 2);
    }
}