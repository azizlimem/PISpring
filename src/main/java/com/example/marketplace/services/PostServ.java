package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;
import com.example.marketplace.repository.IPostRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostServ implements IPostServ{
    final IPostRepo iPostRepo;

    @Override
    public List<Post> getAllPost() {
        List<Post> p = new ArrayList<>();
        iPostRepo.findAll().forEach(p::add);
        return p;
    }

    @Override
    public Post updatePost(Post c) {
        return iPostRepo.save(c);
    }

    @Override
    public Post addPost(Post c) {
        return iPostRepo.save(c);
    }

    @Override
    public Post getPost(Integer idC) {
        return iPostRepo.findById(idC).orElse(null);
    }

    @Override
    public void removePost(Integer idC) {
        iPostRepo.deleteById(idC);
    }

    @Override
    public List<Comment> getAllCommentOfPost(Integer id) {
        return iPostRepo.getAllcomment(id);
    }

    @Override
    public int nbPostLike(Integer id) {
        return iPostRepo.nbPostLike(id);
    }


}
