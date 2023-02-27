package com.example.marketplace.entities;

import com.example.marketplace.enumerations.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "taux_remise")
    private Float tauxRemise;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Float price;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_expiration")
    private Date dateExpiration;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Set<Catalogue> catalogues ;
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "products")
    private Set<LigneCommande>  ligneCommandes  ;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    Set<Rating> ratings;

}