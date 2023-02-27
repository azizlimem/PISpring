package com.example.marketplace.services;

import com.example.marketplace.entities.CommentLike;
import com.example.marketplace.entities.PostLike;

import java.io.Serializable;
import java.util.List;

public interface ICommentLikeServ extends Serializable {
    List<CommentLike> getAllCommentLike();

    CommentLike updateCommentLike (CommentLike c);

    CommentLike addCommentLike (CommentLike c);

    CommentLike getOneCommentLike(Integer idC);

    void removeCommentLike(Integer idC);
    public CommentLike addAndAssignCommentLikeToComment(CommentLike commentLike, Integer id);
}
