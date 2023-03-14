package com.example.marketplace.services;

import com.example.marketplace.entities.*;
import com.example.marketplace.repository.ICommentLikeRepo;
import com.example.marketplace.repository.ICommentRepo;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentLikeServ implements ICommentLikeServ{
    final ICommentLikeRepo iCommentLikeRepo;
    final ICommentRepo iCommentRepo;
    final IUserRepository iUserRepository;

    @Override
    public List<CommentLike> getAllCommentLike() {
        List<CommentLike> Comm = new ArrayList<>();
        iCommentLikeRepo.findAll().forEach(Comm::add);
        return Comm;
    }

    @Override
    public CommentLike updateCommentLike(CommentLike c) {
        return iCommentLikeRepo.save(c);
    }

    @Override
    public CommentLike addCommentLike(CommentLike c) {
        return iCommentLikeRepo.save(c);
    }

    @Override
    public CommentLike getOneCommentLike(Integer idC) {
        return iCommentLikeRepo.findById(idC).orElse(null);
    }

    @Override
    public void removeCommentLike(Integer idC) {
        iCommentLikeRepo.deleteById(idC);
    }


    @Override
    public CommentLike addAndAssignCommentLikeToComment(CommentLike postLike, Integer idP, Integer idU) {
        if (iCommentLikeRepo.ReachtIs(idU,idP)==null){
            Comment p=iCommentRepo.findById(idP).orElse(null);
            User user=iUserRepository.findById(idU).orElse(null);
            postLike.setComment(p);
            postLike.setUser(user);
            return iCommentLikeRepo.save(postLike);
        } else if((iCommentLikeRepo.ReachtIs(idU,idP).toString().equals(postLike.getReact().toString()))==true){
            System.out.println("kifkif");
            iCommentLikeRepo.deleteById(iCommentLikeRepo.deleteCommentLikeeeeBy(idU,idP));
            return null;
        } else if((iCommentLikeRepo.ReachtIs(idU,idP).toString().equals(postLike.getReact().toString()))==false){
            iCommentLikeRepo.deleteById(iCommentLikeRepo.deleteCommentLikeeeeBy(idU,idP));
            Comment p=iCommentRepo.findById(idP).orElse(null);
            User user=iUserRepository.findById(idU).orElse(null);
            postLike.setComment(p);
            postLike.setUser(user);
            System.out.println("DISLIKE");
            return iCommentLikeRepo.save(postLike);
        }
        return null;
    }
}
