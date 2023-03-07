package com.example.marketplace.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Integer id;

    @NonNull
    private String name;
    // ManyToMany Relations
    @OneToMany(cascade = CascadeType.ALL, mappedBy="role")
    private Set<User> users;

}
