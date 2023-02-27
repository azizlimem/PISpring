package com.example.marketplace.repository;

import com.example.marketplace.entities.Post;
import org.springframework.data.repository.CrudRepository;

public interface IPostRepo extends CrudRepository<Post,Integer> {
}
