package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "catalogue")
public class Catalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCatalogue", nullable = false)
    private Integer idCatalogue;

    @Column(name = "catalogue_name")
    private String catalogueName;

    @JsonIgnore
    @ManyToMany
    private Set<Product> products ;
    @JsonIgnore
    @OneToOne
    Market market;


}