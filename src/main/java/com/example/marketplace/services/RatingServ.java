package com.example.marketplace.services;

import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Rating;
import com.example.marketplace.repository.IRatingRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
@Slf4j
public class RatingServ implements IRatingServ {
    @Autowired
    IRatingRepo iRatingRepo;
    @Override
    public Rating addProduct(Rating rating) {
        return iRatingRepo.save(rating);
    }

    @Override
    public List<Rating> findAllRating() {
        List<Rating> rat =new ArrayList<>();
        iRatingRepo.findAll().forEach(rat::add);
        return rat;
    }

    @Override
    public Rating updateRating(Rating rating) {
        return iRatingRepo.save(rating);
    }

    @Override
    public Rating findById(Integer id) {
        return iRatingRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteRating(Integer id) {
        iRatingRepo.deleteById(id);
    }
}
