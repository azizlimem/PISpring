package com.example.marketplace.services;

import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.PostLike;
import com.example.marketplace.entities.User;
import com.example.marketplace.repository.IPostLikeRepo;
import com.example.marketplace.repository.IPostRepo;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostLikeServ implements IPostLikeServ{
    final IPostLikeRepo iPostLikeRepo;
    final IPostRepo iPostRepo;
    final IUserRepository iUserRepository;

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
    public PostLike addAndAssignPostLikeToPostAndUser(PostLike postLike, Integer idP,Integer idU) {
        if (iPostLikeRepo.ReachtIs(idU,idP)==null){
            Post p=iPostRepo.findById(idP).orElse(null);
            User user=iUserRepository.findById(idU).orElse(null);
            postLike.setPost(p);
            postLike.setUser(user);
            return iPostLikeRepo.save(postLike);
        } else if((iPostLikeRepo.ReachtIs(idU,idP).toString().equals(postLike.getReact().toString()))==true){
            System.out.println("kifkif");
           iPostLikeRepo.deleteById(iPostLikeRepo.deletePostLikeeeeBy(idU,idP));
            return null;
        } else if((iPostLikeRepo.ReachtIs(idU,idP).toString().equals(postLike.getReact().toString()))==false){
                iPostLikeRepo.deleteById(iPostLikeRepo.deletePostLikeeeeBy(idU,idP));
                Post p=iPostRepo.findById(idP).orElse(null);
                User user=iUserRepository.findById(idU).orElse(null);
                postLike.setPost(p);
                postLike.setUser(user);
                System.out.println("DISLIKE");
                return iPostLikeRepo.save(postLike);
        }
        return null;
    }
}
