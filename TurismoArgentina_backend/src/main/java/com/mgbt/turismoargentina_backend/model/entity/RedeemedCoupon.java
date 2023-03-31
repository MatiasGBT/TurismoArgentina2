package com.mgbt.turismoargentina_backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.io.*;
import java.util.Date;

@Data
@Entity
@Table(name = "redeemed_coupons", schema="turismo_argentina")
public class RedeemedCoupon implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_redeemed_coupon")
    private Long idRedeemedCoupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","name","lastName","creationDate","deletionDate"})
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @JoinColumn(name = "id_coupon", nullable = false)
    private Coupon coupon;

    @Column(name = "date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="used", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isUsed;

    @PrePersist
    public void setUp() {
        this.date = new Date();
        this.isUsed = false;
    }
}
