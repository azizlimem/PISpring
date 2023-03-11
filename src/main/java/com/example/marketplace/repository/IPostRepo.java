package com.example.marketplace.repository;

import com.example.marketplace.entities.Post;
import com.example.marketplace.enumerations.React;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.marketplace.entities.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPostRepo extends CrudRepository<Post,Integer> {
    @Query("select p.Comments from Post p where p.idPost = :id")
    public List<Comment> getAllcomment(@Param("id") Integer id);
    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id ")
    public int nbPostLikeTotal(@Param("id") Integer id);

    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id and  pl.react = 'LOVE'")
    public int nbLovePost(@Param("id") Integer id);

    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id and  pl.react = 'LIKE'")
    public int nbLIKEPost(@Param("id") Integer id);

    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id and  pl.react = 'DISLIKE'")
    public int nbDISLIKEPost(@Param("id") Integer id);
    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id and  pl.react = 'ANGRY'")
    public int nbANGRYPost(@Param("id") Integer id);

    @Query("select count (up) from User u inner join u.posts up where u.id = :id")
    public int mostUserPost(@Param("id")Integer id);

    @Query("select count (uc) from User u inner join u.comments uc where u.id = :id")
    public int mostUserComment(@Param("id")Integer id);
}

