package com.example.marketplace.repository;

import com.example.marketplace.entities.Post;
import com.example.marketplace.enumerations.React;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.marketplace.entities.*;

import java.util.List;

public interface IPostRepo extends CrudRepository<Post,Integer> {
    @Query("select p.Comments from Post p where p.idPost = :id")
    public List<Comment> getAllcomment(@Param("id") Integer id);
    @Query("select count(pl) from Post p inner join p.postLikes pl where p.idPost = :id and  (pl.react = 'LIKE' or pl.react = 'LOVE') ")
    public int nbPostLike(@Param("id") Integer id);



}

