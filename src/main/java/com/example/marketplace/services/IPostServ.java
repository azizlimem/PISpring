package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.PostLike;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;

public interface IPostServ extends Serializable {
    List<Post> getAllPost();

    Post updatePost (Post c);

    Post addPost(Post c);

    Post getPost(Integer idC);

    void removePost(Integer idC);

    public List<Comment> getAllCommentOfPost(Integer id);
    public int nbPostLike(Integer id);
}
