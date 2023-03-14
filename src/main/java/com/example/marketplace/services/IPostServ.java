package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;

import java.io.Serializable;
import java.util.List;

public interface IPostServ extends Serializable {
    List<Post> getAllPost();

    Post updatePost (Post c);

    Post addPost(Post c);

    Post getPost(Integer idC);

    void removePost(Integer idC);

    List<Comment> getAllCommentOfPost(Integer id);
     int nbPostLikeTotal(Integer id);
     String addAndAssignPostToPostUser(Post post, Integer id);
     void affecterSignal(Integer idP,Integer idU);
     int NbSignale(Integer id);
     Post bestPost();
     String Statistique();
    String ArchiverAutomatique();

}
