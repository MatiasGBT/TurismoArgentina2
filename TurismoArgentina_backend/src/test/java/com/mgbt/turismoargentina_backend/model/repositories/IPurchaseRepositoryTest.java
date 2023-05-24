package com.mgbt.turismoargentina_backend.model.repositories;

import com.mgbt.turismoargentina_backend.model.entities.Purchase;
import com.mgbt.turismoargentina_backend.model.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IPurchaseRepositoryTest {

    @Autowired
    private IPurchaseRepository purchaseRepository;

    @Autowired
    private IUserRepository userRepository;

    private User user1;
    private User user2;
    private Purchase purchase1;
    private Purchase purchase2;
    private Purchase purchase3;
    private Purchase purchase4;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .username("user1")
                .name("Albert")
                .lastName("Wesker")
                .creationDate(new Date())
                .build();
        user2 = User.builder()
                .username("user2")
                .name("Chris")
                .lastName("Redfield")
                .creationDate(new Date())
                .build();
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        purchase1 = Purchase.builder()
                .date(new Date())
                .user(user1)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(10000d)
                .build();
        purchase2 = Purchase.builder()
                .date(new Date())
                .user(user1)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(5000d)
                .build();
        purchase3 = Purchase.builder()
                .date(new Date())
                .user(user2)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(20000d)
                .build();
        purchase4 = Purchase.builder()
                .date(new Date())
                .user(user2)
                .locations(new ArrayList<>())
                .activities(new ArrayList<>())
                .price(10000d)
                .build();
        this.purchaseRepository.save(purchase1);
        this.purchaseRepository.save(purchase2);
        this.purchaseRepository.save(purchase3);
        this.purchaseRepository.save(purchase4);
        purchase2.setIsRefunded(true);
        purchase4.setIsRefunded(true);
        this.purchaseRepository.save(purchase2);
        this.purchaseRepository.save(purchase4);
    }

    @Test
    void findByUserIdUserOrderByIdPurchaseDesc() {
        Pageable pageable = PageRequest.of(0, 9);
        Page<Purchase> purchases = purchaseRepository.findByUserIdUserOrderByIdPurchaseDesc(user1.getIdUser(), pageable);
        assertEquals(purchases.stream().toList().size(), 2);
        assertTrue(purchases.stream().toList().contains(purchase1));
        assertTrue(purchases.stream().toList().contains(purchase2));
        assertFalse(purchases.stream().toList().contains(purchase3));
        assertFalse(purchases.stream().toList().contains(purchase4));
        assertEquals(purchases.stream().toList().get(0), purchase2);
        assertEquals(purchases.stream().toList().get(1), purchase1);
    }

    @Test
    void findCountIsNotRefunded() {
        Long purchasesNotRefundedCount = purchaseRepository.findCountIsNotRefunded();
        assertEquals(purchasesNotRefundedCount, 2);
    }

    @Test
    void findCountIsRefunded() {
        Long purchasesRefundedCount = purchaseRepository.findCountIsRefunded();
        assertEquals(purchasesRefundedCount, 2);
    }

    @Test
    void findMoneyNotRefunded() {
        Double purchasesNotRefundedMoney = purchaseRepository.findMoneyNotRefunded();
        assertEquals(purchasesNotRefundedMoney, purchase1.getPrice() + purchase3.getPrice());
    }

    @Test
    void findMoneyRefunded() {
        Double purchasesRefundedMoney = purchaseRepository.findMoneyRefunded();
        assertEquals(purchasesRefundedMoney, purchase2.getPrice() + purchase4.getPrice());
    }
}