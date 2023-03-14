package com.example.marketplace.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer idComment;
    String text;
    @JsonIgnore
    LocalDateTime commentDate=LocalDateTime.now();
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
