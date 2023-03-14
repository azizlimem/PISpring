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
public class Post {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @JsonIgnore
    Integer idPost;
    String title;
    @JsonIgnore
    LocalDateTime dateCre=LocalDateTime.now();
    @JsonIgnore
    boolean archiver;
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
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Set<User> reported;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "post")
    Image image;
}
