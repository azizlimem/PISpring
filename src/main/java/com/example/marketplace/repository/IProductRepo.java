package com.example.marketplace.repository;

import com.example.marketplace.entities.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductRepo extends CrudRepository<Product,Integer> {
    @Query("SELECT p FROM Product p WHERE p.price <= :price")
    List<Product> filterByPrice(Float price);
    List<Product> findByQuantityLessThanEqual(int quantity);
}

