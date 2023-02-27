package com.example.marketplace.repository;

import com.example.marketplace.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface IRatingRepo extends CrudRepository<Rating,Integer> {
}
