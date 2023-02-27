package com.example.marketplace.repository;

import com.example.marketplace.entities.FactureCommande;
import org.springframework.data.repository.CrudRepository;

public interface IFactureCommandeRepo extends CrudRepository<FactureCommande,Integer> {
}
