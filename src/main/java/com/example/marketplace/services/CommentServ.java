package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.repository.ICommentRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServ implements ICommentServ{
    final ICommentRepo iCommentRepo;


    @Override
    public List<Comment> getAllComment() {
        List<Comment> Comm = new ArrayList<>();
        iCommentRepo.findAll().forEach(Comm::add);
        return Comm;
    }
}
