package com.mgbt.turismoargentina_backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.io.*;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "redeemed_coupons", schema="turismo_argentina")
public class RedeemedCoupon implements Serializable {

    public RedeemedCoupon() {}

    public RedeemedCoupon(User user, Coupon coupon) {
        this.user = user;
        this.coupon = coupon;
        this.date = new Date();
        this.isUsed = false;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_redeemed_coupon")
    private Long idRedeemedCoupon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","name","lastName","creationDate","deletionDate"})
    @JoinColumn(name = "id_user", nullable = false)
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","redeemedCoupons"})
    @JoinColumn(name = "id_coupon", nullable = false)
    @ToString.Exclude
    private Coupon coupon;

    @Column(name = "date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @Column(name="used", columnDefinition = "BOOLEAN", nullable = false)
    private Boolean isUsed;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        RedeemedCoupon that = (RedeemedCoupon) o;
        return idRedeemedCoupon != null && Objects.equals(idRedeemedCoupon, that.idRedeemedCoupon);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
