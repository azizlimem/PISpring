package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.CommentLike;
import com.example.marketplace.entities.Post;
import com.example.marketplace.repository.ICommentLikeRepo;
import com.example.marketplace.repository.ICommentRepo;
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
    public CommentLike addAndAssignCommentLikeToComment(CommentLike commentLike, Integer id) {
        Comment c=iCommentRepo.findById(id).orElse(null);
        commentLike.setComment(c);
        return iCommentLikeRepo.save(commentLike);
    }
}
