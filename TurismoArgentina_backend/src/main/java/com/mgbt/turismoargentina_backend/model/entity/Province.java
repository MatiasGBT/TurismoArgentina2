package com.mgbt.turismoargentina_backend.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.io.*;
import java.util.Date;

@Data
@Entity
@Table(name = "provinces", schema="turismo_argentina")
public class Province implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_province")
    private Long idProvince;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "deletion_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletionDate;
}
