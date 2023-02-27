package com.example.marketplace.repository;

import com.example.marketplace.entities.Post;
import com.example.marketplace.entities.PostLike;
import org.springframework.data.repository.CrudRepository;

public interface IPostLikeRepo extends CrudRepository<PostLike,Integer> {
}
