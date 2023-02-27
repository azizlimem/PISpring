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
public class CommentLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idPostLike;
    @Enumerated(EnumType.STRING)
    React react;
    Date CommentLikeDate;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Comment comment;
}
