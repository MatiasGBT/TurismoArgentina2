package com.mgbt.turismoargentina_backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;
import java.io.*;
import java.util.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupons", schema="turismo_argentina")
public class Coupon implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_coupon")
    private Long idCoupon;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 30, message = "Name cannot exceed 30 characters")
    private String name;

    @Column(name = "discount")
    @NotNull(message = "Discount is mandatory")
    @DecimalMin(value = "1", message = "Discount must be greater than 0")
    @DecimalMax(value = "100", message = "Discount cannot exceed 100%")
    private Integer discount;

    @Column(name = "start_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull(message = "Start date is mandatory")
    private Date startDate;

    @Column(name = "finish_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull(message = "Finish date is mandatory")
    private Date finishDate;

    @OneToMany(mappedBy="coupon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<RedeemedCoupon> redeemedCoupons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Coupon coupon = (Coupon) o;
        return idCoupon != null && Objects.equals(idCoupon, coupon.idCoupon);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
