package com.example.marketplace.entities;

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
public class Commande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idCommande;
    Long NumCommande;
    @Temporal(TemporalType.DATE)
    Date DateCommande;
    String AdressCommande;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    Set<LigneCommande> ligneCommandes;
    @JsonIgnore
    @OneToOne(mappedBy = "commande")
    FactureCommande factureCommandes;
    @JsonIgnore
    @OneToOne(mappedBy = "commande")
    Charity charity;


}

