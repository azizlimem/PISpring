package com.example.marketplace.repository;

import com.example.marketplace.entities.Panier;
import org.springframework.data.repository.CrudRepository;

public interface IPanierRepo extends CrudRepository<Panier,Integer> {
}
