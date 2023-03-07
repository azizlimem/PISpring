package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(value = AccessLevel.NONE)
    private Integer id;

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String username;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @JsonIgnore
    @ManyToOne
    Role role;
    String cinUser;
    String phoneNumber;
    @JsonIgnore
    String status;
    @JsonIgnore
    String photo;
    @JsonIgnore
    LocalDateTime createdAt;
    /////////////////////MARKET///////////////
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
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "reported")
    Set<Post> PostsSignale;
    ////////////////Product///////////////////

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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private Livreur livreur;

    @JsonIgnore
    @ManyToMany(mappedBy = "users" ,cascade = CascadeType.ALL)
    Set<Rating> ratings;



}
