package com.mgbt.turismoargentina_backend.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.*;
import java.util.*;

@Data
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
    private User user;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","price",
            "deletionDate","province"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_locations",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_location"))
    List<Location> locations;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image2",
            "image3","price","duration","deletionDate","location"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_activities",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_activity"))
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
}
