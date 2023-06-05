package com.mgbt.turismoargentina_backend.model.services;

import com.mgbt.turismoargentina_backend.exceptions.EntityNotFoundException;
import com.mgbt.turismoargentina_backend.exceptions.PurchaseIncompleteException;
import com.mgbt.turismoargentina_backend.model.entities.Location;
import com.mgbt.turismoargentina_backend.model.entities.Purchase;
import com.mgbt.turismoargentina_backend.model.entities.User;
import com.mgbt.turismoargentina_backend.model.repositories.IPurchaseRepository;
import com.mgbt.turismoargentina_backend.model.services.impl.PurchaseService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @Mock
    private IPurchaseRepository purchaseRepository;

    @InjectMocks
    private PurchaseService purchaseService;

    private User user;
    private Purchase purchase1;
    private Purchase purchase2;
    private Purchase purchase3;
    private Purchase purchase4;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .idUser(1L)
                .username("user")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        purchase1 = Purchase.builder()
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(20000d)
                .isRefunded(false)
                .build();
        purchase2 = Purchase.builder()
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(15000d)
                .isRefunded(false)
                .build();
        purchase3 = Purchase.builder()
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(10000d)
                .isRefunded(false)
                .build();
        purchase4 = Purchase.builder()
                .date(new Date())
                .user(user)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(5000d)
                .isRefunded(true)
                .build();
    }

    @Test
    void getAll() {
        when(purchaseRepository.findAll()).thenReturn(List.of(purchase1, purchase2, purchase3, purchase4));
        List<Purchase> purchases = purchaseService.getAll();
        assertNotNull(purchases);
        assertEquals(purchases.size(), 4);
        assertTrue(purchases.contains(purchase1));
        assertTrue(purchases.contains(purchase2));
        assertTrue(purchases.contains(purchase3));
        assertTrue(purchases.contains(purchase4));
    }

    @Test
    void save() {
        when(purchaseRepository.save(purchase1)).thenReturn(purchase1);
        Purchase purchaseSaved = purchaseService.save(purchase1);
        verify(purchaseRepository, times(1)).save(purchase1);
        assertNotNull(purchaseSaved);
    }

    @Test
    void delete() {
        purchase1.setIdPurchase(1L);
        willDoNothing().given(purchaseRepository).delete(purchase1);
        purchaseService.delete(purchase1);
        verify(purchaseRepository, times(1)).delete(purchase1);
    }

    @Test
    void findById() {
        purchase1.setIdPurchase(1L);
        when(purchaseRepository.findById(1L)).thenReturn(Optional.ofNullable(purchase1));
        when(purchaseRepository.findById(2L)).thenThrow(EntityNotFoundException.class);
        Purchase purchaseFound = purchaseService.findById(1L);
        assertNotNull(purchaseFound);
        assertEquals(purchaseFound, purchase1);
        assertThrows(EntityNotFoundException.class, () ->  purchaseService.findById(2L));
    }

    @Test
    void getByUser() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Purchase> page = new PageImpl<>(List.of(purchase4, purchase3, purchase2, purchase1));
        when(purchaseRepository.findByUserIdUserOrderByIdPurchaseDesc(user.getIdUser(), pageable)).thenReturn(page);
        Page<Purchase> pageFound = purchaseService.getByUser(user.getIdUser(), pageable);
        assertNotNull(pageFound);
        assertEquals(pageFound.getContent().size(), 4);
        assertEquals(pageFound.getContent().get(0), purchase4);
        assertEquals(pageFound.getContent().get(1), purchase3);
        assertEquals(pageFound.getContent().get(2), purchase2);
        assertEquals(pageFound.getContent().get(3), purchase1);
    }

    @Test
    void getCountIsNotRefunded() {
        when(purchaseRepository.findCountIsNotRefunded()).thenReturn(3L);
        long purchaseCount = purchaseService.getCountIsNotRefunded();
        assertEquals(purchaseCount, 3);
    }

    @Test
    void getCountIsRefunded() {
        when(purchaseRepository.findCountIsRefunded()).thenReturn(1L);
        long purchaseCount = purchaseService.getCountIsRefunded();
        assertEquals(purchaseCount, 1);
    }

    @Test
    void getMoneyNotRefunded() {
        double totalMoney = purchase1.getPrice() + purchase2.getPrice() + purchase3.getPrice();
        when(purchaseRepository.findMoneyNotRefunded()).thenReturn(totalMoney);
        double moneyFound = purchaseService.getMoneyNotRefunded();
        assertEquals(moneyFound, totalMoney);
    }

    @Test
    void getMoneyRefunded() {
        double totalMoney = purchase4.getPrice();
        when(purchaseRepository.findMoneyRefunded()).thenReturn(totalMoney);
        double moneyFound = purchaseService.getMoneyRefunded();
        assertEquals(moneyFound, totalMoney);
    }

    @Test
    void checkIfLocationsAndActivitiesAreEmpty() {
        purchase1.setLocations(List.of(new Location()));
        assertDoesNotThrow(() -> purchaseService.validateIfLocationsAndActivitiesAreEmpty(purchase1));
        assertThrows(PurchaseIncompleteException.class, () -> purchaseService.validateIfLocationsAndActivitiesAreEmpty(purchase2));
    }
}