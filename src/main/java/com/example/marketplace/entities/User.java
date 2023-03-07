package com.example.marketplace.entities;

import com.example.marketplace.enumerations.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idUser;
    String nomUser;
    String prenomUser;
    String username;
    String password;
    String emailUser;
    @Enumerated(EnumType.STRING)
    UserRole role;
    String cinUser;
    String phoneNumber;
    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Market market;
    ////////////////FRRUM///////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<Comment> comments;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<PostLike> postLikes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<Post> posts;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<CommentLike> commentLikes;
    ////////////////Product///////////////////
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    Set<Rating> ratings;
    ///////////Panier/////////////
    @JsonIgnore
    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Panier panier;
//////reclamation//////
@OneToMany(mappedBy="userrr",cascade = CascadeType.ALL)
@JsonIgnore
                Set<Reclamation> userrec;
////intervention /////
@OneToMany(mappedBy="userrrr",cascade = CascadeType.ALL)
@JsonIgnore
                Set<Intervention> interuser;

///Livreur/////
    @OneToOne( cascade = CascadeType.ALL)
    @JsonIgnore
    private Livreur livreur;
}
