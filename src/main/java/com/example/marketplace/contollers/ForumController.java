package com.example.marketplace.contollers;

import com.example.marketplace.Playload.Response.MessageResponse;
import com.example.marketplace.entities.*;
import com.example.marketplace.repository.IUserRepository;
import com.example.marketplace.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Forum")
public class ForumController {
    final ICommentServ iCommentServ;
    final ICommentLikeServ iCommentLikeServ;
    final IPostServ iPostServ;
    final IPostLikeServ iPostLikeServ;
    final IimageSer iimageSer;
    final IUserRepository userRepository;

    ////////////////////////////post///////////////////
    @GetMapping("/allPost")
    List<Post> getAllPost() {
        return iPostServ.getAllPost();
    }

//    @PostMapping("/addPost")
//    Post addPost(@RequestBody Post c) {
//        return iPostServ.addPost(c);
//    }


    @PutMapping("/updatePost")
    Post updatePost(@RequestBody Post c) {
        return iPostServ.updatePost(c);
    }

//    @GetMapping("/getPost/{id}")
//    Post getPost(@PathVariable("id") Integer id) {
//        return iPostServ.getPost(id);
//    }

    @DeleteMapping("/deletePost/{id}")
    void deletePost(@PathVariable("id") Integer id) {
        iPostServ.removePost(id);
    }

    ///////////////////////////PostLike///////////////
    @GetMapping("/allPostLike")
    List<PostLike> getAllPostLike() {
        return iPostLikeServ.getAllPostLike();
    }

//    @PostMapping("/addPostLike")
//    PostLike addPostLike(@RequestBody PostLike c) {
//        return iPostLikeServ.addPostLike(c);
//    }

    @PutMapping("/updatePostLike")
    PostLike updatePostLike(@RequestBody PostLike c) {
        return iPostLikeServ.updatePostLike(c);
    }

//    @GetMapping("/getPostLike/{id}")
//    PostLike getPostLike(@PathVariable("id") Integer id) {
//        return iPostLikeServ.getPostLike(id);
//    }

//    @DeleteMapping("/deletePostLike/{id}")
//    void deletePostLike(@PathVariable("id") Integer id) {
//        iPostLikeServ.removePostLike(id);
//    }

    /////////////////////////Comment/////////////////////////
    @GetMapping("/allComment")
    List<Comment> getAllComment() {
        return iCommentServ.getAllComment();
    }

//    @PostMapping("/addComment")
//    Comment addComment(@RequestBody Comment c) {
//        return iCommentServ.addComment(c);
//    }

    @PutMapping("/updateComment")
    Comment updateComment(@RequestBody Comment c) {
        return iCommentServ.updateComment(c);
    }

//    @GetMapping("/getComment/{id}")
//    Comment getComment(@PathVariable("id") Integer id) {
//        return iCommentServ.getOneComment(id);
//    }

    @DeleteMapping("/deleteComment/{id}")
    void deleteComment(@PathVariable("id") Integer id) {
        iCommentServ.removeComment(id);
    }

    /////////////////CommentLike/////////////
    @GetMapping("/allCommentLike")
    List<CommentLike> getAllCommentLike() {
        return iCommentLikeServ.getAllCommentLike();
    }

//    @PostMapping("/addCommentLike")
//    CommentLike addCommentLike(@RequestBody CommentLike c) {
//        return iCommentLikeServ.addCommentLike(c);
//    }
//
//    @PutMapping("/updateCommentLike")
//    CommentLike updateCommentLike(@RequestBody CommentLike c) {
//        return iCommentLikeServ.updateCommentLike(c);
//    }
//
//    @GetMapping("/getCommentLike/{id}")
//    CommentLike getCommentLike(@PathVariable("id") Integer id) {
//        return iCommentLikeServ.getOneCommentLike(id);
//    }
//
//    @DeleteMapping("/deleteCommentLike/{id}")
//    void deleteCommentLike(@PathVariable("id") Integer id) {
//        iCommentLikeServ.removeCommentLike(id);
//    }

    ///////////////////////////Affecter///////////////////////////////
    @PutMapping("/addAndAssignPostLiketoPostAndUser/{idPost}")
    public ResponseEntity<?> addAndAssignPostLiketoPostAndUser(@RequestBody PostLike postLike, @PathVariable("idPost") Integer IdPost,Principal principal)  {
        try {
            String username = principal.getName();
            User u = userRepository.findByUsername(username).orElse(null);
            Integer id = u.getId();

            return ResponseEntity.ok(new MessageResponse(iPostLikeServ.addAndAssignPostLikeToPostAndUser(postLike, IdPost, id)));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }

    }

    @PutMapping("/AssignCommentLiketoCommentand/{idComment}")
    public ResponseEntity<?> addAndAssignCommentLiketoComment(@RequestBody CommentLike commentLike, @PathVariable("idComment") Integer idComment,Principal principal) {
        try {

            String username = principal.getName();
            User u = userRepository.findByUsername(username).orElse(null);
            Integer id = u.getId();
            iCommentLikeServ.addAndAssignCommentLikeToComment(commentLike, idComment,id);
            return ResponseEntity.ok(new MessageResponse("React is added successfully!"));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }

    }
    @PutMapping("/addAndAssignCommenttoPostAndUser/{idPost}/{idUser}")
    public ResponseEntity<?> addAndAssignCommenttoPostAndUser(@RequestBody Comment comment, @PathVariable("idPost") Integer IdPost,Principal principal){
     try{


        String username = principal.getName();
        User u = userRepository.findByUsername(username).orElse(null);
        Integer id = u.getId();
        iCommentServ.addAndAssignCommentToPostUser(comment, IdPost,id);
        return ResponseEntity.ok(new MessageResponse("React is added successfully!"));
    }catch (Exception e){
        return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
    }
    }

    @PutMapping("/addAndAssignPostToUser")
    public ResponseEntity<?> addAndAssignPostToUser(@RequestBody Post post,Principal principal) {
        try {
            String username = principal.getName();
            User u = userRepository.findByUsername(username).orElse(null);
            Integer id = u.getId();
            return ResponseEntity.ok(new MessageResponse(iPostServ.addAndAssignPostToPostUser(post, id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Please Signin or Signup"));
        }
    }
    ////////////////////////////QUERY/////////////////////////////////////
    @GetMapping("/getAllCommentOfPost/{id}")
    public List<Comment> getAllCommentOfPost(@PathVariable("id") Integer id) {
        return iPostServ.getAllCommentOfPost(id);
    }
    @GetMapping("/NbPostLikeTotal/{id}")
    public int nbPostLikeToatal(@PathVariable("id") Integer id) {
        return iPostServ.nbPostLikeTotal(id);
    }
    @GetMapping("/NbCommentLike/{id}")
    public int nbCommentLike(@PathVariable("id") Integer id) {
        return iCommentServ.nbCommentLike(id);
    }
    @PutMapping("/SignalerPost/{idP}")
    public ResponseEntity<?> SignalerPost(@PathVariable("idP") Integer idP,Principal principal) {
        try {
            String username = principal.getName();
            User u = userRepository.findByUsername(username).orElse(null);
            Integer id = u.getId();
            iPostServ.affecterSignal(idP,id);
            return ResponseEntity.ok(new MessageResponse("signal is added successfully!"));
        }catch ( Exception e){
            return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
        }
    }
    @GetMapping("/NbSignale/{id}")
    public int NBSignale(@PathVariable("id") Integer id) {
        return iPostServ.NbSignale(id);
    }

    @GetMapping("/bestPost")
    public Post bestPost() {
        return iPostServ.bestPost();
    }
    @GetMapping("/Statistique")
    public String Statistique() {
        return iPostServ.Statistique();
    }

    @GetMapping("/Sugg/{id}")
    public String[] SuggForPost(@PathVariable("id") Integer id) {
        return iCommentServ.CommentaireSuggestion(id);
    }

    @PutMapping("/addImageAndAssigToPost/{idPost}")
    public ResponseEntity<?> addAndAssignPostToUser(@RequestBody MultipartFile image,@PathVariable("idPost") Integer idP) throws IOException {
        try{iimageSer.AddandAssig(image,idP);
        return ResponseEntity.ok(new MessageResponse("image is added successfully!"));
    }catch ( Exception e){
        return ResponseEntity.badRequest().body(new MessageResponse("Erreur"));
    }
    }
    @GetMapping("/getALLImage")
    public List<Image> list() {
        return iimageSer.list();
    }




}