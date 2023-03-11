package com.example.marketplace.services;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Media;
import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.User;
import com.example.marketplace.repository.IPostRepo;
import com.example.marketplace.repository.IUserRepository;
import com.example.marketplace.repository.MediaRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


@Service
@AllArgsConstructor
@Slf4j

public class PostServ implements IPostServ {
    final IPostRepo iPostRepo;
    final IUserRepository iUserRepository;
    final MailerService mailerService;
    @Autowired
    CloudinaryService cloudImage;
    @Autowired
    MediaRepo mediaRepo;
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
        User u = iUserRepository.findById(id).orElse(null);
        LocalDateTime time1 = LocalDateTime.now();
        badWordImpl b = new badWordImpl();
        if (b.filterText(post.getBody()) == true || b.filterText(post.getTitle()) == true) {

            if(u.getBan()==0){
                System.out.println("warning of bad words");
            }
            if(u.getBan()==1){
                System.out.println("you have banned for 1h");
                u.setBanTime(time1.plusHours(1));
            }
            if(u.getBan()==2) {
                System.out.println("you have banned for 24h");
                u.setBanTime(time1.plusDays(1));
            }
            if(u.getBan()==3) {
                System.out.println("you have banned for 1 month");
                u.setBanTime(time1.plusMonths(1));
            }
            if(u.getBan()==4) {
                System.out.println("you have banned for 100 years");
                u.setBanTime(time1.plusYears(99));
            }

            u.setBan(u.getBan()+1);
            iUserRepository.save(u);
            return null;
        }
        post.setUser(u);
        return iPostRepo.save(post);
    }

    @Override
    public int nbPostLikeTotal(Integer id) {
        return iPostRepo.nbPostLikeTotal(id);
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
    public void ArchiverAutomatique() {
        for (Post post : iPostRepo.findAll()) {
            if (post.getReported().size() > 4) {
                post.setArchiver(true);
                iPostRepo.save(post);
            }
        }
    }


  //  @Scheduled(cron = "*/10 * * * * *")
    @Override
    public Post bestPost() {
        int x = 0;
        int totalFinal=0;

        for (Post post : iPostRepo.findAll()) {
            int total=0;
            if(post.isArchiver()==true){
                total=total+(iPostRepo.nbLovePost(post.getIdPost()))*2+(iPostRepo.nbLIKEPost(post.getIdPost()))-(iPostRepo.nbDISLIKEPost(post.getIdPost()))-(iPostRepo.nbDISLIKEPost(post.getIdPost()))*2;
                if (total>totalFinal){
                    totalFinal=total;
                    x = post.getIdPost();
                }
            }

        }
        if (x != 0) {
            Post y = iPostRepo.findById(x).orElse(null);
            Date d = new Date();
            mailerService.sendEmail(y.getUser().getEmail(), "Best Post Winner", " Hello " + y.getUser().getFirstName() + " " + y.getUser().getLastName() + "\n Your Post is the best for week " + d + "\n");
            return y;

        }
        return null;
    }
    @Override
    public String Statistique(){
        int user_nbPost=0,user_nbComment=0;
        String user_BestPost="",user_BestComment="";
        String t;
        for(User u:iUserRepository.findAll()){
            if(iPostRepo.mostUserPost(u.getId())>user_nbPost){
                user_BestPost="";///vider la chaine
                user_nbPost=iPostRepo.mostUserPost(u.getId());///nb Post
                user_BestPost=u.getFirstName()+" "+u.getLastName();///get nom et prenom
            }
            if(iPostRepo.mostUserComment(u.getId())>user_nbComment){
                user_BestComment="";
                user_nbComment=iPostRepo.mostUserComment(u.getId());
                user_BestComment=u.getFirstName()+" "+u.getLastName();
            }

        }
        t="User: "+user_BestPost+" have the most number of posts with "+user_nbPost+"\n"+"User: "+user_BestComment+" have the most number of comments with "+user_nbComment;

        return t;
    }


    public ResponseEntity<?> addimagepost(MultipartFile image, Integer idpost) throws IOException {
        Post p = iPostRepo.findById(idpost).orElse(null);

        BufferedImage bi = ImageIO.read(image.getInputStream());

        Map result = cloudImage.upload(image);

        Media media = new Media((String)
                result.get("original_filename")
                , (String) result.get("url"),
                (String) result.get("public_id"));
        media.setPost(p);
        mediaRepo.save(media);
        return ResponseEntity.status(HttpStatus.OK).body("Image added to post");
    }
}




    

