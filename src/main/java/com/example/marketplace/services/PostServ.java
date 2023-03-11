package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.User;
import com.example.marketplace.repository.IPostRepo;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor
@Slf4j

public class PostServ implements IPostServ {
    final IPostRepo iPostRepo;
    final IUserRepository iUserRepository;
    final MailerService mailerService;

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
    public Post addAndAssignPostToPostUser(Post post, Integer id) {
        badWordImpl b = new badWordImpl();
        if (b.filterText(post.getBody()) == true || b.filterText(post.getTitle()) == true) {
            return null;
        }
        User u = iUserRepository.findById(id).orElse(null);
        post.setUser(u);
        return iPostRepo.save(post);
    }

    @Override
    public int nbPostLike(Integer id) {
        return iPostRepo.nbPostLike(id);
    }

    @Override
    public void affecterSignal(Integer idP, Integer idU) {
        User u = iUserRepository.findById(idU).orElse(null);
        Post p = iPostRepo.findById(idP).orElse(null);
        p.getReported().add(u);
        iPostRepo.save(p);
    }

    @Override
    public int NbSignale(Integer id) {
        int x = 0;
        Post p = iPostRepo.findById(id).get();
        if (p == null) {
            return x;
        }
        for (int i = 0; i < p.getReported().size(); i++) {
            x++;
        }
        return x;
    }

 //   @Scheduled(cron = "*/10 * * * * *")
    public void deleteAutomatique() {
        for (Post post : iPostRepo.findAll()) {
            if (post.getReported().size() > 3) {
                iPostRepo.deleteById(post.getIdPost());
            }
        }
    }
    @Override
    public Post bestPost() {
        int x = 0;
        for (Post post : iPostRepo.findAll()) {
            if (iPostRepo.nbPostLike(post.getIdPost()) > x){
                x=post.getIdPost();
            }
        }
        if(x!=0){
            Post y=iPostRepo.findById(x).orElse(null);
            Date d=new Date();
            mailerService.sendEmail(y.getUser().getEmail(),"Best Post Winner"," Hello "+y.getUser().getFirstName()+" "+y.getUser().getLastName()+"\n Your Post is the best for week "+d+"\n");
            return y;

        }
        return null;
    }
}




    

