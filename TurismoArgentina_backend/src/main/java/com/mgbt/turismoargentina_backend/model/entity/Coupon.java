package com.mgbt.turismoargentina_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.*;
import java.util.Date;

@Data
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
    private String name;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "start_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "finish_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date finishDate;
}
