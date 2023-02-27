package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Market implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idMarket;
    String marketName;
    String immatriculation;
    String marketAddress;
    String phoneNumber;

    @JsonIgnore
    @OneToOne(mappedBy = "market", cascade = CascadeType.ALL)
    private Contract contract;
    @JsonIgnore
    @OneToOne
    private User user;
}
