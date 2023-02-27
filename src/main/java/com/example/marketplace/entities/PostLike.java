package com.example.marketplace.entities;


import com.example.marketplace.enumerations.React;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idLikePost;
    @Enumerated(EnumType.STRING)
    React react;
    Date LikePostDate;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Post post;
}
