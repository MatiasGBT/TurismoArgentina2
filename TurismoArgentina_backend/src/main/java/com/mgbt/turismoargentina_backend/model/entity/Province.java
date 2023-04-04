package com.mgbt.turismoargentina_backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name is mandatory")
    @Size(max = 20, message = "Name cannot exceed 20 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 600, message = "Description cannot exceed 600 characters")
    private String description;

    @Column(name = "image")
    @NotBlank(message = "Image is mandatory")
    @Size(max = 80, message = "Image name cannot exceed 80 characters")
    private String image;

    @Column(name = "deletion_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletionDate;
}
