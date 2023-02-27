package com.example.marketplace.repository;

import com.example.marketplace.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ILivreurRepository extends JpaRepository<Livreur, Long> {
    @Query("select  (l) from Livreur l where l.statutlivreur='Disponible'")
    List<Livreur> GetLivreur();

}

