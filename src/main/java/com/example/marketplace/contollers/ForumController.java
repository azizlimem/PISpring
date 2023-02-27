package com.example.marketplace.contollers;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.CommentLike;
import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.PostLike;
import com.example.marketplace.services.ICommentLikeServ;
import com.example.marketplace.services.ICommentServ;
import com.example.marketplace.services.IPostLikeServ;
import com.example.marketplace.services.IPostServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/AssignPostLiketoPost/{idPost}")
    public PostLike addAndAssignPostLiketoPost(@RequestBody PostLike postLike, @PathVariable("idPost") Integer IdPost) {
        return iPostLikeServ.addAndAssignPostLikeToPost(postLike, IdPost);
    }

    @PutMapping("/AssignCommentLiketoComment/{idComment}")
    public CommentLike addAndAssignPostLiketoPost(@RequestBody CommentLike commentLike, @PathVariable("idComment") Integer idComment) {
        return iCommentLikeServ.addAndAssignCommentLikeToComment(commentLike, idComment);
    }
    @PutMapping("/AssignCommentCommentToPost/{idPost}")
    public Comment addAndAssignPostLiketoPost(@RequestBody Comment comment, @PathVariable("idPost") Integer IdPost) {
        return iCommentServ.addAndAssignCommentToPost(comment, IdPost);
    }
    ////////////////////////////QUERY/////////////////////////////////////
    @GetMapping("/getAllCommentOdPost/{id}")
    public List<Comment> getAllCommentOdPost(@PathVariable("id") Integer id) {
        return iPostServ.getAllCommentOfPost(id);
    }
    @GetMapping("/NbPostLike/{id}")
    public int nbPostLike(@PathVariable("id") Integer id) {
        return iPostServ.nbPostLike(id);
    }

}
