package com.example.marketplace.contollers;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.services.ICommentServ;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Forum")
public class ForumController {
    final ICommentServ iCommentServ;


    @GetMapping("/allComment")
    List<Comment> getAllComment(){

        return iCommentServ.getAllComment();
    }
}
