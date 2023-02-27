package com.example.marketplace.services;

import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.PostLike;
import com.example.marketplace.repository.IPostLikeRepo;
import com.example.marketplace.repository.IPostRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostLikeServ implements IPostLikeServ{
    final IPostLikeRepo iPostLikeRepo;
    final IPostRepo iPostRepo;

    @Override
    public List<PostLike> getAllPostLike() {
        List<PostLike> p = new ArrayList<>();
        iPostLikeRepo.findAll().forEach(p::add);
        return p;
    }

    @Override
    public PostLike updatePostLike(PostLike c) {
        return iPostLikeRepo.save(c);
    }

    @Override
    public PostLike addPostLike(PostLike c) {
        return iPostLikeRepo.save(c);
    }

    @Override
    public PostLike getPostLike(Integer idC) {
        return iPostLikeRepo.findById(idC).orElse(null);
    }

    @Override
    public void removePostLike(Integer idC) {
        iPostLikeRepo.deleteById(idC);

    }

    @Override
    public PostLike addAndAssignPostLikeToPost(PostLike postLike, Integer id) {
        Post p=iPostRepo.findById(id).orElse(null);
        postLike.setPost(p);
        return iPostLikeRepo.save(postLike);
    }
}
