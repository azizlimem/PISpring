package com.example.marketplace.services;

import com.example.marketplace.entities.Rating;

import java.io.Serializable;
import java.util.List;

public interface IRatingServ extends Serializable {
    Rating addProduct(Rating rating);

    List<Rating> findAllRating();

    Rating updateRating(Rating rating);

    Rating findById(Integer id);

    void deleteRating(Integer id);
}
