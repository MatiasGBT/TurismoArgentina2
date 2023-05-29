package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.ActivityImageNumberException;
import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Activity;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.repositories.IActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceTest {

    @Mock
    private IActivityRepository activityRepository;

    @Mock
    private FileService fileService;

    @InjectMocks
    private ActivityService activityService;

    private Activity paseoBarcoTrenCamion;
    private Activity barcoMotor;
    private Activity vueloPanoramico;
    private Activity tirolinaRappel;
    private Activity recorridoCataratas;

    @BeforeEach
    void setUp() {
        paseoBarcoTrenCamion = Activity.builder()
                .name("Paseo en barco, tren y camión de safari")
                .description("desc")
                .image1("paseobarcotrencamion1.jpg")
                .image2("paseobarcotrencamion2.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        barcoMotor = Activity.builder()
                .name("Barco a motor bajo las cataratas")
                .description("desc")
                .image1("barcomotor.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        vueloPanoramico = Activity.builder()
                .name("Vuelo panorámico en helicóptero por las Cataratas del Iguazú")
                .description("desc")
                .image1("vuelopanoramico.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        tirolinaRappel = Activity.builder()
                .name("Aventura Privada de Tirolina y Rappel")
                .description("desc")
                .image1("tirolinarappel.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(new Date())
                .build();
        recorridoCataratas = Activity.builder()
                .name("Recorrido por las Cataratas del Iguazú")
                .description("desc")
                .image1("recorridocataratas.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(new Date())
                .build();
    }

    @Test
    void getAllNonDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> page = new PageImpl<>(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        when(activityRepository.findByDeletionDateIsNull(pageable)).thenReturn(page);
        Page<Activity> pageFound = activityService.getAllNonDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 3);
        assertTrue(pageFound.getContent().contains(paseoBarcoTrenCamion));
        assertTrue(pageFound.getContent().contains(barcoMotor));
        assertTrue(pageFound.getContent().contains(vueloPanoramico));
        assertFalse(pageFound.getContent().contains(tirolinaRappel));
        assertFalse(pageFound.getContent().contains(recorridoCataratas));
    }

    @Test
    void getAllDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> page = new PageImpl<>(List.of(tirolinaRappel, recorridoCataratas));
        when(activityRepository.findByDeletionDateIsNotNull(pageable)).thenReturn(page);
        Page<Activity> pageFound = activityService.getAllDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertFalse(pageFound.getContent().contains(paseoBarcoTrenCamion));
        assertFalse(pageFound.getContent().contains(barcoMotor));
        assertFalse(pageFound.getContent().contains(vueloPanoramico));
        assertTrue(pageFound.getContent().contains(tirolinaRappel));
        assertTrue(pageFound.getContent().contains(recorridoCataratas));
    }

    @Test
    void getAllNonDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> page = new PageImpl<>(List.of(barcoMotor, vueloPanoramico));
        when(activityRepository.findByDeletionDateIsNullAndNameContaining("las", pageable)).thenReturn(page);
        Page<Activity> pageFound = activityService.getAllNonDeletedByName(pageable, "las");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertFalse(pageFound.getContent().contains(paseoBarcoTrenCamion));
        assertTrue(pageFound.getContent().contains(barcoMotor));
        assertTrue(pageFound.getContent().contains(vueloPanoramico));
        assertFalse(pageFound.getContent().contains(tirolinaRappel));
        assertFalse(pageFound.getContent().contains(recorridoCataratas));
    }

    @Test
    void getAllDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> page = new PageImpl<>(List.of(tirolinaRappel));
        when(activityRepository.findByDeletionDateIsNotNullAndNameContaining("Tirolina", pageable)).thenReturn(page);
        Page<Activity> pageFound = activityService.getAllDeletedByName(pageable, "Tirolina");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertFalse(pageFound.getContent().contains(paseoBarcoTrenCamion));
        assertFalse(pageFound.getContent().contains(barcoMotor));
        assertFalse(pageFound.getContent().contains(vueloPanoramico));
        assertTrue(pageFound.getContent().contains(tirolinaRappel));
        assertFalse(pageFound.getContent().contains(recorridoCataratas));
    }

    @Test
    void save() {
        when(activityRepository.save(paseoBarcoTrenCamion)).thenReturn(paseoBarcoTrenCamion);
        Activity activitySaved = activityService.save(paseoBarcoTrenCamion);
        verify(activityRepository, times(1)).save(paseoBarcoTrenCamion);
        assertNotNull(activitySaved);
    }

    @Test
    void delete() {
        paseoBarcoTrenCamion.setIdActivity(1L);
        willDoNothing().given(activityRepository).delete(paseoBarcoTrenCamion);
        activityService.delete(paseoBarcoTrenCamion);
        verify(activityRepository, times(1)).delete(paseoBarcoTrenCamion);
    }

    @Test
    void findById() {
        paseoBarcoTrenCamion.setIdActivity(1L);
        when(activityRepository.findById(1L)).thenReturn(Optional.ofNullable(paseoBarcoTrenCamion));
        when(activityRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        Activity activityFound = activityService.findById(1L);
        assertNotNull(activityFound);
        assertEquals(activityFound, paseoBarcoTrenCamion);
        assertThrows(EntityNotFoundException.class, () ->  activityService.findById(2L));
    }

    @Test
    void getFiveRandom() {
        Activity entradaMalba = Activity.builder()
                .name("Entrada para el Museo de Arte Latinoamericano de Buenos Aires")
                .description("desc")
                .image1("malba.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        Activity entradaColon = Activity.builder()
                .name("Entrada para el Teatro Colón")
                .description("desc")
                .image1("entradacolon.jpg")
                .price(1d)
                .location(new Location())
                .deletionDate(null)
                .build();
        when(activityRepository.findFiveRandom()).thenReturn(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico, entradaMalba, entradaColon));
        List<Activity> activitiesFound = activityService.getFiveRandom();
        assertNotNull(activitiesFound);
        assertEquals(activitiesFound.size(), 5);
    }

    @Test
    void getByLocationName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Activity> page = new PageImpl<>(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        when(activityRepository.findByLocationNameAndDeletionDateIsNull(pageable, "Puerto Iguazú")).thenReturn(page);
        Page<Activity> pageFound = activityService.getByLocationName(pageable, "Puerto Iguazú");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 3);
        assertTrue(pageFound.getContent().contains(paseoBarcoTrenCamion));
        assertTrue(pageFound.getContent().contains(barcoMotor));
        assertTrue(pageFound.getContent().contains(vueloPanoramico));
        assertFalse(pageFound.getContent().contains(tirolinaRappel));
        assertFalse(pageFound.getContent().contains(recorridoCataratas));
    }

    @Test
    void getByLocationId() {
        when(activityRepository.findByLocationIdLocationAndDeletionDateIsNull(1L)).thenReturn(List.of(paseoBarcoTrenCamion, barcoMotor, vueloPanoramico));
        List<Activity> activities = activityService.getByLocationId(1L);
        assertNotNull(activities);
        assertEquals(activities.size(), 3);
        assertTrue(activities.contains(paseoBarcoTrenCamion));
        assertTrue(activities.contains(barcoMotor));
        assertTrue(activities.contains(vueloPanoramico));
        assertFalse(activities.contains(tirolinaRappel));
        assertFalse(activities.contains(recorridoCataratas));
    }

    @Test
    void getCount() {
        when(activityRepository.findCount()).thenReturn(3);
        int activityCount = activityService.getCount();
        assertEquals(activityCount, 3);
    }

    @Test
    void updateImage() {
        when(fileService.delete("paseobarcotrencamion1.jpg", "example_directory")).thenReturn(true);
        when(fileService.delete("paseobarcotrencamion2.jpg", "example_directory")).thenReturn(true);
        activityService.updateImage(paseoBarcoTrenCamion, 1, "newimage1.jpg", "example_directory");
        activityService.updateImage(paseoBarcoTrenCamion, 2, "newimage2.jpg", "example_directory");
        activityService.updateImage(paseoBarcoTrenCamion, 3, "newimage3.jpg", "example_directory");
        assertEquals(paseoBarcoTrenCamion.getImage1(), "newimage1.jpg");
        assertEquals(paseoBarcoTrenCamion.getImage2(), "newimage2.jpg");
        assertEquals(paseoBarcoTrenCamion.getImage3(), "newimage3.jpg");
        assertThrows(ActivityImageNumberException.class, () -> activityService.updateImage(paseoBarcoTrenCamion, 4, "newimage.jpg", "example_directory"));
    }
}