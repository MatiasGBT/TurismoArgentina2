package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.model.entities.Province;
import com.mgbt.turismoargentina_backend.model.repositories.IProvinceRepository;
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
class ProvinceServiceTest {

    @Mock
    private IProvinceRepository provinceRepository;

    @InjectMocks
    private ProvinceService provinceService;

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
    }

    @Test
    void getAllNonDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> page = new PageImpl<>(List.of(buenosAires, salta));
        when(provinceRepository.findByDeletionDateIsNull(pageable)).thenReturn(page);
        Page<Province> pageFound = provinceService.getAllNonDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertTrue(pageFound.getContent().contains(buenosAires));
        assertTrue(pageFound.getContent().contains(salta));
        assertFalse(pageFound.getContent().contains(misiones));
        assertFalse(pageFound.getContent().contains(rioNegro));
    }

    @Test
    void getAllDeleted() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> page = new PageImpl<>(List.of(misiones, rioNegro));
        when(provinceRepository.findByDeletionDateIsNotNull(pageable)).thenReturn(page);
        Page<Province> pageFound = provinceService.getAllDeleted(pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 2);
        assertFalse(pageFound.getContent().contains(buenosAires));
        assertFalse(pageFound.getContent().contains(salta));
        assertTrue(pageFound.getContent().contains(misiones));
        assertTrue(pageFound.getContent().contains(rioNegro));
    }

    @Test
    void getAllNonDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> page = new PageImpl<>(List.of(buenosAires));
        when(provinceRepository.findByDeletionDateIsNullAndNameContaining("Aires", pageable)).thenReturn(page);
        Page<Province> pageFound = provinceService.getAllNonDeletedByName(pageable, "Aires");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertTrue(pageFound.getContent().contains(buenosAires));
        assertFalse(pageFound.getContent().contains(salta));
        assertFalse(pageFound.getContent().contains(misiones));
        assertFalse(pageFound.getContent().contains(rioNegro));
    }

    @Test
    void getAllDeletedByName() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Province> page = new PageImpl<>(List.of(misiones));
        when(provinceRepository.findByDeletionDateIsNotNullAndNameContaining("isiones", pageable)).thenReturn(page);
        Page<Province> pageFound = provinceService.getAllDeletedByName(pageable, "isiones");
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 1);
        assertFalse(pageFound.getContent().contains(buenosAires));
        assertFalse(pageFound.getContent().contains(salta));
        assertTrue(pageFound.getContent().contains(misiones));
        assertFalse(pageFound.getContent().contains(rioNegro));
    }

    @Test
    void save() {
        when(provinceRepository.save(buenosAires)).thenReturn(buenosAires);
        Province provinceSaved = provinceService.save(buenosAires);
        verify(provinceRepository, times(1)).save(buenosAires);
        assertNotNull(provinceSaved);
    }

    @Test
    void delete() {
        buenosAires.setIdProvince(1L);
        willDoNothing().given(provinceRepository).delete(buenosAires);
        provinceService.delete(buenosAires);
        verify(provinceRepository, times(1)).delete(buenosAires);
    }

    @Test
    void findById() {
        buenosAires.setIdProvince(1L);
        when(provinceRepository.findById(1L)).thenReturn(Optional.ofNullable(buenosAires));
        when(provinceRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        Province provinceFound = provinceService.findById(1L);
        assertNotNull(provinceFound);
        assertEquals(provinceFound, buenosAires);
        assertThrows(EntityNotFoundException.class, () ->  provinceService.findById(2L));
    }

    @Test
    void findByName() {
        when(provinceRepository.findByName("Buenos Aires")).thenReturn(buenosAires);
        when(provinceRepository.findByName("Bires Auenos")).thenThrow(EntityNotFoundException.class);
        Province provinceFound = provinceService.findByName("Buenos Aires");
        assertNotNull(provinceFound);
        assertEquals(provinceFound, buenosAires);
        assertThrows(EntityNotFoundException.class, () ->  provinceService.findByName("Bires Auenos"));
    }

    @Test
    void getThreeRandom() {
        Province mendoza = Province.builder()
                .name("Mendoza")
                .description("desc")
                .image("mendoza.jpg")
                .deletionDate(null)
                .build();
        when(provinceRepository.findThreeRandom()).thenReturn(List.of(buenosAires, salta, mendoza));
        List<Province> provincesFound = provinceService.getThreeRandom();
        assertNotNull(provincesFound);
        assertEquals(provincesFound.size(), 3);
    }

    @Test
    void getAllProvinceNames() {
        when(provinceRepository.findAllProvinceNames()).thenReturn(List.of("Buenos Aires", "Salta"));
        List<String> provinceNames = provinceService.getAllProvinceNames();
        assertNotNull(provinceNames);
        assertEquals(provinceNames.size(), 2);
    }

    @Test
    void getCount() {
        when(provinceRepository.findCount()).thenReturn(2);
        int provinceCount = provinceService.getCount();
        assertEquals(provinceCount, 2);
    }
}