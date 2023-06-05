package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.repositories.ILocationRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.LocationService;
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
class LocationServiceTest {

    @Mock
    private ILocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    private Location caba;
    private Location marDelPlata;
    private Location carilo;
    private Location tigre;

    @BeforeEach
    void setUp() {
        caba = Location.builder()
                .name("Ciudad Autónoma de Buenos Aires")
                .description("desc")
                .image("caba.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        marDelPlata = Location.builder()
                .name("Mar del Plata")
                .description("desc")
                .image("mdp.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        carilo = Location.builder()
                .name("Cariló")
                .description("desc")
                .image("carilo.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(new Date())
                .build();
        tigre = Location.builder()
                .name("Tigre")
                .description("desc")
                .image("tigre.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(new Date())
                .build();
    }

    @Test
    void getAllNonDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> page = new PageImpl<>(List.of(caba, marDelPlata));
        when(locationRepository.findByDeletionDateIsNull(pageable)).thenReturn(page);
        Page<Location> pageFound = locationService.getAllNonDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertTrue(pageFound.getContent().contains(caba));
        assertTrue(pageFound.getContent().contains(marDelPlata));
        assertFalse(pageFound.getContent().contains(carilo));
        assertFalse(pageFound.getContent().contains(tigre));
    }

    @Test
    void getAllDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> page = new PageImpl<>(List.of(carilo, tigre));
        when(locationRepository.findByDeletionDateIsNotNull(pageable)).thenReturn(page);
        Page<Location> pageFound = locationService.getAllDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertFalse(pageFound.getContent().contains(caba));
        assertFalse(pageFound.getContent().contains(marDelPlata));
        assertTrue(pageFound.getContent().contains(carilo));
        assertTrue(pageFound.getContent().contains(tigre));
    }

    @Test
    void getAllNonDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> page = new PageImpl<>(List.of(caba));
        when(locationRepository.findByDeletionDateIsNullAndNameContaining("Buenos Aires", pageable)).thenReturn(page);
        Page<Location> pageFound = locationService.getAllNonDeletedByName(pageable, "Buenos Aires");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertTrue(pageFound.getContent().contains(caba));
        assertFalse(pageFound.getContent().contains(marDelPlata));
        assertFalse(pageFound.getContent().contains(carilo));
        assertFalse(pageFound.getContent().contains(tigre));
    }

    @Test
    void getAllDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> page = new PageImpl<>(List.of(tigre));
        when(locationRepository.findByDeletionDateIsNotNullAndNameContaining("igre", pageable)).thenReturn(page);
        Page<Location> pageFound = locationService.getAllDeletedByName(pageable, "igre");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertFalse(pageFound.getContent().contains(caba));
        assertFalse(pageFound.getContent().contains(marDelPlata));
        assertFalse(pageFound.getContent().contains(carilo));
        assertTrue(pageFound.getContent().contains(tigre));
    }

    @Test
    void save() {
        when(locationRepository.save(caba)).thenReturn(caba);
        Location locationSaved = locationService.save(caba);
        verify(locationRepository, times(1)).save(caba);
        assertNotNull(locationSaved);
    }

    @Test
    void delete() {
        caba.setIdLocation(1L);
        willDoNothing().given(locationRepository).delete(caba);
        locationService.delete(caba);
        verify(locationRepository, times(1)).delete(caba);
    }

    @Test
    void findById() {
        caba.setIdLocation(1L);
        when(locationRepository.findById(1L)).thenReturn(Optional.ofNullable(caba));
        when(locationRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        Location locationFound = locationService.findById(1L);
        assertNotNull(locationFound);
        assertEquals(locationFound, caba);
        assertThrows(EntityNotFoundException.class, () ->  locationService.findById(2L));
    }

    @Test
    void findByName() {
        when(locationRepository.findByName("Ciudad Autónoma de Buenos Aires")).thenReturn(caba);
        when(locationRepository.findByName("Ciudad de Buenos Aires")).thenThrow(EntityNotFoundException.class);
        Location locationFound = locationService.findByName("Ciudad Autónoma de Buenos Aires");
        assertNotNull(locationFound);
        assertEquals(locationFound, caba);
        assertThrows(EntityNotFoundException.class, () ->  locationService.findByName("Ciudad de Buenos Aires"));
    }

    @Test
    void getFourRandom() {
        Location cayafate = Location.builder()
                .name("Cayafate")
                .description("desc")
                .image("cayafate.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        Location cuevasDeAcsibi = Location.builder()
                .name("Cuevas de Acsibi")
                .description("desc")
                .image("cuevas.jpg")
                .price(1d)
                .province(new Province())
                .deletionDate(null)
                .build();
        when(locationRepository.findFourRandom()).thenReturn(List.of(caba, marDelPlata, cayafate, cuevasDeAcsibi));
        List<Location> locationsFound = locationService.getFourRandom();
        assertNotNull(locationsFound);
        assertEquals(locationsFound.size(), 4);
    }

    @Test
    void getAllLocationNames() {
        when(locationRepository.findAllLocationNames()).thenReturn(List.of("Ciudad Autónoma de Buenos Aires", "Mar del Plata"));
        List<String> locationNames = locationService.getAllLocationNames();
        assertNotNull(locationNames);
        assertEquals(locationNames.size(), 2);
    }

    @Test
    void getByProvinceName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Location> page = new PageImpl<>(List.of(caba, marDelPlata));
        when(locationRepository.findByProvinceNameAndDeletionDateIsNull(pageable, "Buenos Aires")).thenReturn(page);
        Page<Location> pageFound = locationService.getByProvinceName(pageable, "Buenos Aires");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertTrue(pageFound.getContent().contains(caba));
        assertTrue(pageFound.getContent().contains(marDelPlata));
        assertFalse(pageFound.getContent().contains(carilo));
        assertFalse(pageFound.getContent().contains(tigre));
    }

    @Test
    void getByProvinceId() {
        when(locationRepository.findByProvinceIdProvinceAndDeletionDateIsNull(1L)).thenReturn(List.of(caba, marDelPlata));
        List<Location> locations = locationService.getByProvinceId(1L);
        assertNotNull(locations);
        assertEquals(locations.size(), 2);
        assertTrue(locations.contains(caba));
        assertTrue(locations.contains(marDelPlata));
        assertFalse(locations.contains(carilo));
        assertFalse(locations.contains(tigre));
    }

    @Test
    void getCount() {
        when(locationRepository.findCount()).thenReturn(2);
        int locationCount = locationService.getCount();
        assertEquals(locationCount, 2);
    }
}