package com.example.marketplace.repository;
import com.example.marketplace.entities.PostLike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IPostLikeRepo extends CrudRepository<PostLike,Integer> {
    @Query("select pl.react from PostLike pl inner join pl.user plu inner join pl.post plp  where plu.id=:idUser and plp.idPost=:idPost")
    public String ReachtIs(Integer idUser, Integer idPost);

    @Query("select pl.idLikePost from PostLike pl inner join pl.user plu inner join pl.post plp  where plu.id=:idUser and plp.idPost=:idPost")
    public Integer deletePostLikeeeeBy(Integer idUser, Integer idPost);
}