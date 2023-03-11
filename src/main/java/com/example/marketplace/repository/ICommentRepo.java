package com.example.marketplace.repository;

import com.example.marketplace.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ICommentRepo extends CrudRepository<Comment,Integer> {
    @Query("select count(pl) from Comment p inner join p.commentLikes pl where p.idComment = :id ")
    public int nbCommentLike(@Param("id") Integer id);
    @Query("select count(pl) from Comment p inner join p.commentLikes pl where  pl.react ='NULL'")
    public int nbNULLComment(@Param("id") Integer id);
}
