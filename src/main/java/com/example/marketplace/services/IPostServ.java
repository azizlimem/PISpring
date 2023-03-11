package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public interface IPostServ extends Serializable {
    List<Post> getAllPost();

    Post updatePost (Post c);

    Post addPost(Post c);

    Post getPost(Integer idC);

    void removePost(Integer idC);

    public List<Comment> getAllCommentOfPost(Integer id);
    public int nbPostLikeTotal(Integer id);
    public Post addAndAssignPostToPostUser(Post post, Integer id);
    public void affecterSignal(Integer idP,Integer idU);
    public int NbSignale(Integer id);
    public Post bestPost();
    public ResponseEntity<?> addimagepost(MultipartFile image, Integer idpost) throws IOException;
    public String Statistique();

}
