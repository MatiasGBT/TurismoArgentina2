package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Activity;
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

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IActivityRepositoryTest {

    @Autowired
    private IProvinceRepository provinceRepository;

    @Autowired
    private ILocationRepository locationRepository;

    @Autowired
    private IActivityRepository activityRepository;

    private Location puertoIguazu;
    private Activity paseoBarcoTrenCamion;
    private Activity barcoMotor;
    private Activity vueloPanoramico;
    private Activity tirolinaRappel;
    private Activity recorridoCataratas;

    @BeforeEach
    void setUp() {
        Province misiones = Province.builder()
                .name("Misiones")
                .description("desc")
                .image("misiones.jpg")
                .deletionDate(null)
                .build();
        this.provinceRepository.save(misiones);
        puertoIguazu = Location.builder()
                .name("Puerto Iguazú")
                .description("desc")
                .image("puerto.jpg")
                .price(1d)
                .province(misiones)
                .deletionDate(null)
                .build();
        this.locationRepository.save(puertoIguazu);
        paseoBarcoTrenCamion = Activity.builder()
                .name("Paseo en barco, tren y camión de safari")
                .description("desc")
                .image1("paseobarcotrencamion.jpg")
                .price(1d)
                .location(puertoIguazu)
                .deletionDate(null)
                .build();
        barcoMotor = Activity.builder()
                .name("Barco a motor bajo las cataratas")
                .description("desc")
                .image1("barcomotor.jpg")
                .price(1d)
                .location(puertoIguazu)
                .deletionDate(null)
                .build();
        vueloPanoramico = Activity.builder()
                .name("Vuelo panorámico en helicóptero por las Cataratas del Iguazú")
                .description("desc")
                .image1("vuelopanoramico.jpg")
                .price(1d)
                .location(puertoIguazu)
                .deletionDate(null)
                .build();
        tirolinaRappel = Activity.builder()
                .name("Aventura Privada de Tirolina y Rappel")
                .description("desc")
                .image1("tirolinarappel.jpg")
                .price(1d)
                .location(puertoIguazu)
                .deletionDate(new Date())
                .build();
        recorridoCataratas = Activity.builder()
                .name("Recorrido por las Cataratas del Iguazú")
                .description("desc")
                .image1("recorridocataratas.jpg")
                .price(1d)
                .location(puertoIguazu)
                .deletionDate(new Date())
                .build();
        this.activityRepository.save(paseoBarcoTrenCamion);
        this.activityRepository.save(barcoMotor);
        this.activityRepository.save(vueloPanoramico);
        this.activityRepository.save(tirolinaRappel);
        this.activityRepository.save(recorridoCataratas);
    }

    @Test
    void findByDeletionDateIsNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> activities = activityRepository.findByDeletionDateIsNull(pageable);
        assertEquals(activities.stream().toList().size(), 3);
        assertTrue(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertTrue(activities.stream().toList().contains(barcoMotor));
        assertTrue(activities.stream().toList().contains(vueloPanoramico));
        assertFalse(activities.stream().toList().contains(tirolinaRappel));
        assertFalse(activities.stream().toList().contains(recorridoCataratas));
    }

    @Test
    void findByDeletionDateIsNotNull() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> activities = activityRepository.findByDeletionDateIsNotNull(pageable);
        assertEquals(activities.stream().toList().size(), 2);
        assertFalse(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertFalse(activities.stream().toList().contains(barcoMotor));
        assertFalse(activities.stream().toList().contains(vueloPanoramico));
        assertTrue(activities.stream().toList().contains(tirolinaRappel));
        assertTrue(activities.stream().toList().contains(recorridoCataratas));
    }

    @Test
    void findByDeletionDateIsNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> activities = activityRepository.findByDeletionDateIsNullAndNameContaining("las", pageable);
        assertEquals(activities.stream().toList().size(), 2);
        assertFalse(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertTrue(activities.stream().toList().contains(barcoMotor));
        assertTrue(activities.stream().toList().contains(vueloPanoramico));
        assertFalse(activities.stream().toList().contains(tirolinaRappel));
        assertFalse(activities.stream().toList().contains(recorridoCataratas));
    }

    @Test
    void findByDeletionDateIsNotNullAndNameContaining() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> activities = activityRepository.findByDeletionDateIsNotNullAndNameContaining("Tirolina", pageable);
        assertEquals(activities.stream().toList().size(), 1);
        assertFalse(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertFalse(activities.stream().toList().contains(barcoMotor));
        assertFalse(activities.stream().toList().contains(vueloPanoramico));
        assertTrue(activities.stream().toList().contains(tirolinaRappel));
        assertFalse(activities.stream().toList().contains(recorridoCataratas));
    }

    @Test
    void findFiveRandom() {
        Province buenosAires = Province.builder()
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        this.provinceRepository.save(buenosAires);
        Location caba = Location.builder()
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(null)
                .build();
        this.locationRepository.save(caba);
        Activity entradaMalba = Activity.builder()
                .name("Entrada para el Museo de Arte Latinoamericano de Buenos Aires")
                .description("desc")
                .image1("malba.jpg")
                .price(1d)
                .location(caba)
                .deletionDate(null)
                .build();
        Activity recorridoCaba = Activity.builder()
                .name("Recorrido privado por la ciudad de Buenos Aires en coche")
                .description("desc")
                .image1("recorridocaba.jpg")
                .price(1d)
                .location(caba)
                .deletionDate(null)
                .build();
        Activity entradaColon = Activity.builder()
                .name("Entrada para el Teatro Colón")
                .description("desc")
                .image1("entradacolon.jpg")
                .price(1d)
                .location(caba)
                .deletionDate(null)
                .build();
        this.activityRepository.save(entradaMalba);
        this.activityRepository.save(recorridoCaba);
        this.activityRepository.save(entradaColon);
        List<Activity> activities = this.activityRepository.findFiveRandom();
        assertEquals(activities.size(), 5);
    }

    @Test
    void findByLocationNameAndDeletionDateIsNull() {
        Province buenosAires = Province.builder()
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        this.provinceRepository.save(buenosAires);
        Location caba = Location.builder()
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(null)
                .build();
        this.locationRepository.save(caba);
        Activity entradaMalba = Activity.builder()
                .name("Entrada para el Museo de Arte Latinoamericano de Buenos Aires")
                .description("desc")
                .image1("malba.jpg")
                .price(1d)
                .location(caba)
                .deletionDate(null)
                .build();
        this.activityRepository.save(entradaMalba);
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> activities = activityRepository.findByLocationNameAndDeletionDateIsNull(pageable, puertoIguazu.getName());
        assertTrue(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertTrue(activities.stream().toList().contains(barcoMotor));
        assertTrue(activities.stream().toList().contains(vueloPanoramico));
        assertFalse(activities.stream().toList().contains(tirolinaRappel));
        assertFalse(activities.stream().toList().contains(recorridoCataratas));
        assertFalse(activities.stream().toList().contains(entradaMalba));
    }

    @Test
    void findByLocationIdLocationAndDeletionDateIsNull() {
        Province buenosAires = Province.builder()
                .name("Buenos Aires")
                .description("desc")
                .image("bsas.jpg")
                .deletionDate(null)
                .build();
        this.provinceRepository.save(buenosAires);
        Location caba = Location.builder()
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(buenosAires)
                .deletionDate(null)
                .build();
        this.locationRepository.save(caba);
        Activity entradaMalba = Activity.builder()
                .name("Entrada para el Museo de Arte Latinoamericano de Buenos Aires")
                .description("desc")
                .image1("malba.jpg")
                .price(1d)
                .location(caba)
                .deletionDate(null)
                .build();
        this.activityRepository.save(entradaMalba);
        List<Activity> activities = activityRepository.findByLocationIdLocationAndDeletionDateIsNull(puertoIguazu.getIdLocation());
        assertTrue(activities.stream().toList().contains(paseoBarcoTrenCamion));
        assertTrue(activities.stream().toList().contains(barcoMotor));
        assertTrue(activities.stream().toList().contains(vueloPanoramico));
        assertFalse(activities.stream().toList().contains(tirolinaRappel));
        assertFalse(activities.stream().toList().contains(recorridoCataratas));
        assertFalse(activities.stream().toList().contains(entradaMalba));
    }

    @Test
    void findCount() {
        int activityCount = activityRepository.findCount();
        assertEquals(activityCount, 3);
    }
}