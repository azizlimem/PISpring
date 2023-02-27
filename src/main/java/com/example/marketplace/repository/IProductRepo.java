package com.example.marketplace.repository;

import com.example.marketplace.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface IProductRepo extends CrudRepository<Product,Integer> {
}
