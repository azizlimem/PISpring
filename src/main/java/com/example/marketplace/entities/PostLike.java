package com.example.marketplace.entities;


import com.example.marketplace.enumerations.React;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    Integer idLikePost;
    @Enumerated(EnumType.STRING)
    React react;
    @JsonIgnore
    LocalDateTime LikePostDate=LocalDateTime.now();
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Post post;
}
