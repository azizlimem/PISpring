package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FactureCommande {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idFacture;
    float prixFacture;
    @Temporal(TemporalType.DATE)
    Date DateCommande;
    @JsonIgnore
    @OneToOne
    Commande commande;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "facture")
    @JsonIgnore
    Livraison livraison;




}