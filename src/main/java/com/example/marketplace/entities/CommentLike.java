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
public class CommentLike {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    Integer idCommentLike;
    @Enumerated(EnumType.STRING)
    React react;
    @JsonIgnore
    LocalDateTime CommentLikeDate=LocalDateTime.now();
    @JsonIgnore
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Comment comment;
}
