package com.example.marketplace.repository;

import com.example.marketplace.entities.Comment;
import org.springframework.data.repository.CrudRepository;

public interface ICommentRepo extends CrudRepository<Comment,Integer> {
}
