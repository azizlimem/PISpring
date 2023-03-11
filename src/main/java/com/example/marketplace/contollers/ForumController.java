package com.example.marketplace.contollers;

import com.example.marketplace.entities.*;
import com.example.marketplace.services.ICommentLikeServ;
import com.example.marketplace.services.ICommentServ;
import com.example.marketplace.services.IPostLikeServ;
import com.example.marketplace.services.IPostServ;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Forum")
public class ForumController {
    final ICommentServ iCommentServ;
    final ICommentLikeServ iCommentLikeServ;
    final IPostServ iPostServ;
    final IPostLikeServ iPostLikeServ;

    ////////////////////////////post///////////////////
    @GetMapping("/allPost")
    List<Post> getAllPost() {
        return iPostServ.getAllPost();
    }

    @PostMapping("/addPost")
    Post addPost(@RequestBody Post c) {
        return iPostServ.addPost(c);
    }

    @PutMapping("/updatePost")
    Post updatePost(@RequestBody Post c) {
        return iPostServ.updatePost(c);
    }

    @GetMapping("/getPost/{id}")
    Post getPost(@PathVariable("id") Integer id) {
        return iPostServ.getPost(id);
    }

    @DeleteMapping("/deletePost/{id}")
    void deletePost(@PathVariable("id") Integer id) {
        iPostServ.removePost(id);
    }

    ///////////////////////////PostLike///////////////
    @GetMapping("/allPostLike")
    List<PostLike> getAllPostLike() {
        return iPostLikeServ.getAllPostLike();
    }

    @PostMapping("/addPostLike")
    PostLike addPostLike(@RequestBody PostLike c) {
        return iPostLikeServ.addPostLike(c);
    }

    @PutMapping("/updatePostLike")
    PostLike updatePostLike(@RequestBody PostLike c) {
        return iPostLikeServ.updatePostLike(c);
    }

    @GetMapping("/getPostLike/{id}")
    PostLike getPostLike(@PathVariable("id") Integer id) {
        return iPostLikeServ.getPostLike(id);
    }

    @DeleteMapping("/deletePostLike/{id}")
    void deletePostLike(@PathVariable("id") Integer id) {
        iPostLikeServ.removePostLike(id);
    }

    /////////////////////////Comment/////////////////////////
    @GetMapping("/allComment")
    List<Comment> getAllComment() {
        return iCommentServ.getAllComment();
    }

    @PostMapping("/addComment")
    Comment addComment(@RequestBody Comment c) {
        return iCommentServ.addComment(c);
    }

    @PutMapping("/updateComment")
    Comment updateComment(@RequestBody Comment c) {
        return iCommentServ.updateComment(c);
    }

    @GetMapping("/getComment/{id}")
    Comment getComment(@PathVariable("id") Integer id) {
        return iCommentServ.getOneComment(id);
    }

    @DeleteMapping("/deleteComment/{id}")
    void deleteComment(@PathVariable("id") Integer id) {
        iCommentServ.removeComment(id);
    }

    /////////////////CommentLike/////////////
    @GetMapping("/allCommentLike")
    List<CommentLike> getAllCommentLike() {
        return iCommentLikeServ.getAllCommentLike();
    }

    @PostMapping("/addCommentLike")
    CommentLike addCommentLike(@RequestBody CommentLike c) {
        return iCommentLikeServ.addCommentLike(c);
    }
    @PostMapping("/addImageToPost/{idpost}")
    @ResponseBody
    public ResponseEntity<?> addpostimage(@RequestParam MultipartFile image, @PathVariable("idpost") Integer idpost) throws IOException {
        return iPostServ.addimagepost(image,idpost);

    }
    @PutMapping("/updateCommentLike")
    CommentLike updateCommentLike(@RequestBody CommentLike c) {
        return iCommentLikeServ.updateCommentLike(c);
    }

    @GetMapping("/getCommentLike/{id}")
    CommentLike getCommentLike(@PathVariable("id") Integer id) {
        return iCommentLikeServ.getOneCommentLike(id);
    }

    @DeleteMapping("/deleteCommentLike/{id}")
    void deleteCommentLike(@PathVariable("id") Integer id) {
        iCommentLikeServ.removeCommentLike(id);
    }

    ///////////////////////////Affecter///////////////////////////////
    @PutMapping("/addAndAssignPostLiketoPostAndUser/{idPost}/{idUser}")
    public PostLike addAndAssignPostLiketoPostAndUser(@RequestBody PostLike postLike, @PathVariable("idPost") Integer IdPost, @PathVariable("idUser") Integer idU) {
        return iPostLikeServ.addAndAssignPostLikeToPostAndUser(postLike,IdPost,idU);
    }

    @PutMapping("/AssignCommentLiketoComment/{idComment}")
    public CommentLike addAndAssignCommentLiketoComment(@RequestBody CommentLike commentLike, @PathVariable("idComment") Integer idComment) {
        return iCommentLikeServ.addAndAssignCommentLikeToComment(commentLike, idComment);
    }
    @PutMapping("/addAndAssignCommenttoPostAndUser/{idPost}/{idUser}")
    public Comment addAndAssignCommenttoPostAndUser(@RequestBody Comment comment, @PathVariable("idPost") Integer IdPost, @PathVariable("idUser") Integer IdUser) {
        return iCommentServ.addAndAssignCommentToPostUser(comment, IdPost,IdUser);
    }
    @PutMapping("/addAndAssignPostToUser/{idPost}/{idUser}")
    public Post addAndAssignPostToUser(@RequestBody Post post,@PathVariable("idUser") Integer idUser) {
        return iPostServ.addAndAssignPostToPostUser(post,idUser);
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
    @PutMapping("/SignalerPost/{idP}/{idU}")
    public void SignalerPost(@PathVariable("idP") Integer idP,@PathVariable("idU") Integer idU) {
         iPostServ.affecterSignal(idP,idU);
    }
    @GetMapping("/NbSignale/{id}")
    public int NBtest(@PathVariable("id") Integer id) {
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
}
