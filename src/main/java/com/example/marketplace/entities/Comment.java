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
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idComment;
    String text;
    Date commentDate;
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Post post;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
    Set<CommentLike> commentLikes;



}
