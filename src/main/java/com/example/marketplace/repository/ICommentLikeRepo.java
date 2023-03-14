package com.example.marketplace.repository;

import com.example.marketplace.entities.CommentLike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ICommentLikeRepo extends CrudRepository<CommentLike,Integer> {
    @Query("select pl.react from CommentLike pl inner join pl.user plu inner join pl.comment plp  where plu.id=:idUser and plp.idComment=:idPost")
    public String ReachtIs(Integer idUser, Integer idPost);

    @Query("select pl.idCommentLike from CommentLike pl inner join pl.user plu inner join pl.comment plp  where plu.id=:idUser and plp.idComment=:idPost")
    public Integer deleteCommentLikeeeeBy(Integer idUser, Integer idPost);
}
