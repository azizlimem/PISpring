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
        Comment p=iCommentRepo.findById(idC).orElse(null);
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


    @Override
    public String[] CommentaireSuggestion(Integer idPost) {
      String s[];
        LoadData ld=new LoadData();
        int totalFinal = 0;
        String buffer = "";
        Post post = iPostRepo.findById(idPost).orElse(null);
        if (!post.isArchiver()) {
            totalFinal = (iPostRepo.nbLovePost(post.getIdPost())) * 3 + (iPostRepo.nbLIKEPost(post.getIdPost())) - (iPostRepo.nbDISLIKEPost(post.getIdPost())) - (iPostRepo.nbDISLIKEPost(post.getIdPost())) * 2;
            if (totalFinal > 1) {
                buffer="https://docs.google.com/spreadsheets/d/1Yn3XWmbd51KrNLASTYbuJIc6x1c8Z6woKrGjN2XQ2J0/export?format=csv";
            }
            if (totalFinal<=1 && totalFinal>=-1) {
                buffer="https://docs.google.com/spreadsheets/d/1Y_ynzNqb-b4Pp43KXSGOUzgUY3TkZ5NMeHwzFgpVrdY/export?format=csv";
            }
            if(totalFinal<-1){
                buffer="https://docs.google.com/spreadsheets/d/1qianFqbOhm8Ewy7IDMdLFUydXzvMvIUObYd0iWftIeM/export?format=csv";
            }
        } else {
            return null;
        }
      s=ld.loadFromURL(buffer);
        return s;
    }


}
