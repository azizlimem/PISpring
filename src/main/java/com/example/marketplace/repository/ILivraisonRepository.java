package com.example.marketplace.repository;


import com.example.marketplace.entities.Livraison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ILivraisonRepository extends JpaRepository<Livraison, Long> {

}
