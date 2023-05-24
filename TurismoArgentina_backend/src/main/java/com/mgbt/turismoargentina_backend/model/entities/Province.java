package com.mgbt.turismoargentina_backend.model.entities;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Province province = (Province) o;
        return idProvince != null && Objects.equals(idProvince, province.idProvince);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
