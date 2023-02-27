package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.PostLike;

import java.io.Serializable;
import java.util.List;

public interface IPostLikeServ extends Serializable {
    List<PostLike> getAllPostLike();

    PostLike updatePostLike (PostLike c);

    PostLike addPostLike (PostLike c);

    PostLike getPostLike(Integer idC);

    void removePostLike(Integer idC);

    public PostLike addAndAssignPostLikeToPost(PostLike postLike, Integer id);
}
