package com.mgbt.turismoargentina_backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "purchases", schema="turismo_argentina")
public class Purchase implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_purchase")
    private Long idPurchase;

    @Column(name = "date")
    @Temporal(value = TemporalType.TIMESTAMP)
    @NotNull(message = "Date is mandatory")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","name","lastName","creationDate","deletionDate"})
    @JoinColumn(name = "id_user", nullable = false)
    @NotNull(message = "User is mandatory")
    @ToString.Exclude
    private User user;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","price",
            "deletionDate","province"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_locations",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_location"))
    @ToString.Exclude
    List<Location> locations;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image2",
            "image3","price","duration","deletionDate","location"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_activities",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_activity"))
    @ToString.Exclude
    List<Activity> activities;

    @Column(name = "price")
    @NotNull(message = "Price is mandatory")
    private Double price;

    @Column(name="refunded", columnDefinition = "BOOLEAN", nullable = false)
    Boolean isRefunded;

    @PrePersist
    public void setUp() {
        this.date = new Date();
        this.isRefunded = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Purchase purchase = (Purchase) o;
        return idPurchase != null && Objects.equals(idPurchase, purchase.idPurchase);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
