package com.example.marketplace.repository;

import com.example.marketplace.entities.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IRatingRepo extends CrudRepository<Rating,Integer> {
    @Query("select AVG(cr.note) from Product c inner join c.ratings cr where c.id= :id")
    float NbRating(Integer id);
}
