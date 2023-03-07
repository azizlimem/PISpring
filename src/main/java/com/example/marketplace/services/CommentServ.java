package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.User;
import com.example.marketplace.repository.ICommentRepo;
import com.example.marketplace.repository.IPostRepo;
import com.example.marketplace.repository.IUserRepository;
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
    final IPostRepo iPostRepo;
    final IUserRepository iUserRepository;


    @Override
    public List<Comment> getAllComment() {
        List<Comment> Comm = new ArrayList<>();
        iCommentRepo.findAll().forEach(Comm::add);
        return Comm;
    }
    @Override
    public Comment updateComment(Comment c) {
        return iCommentRepo.save(c);
    }

    @Override
    public Comment addComment(Comment c) {
        return iCommentRepo.save(c);
    }

    @Override
    public Comment getOneComment(Integer idC) {
        return iCommentRepo.findById(idC).orElse(null);
    }

    @Override
    public void removeComment(Integer idC) {
        iCommentRepo.deleteById(idC);
    }

    @Override
    public Comment addAndAssignCommentToPostUser(Comment comment, Integer idPost,Integer iduser) {
        Post p=iPostRepo.findById(idPost).orElse(null);
        User user=iUserRepository.findById(iduser).orElse(null);
        comment.setPost(p);
        comment.setUser(user);
        return iCommentRepo.save(comment);
    }

    @Override
    public int nbCommentLike(Integer id) {
        if(iCommentRepo.nbCommentLike(id)-iCommentRepo.nbNULLComment(id)<0){
            return 0;
        }
        else {
            return iCommentRepo.nbCommentLike(id)-iCommentRepo.nbNULLComment(id);
        }

    }
}
