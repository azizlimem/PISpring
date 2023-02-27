package com.example.marketplace.repository;

import com.example.marketplace.entities.CommentLike;
import org.springframework.data.repository.CrudRepository;

public interface ICommentLikeRepo extends CrudRepository<CommentLike,Integer> {
}
