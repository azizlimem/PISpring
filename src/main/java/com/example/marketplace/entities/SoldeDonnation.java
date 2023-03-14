package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SoldeDonnation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private double donnation;
    @JsonIgnore
    Date Datedonnation;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "soldeDonnation")
    Set<Commande> commandes ;
}
