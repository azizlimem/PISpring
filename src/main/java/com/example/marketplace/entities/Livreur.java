package com.example.marketplace.entities;

import com.example.marketplace.enumerations.Type_livraison;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import net.bytebuddy.build.ToStringPlugin;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Livreur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idLivreur;
    @Temporal(TemporalType.DATE)
    private Date datedembauche;
    float salaire;
    float bonus;
    float penaliser;
    float latitude;
    float longitude;
    Integer Nbrelivraison;
    @Enumerated(EnumType.STRING)
    Type_livraison typelivraison;

    @OneToMany(mappedBy="livreur")
    @JsonIgnore
    private List<Livraison> livraisons ;

    @OneToOne(mappedBy = "livreur")
    @JsonIgnore
    private User user;



}
