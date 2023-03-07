package com.example.marketplace.services;

import com.example.marketplace.entities.Rating;

import java.io.Serializable;
import java.util.List;

public interface IRatingServ extends Serializable {
    Rating addRating(Rating rating);

    List<Rating> findAllRating();

    Rating updateRating(Rating rating);

    Rating findById(Integer id);

    void deleteRating(Integer id);
    Rating addAndassignRatingToProductAndUser(Rating r,Integer idProd,Integer idUser);
    float RatingCalcul(Integer id);
}
