package com.example.marketplace.repository;

import com.example.marketplace.entities.LigneCommande;
import org.springframework.data.repository.CrudRepository;

public interface ILigneCommandeRepo extends CrudRepository<LigneCommande,Integer> {
}
