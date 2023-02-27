package com.example.marketplace.entities;

import com.example.marketplace.enumerations.Type_livraison;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Livraison {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLivraison;
    @NotNull
    String nomdestinataire;
    @NotNull
    String prenomdestinataire;
    @NotNull
    String adresse;
    @NotNull
    Integer numerotel;
    @NotNull
    String mail;
    Integer idlivreur;
    @Temporal(TemporalType.DATE)
    private Date dateLivraison;
    float latitude;
    float longitude;
    @Enumerated(EnumType.STRING)
    Type_livraison typelivraison;

    @ManyToOne
    @JsonIgnore
    Livreur livreur ;

    @OneToOne
    @JsonIgnore
    FactureCommande facture;

}
