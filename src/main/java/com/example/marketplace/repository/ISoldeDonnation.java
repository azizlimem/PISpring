package com.example.marketplace.repository;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.SoldeDonnation;
import org.springframework.data.repository.CrudRepository;

public interface ISoldeDonnation  extends CrudRepository<SoldeDonnation,Integer> {
}
