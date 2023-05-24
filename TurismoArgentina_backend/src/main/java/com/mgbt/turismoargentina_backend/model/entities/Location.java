package com.mgbt.turismoargentina_backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "locations", schema="turismo_argentina")
public class Location implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_location")
    private Long idLocation;

    @Column(name = "name")
    @NotBlank(message = "Name is mandatory")
    @Size(max = 45, message = "Name cannot exceed 45 characters")
    private String name;

    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    @Size(max = 600, message = "Description cannot exceed 600 characters")
    private String description;

    @Column(name = "image")
    @NotBlank(message = "Image is mandatory")
    @Size(max = 80, message = "Image name cannot exceed 80 characters")
    private String image;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    @DecimalMin(value = "1.0", message = "Price must be greater than 0")
    private Double price;

    @Column(name = "deletion_date")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image","deletionDate"})
    @JoinColumn(name = "id_province", nullable = false)
    @ToString.Exclude
    private Province province;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Location location = (Location) o;
        return idLocation != null && Objects.equals(idLocation, location.idLocation);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
