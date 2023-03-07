package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.CommentLike;

import java.io.Serializable;
import java.util.List;

public interface ICommentServ extends Serializable {
    List<Comment> getAllComment();

    Comment updateComment (Comment c);

    Comment addComment (Comment c);

    Comment getOneComment(Integer idC);

    void removeComment(Integer idC);
    public Comment addAndAssignCommentToPostUser(Comment comment, Integer idPost,Integer iduser);
    public int nbCommentLike(Integer id);

}
