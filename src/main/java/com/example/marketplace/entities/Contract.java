package com.example.marketplace.entities;

import com.example.marketplace.enumerations.PackType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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
    Integer price;
    @Enumerated(EnumType.STRING)
    PackType packType;
    @Temporal(TemporalType.DATE)
    Date dateDebutContract;
    @Temporal(TemporalType.DATE)
    Date dateFinContract;
    @JsonIgnore
    @OneToOne
    private Market market;
}
