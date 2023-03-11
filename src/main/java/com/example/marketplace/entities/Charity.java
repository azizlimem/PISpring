package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "charity")
public class Charity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String sosName;
    private String sosAddress;
    private String sosPhone;
    @JsonIgnore
    private Integer score;
    private Integer nbre;
    private Boolean maladie;
    private Boolean CasGrave;
    @JsonIgnore
    @OneToOne
    Commande commande;

}
