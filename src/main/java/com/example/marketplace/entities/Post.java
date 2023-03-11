package com.example.marketplace.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idPost;
    String image;
    String title;
    @Temporal(TemporalType.DATE)
    Date dateCre;
    String Body;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    Set<PostLike> postLikes;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    Set<Comment> Comments;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    Set<User> reported;
}
