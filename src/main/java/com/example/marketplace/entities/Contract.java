package com.example.marketplace.entities;

import com.example.marketplace.enumerations.PackType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idContract;
    @JsonIgnore
    Integer price;
    Integer nbrMonths;
    @JsonIgnore
    Integer discount;
    @JsonIgnore
    Boolean paid;
    @JsonIgnore
    @Enumerated(EnumType.STRING)
    PackType packType;
    @JsonIgnore
    LocalDateTime dateDebutContract;
    @JsonIgnore
    LocalDateTime dateFinContract;
    @JsonIgnore
    @OneToOne
    private Market market;
}
