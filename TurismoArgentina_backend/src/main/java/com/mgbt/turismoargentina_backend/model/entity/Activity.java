package com.mgbt.turismoargentina_backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.io.*;
import java.util.Date;

@Data
@Entity
@Table(name = "activities", schema="turismo_argentina")
public class Activity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_activity")
    private Long idActivity;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 45, message = "Name cannot exceed 65 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 600, message = "Description cannot exceed 600 characters")
    private String description;

    @Column(name = "image1")
    @NotBlank(message = "Image1 is mandatory")
    @Size(max = 80, message = "Image1 name cannot exceed 80 characters")
    private String image1;

    @Column(name = "image2")
    @Size(max = 80, message = "Image2 name cannot exceed 80 characters")
    private String image2;

    @Column(name = "image3")
    @Size(max = 80, message = "Image3 name cannot exceed 80 characters")
    private String image3;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "1.0", message = "Price must be greater than 0")
    private Double price;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "deletion_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image","price","deletionDate"})
    @JoinColumn(name = "id_location", nullable = false)
    private Location location;
}
