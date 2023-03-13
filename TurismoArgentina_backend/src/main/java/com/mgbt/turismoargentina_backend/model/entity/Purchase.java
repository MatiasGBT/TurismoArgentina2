package com.mgbt.turismoargentina_backend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","name","lastName","creationDate","deletionDate"})
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image","price",
            "deletionDate","province"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_locations",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_location"))
    List<Location> locations;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler","description","image1","image2",
            "image3","price","duration","deletionDate","location"})
    @JoinTable(
            schema = "turismo_argentina",
            name = "purchases_activities",
            joinColumns = @JoinColumn(name = "id_purchase"),
            inverseJoinColumns = @JoinColumn(name = "id_activity"))
    List<Activity> activities;
}
