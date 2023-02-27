package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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
    @JsonIgnore
    @OneToOne
    Commande commande;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "facture")
    @JsonIgnore
    Livraison livraison;




}