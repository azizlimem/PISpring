package com.example.marketplace.repository;

import com.example.marketplace.entities.Commande;
import org.springframework.data.repository.CrudRepository;

public interface ICommandeRepo extends CrudRepository<Commande,Integer> {
}
