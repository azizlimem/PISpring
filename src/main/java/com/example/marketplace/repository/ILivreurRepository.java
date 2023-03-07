package com.example.marketplace.repository;

import com.example.marketplace.entities.Livreur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ILivreurRepository extends JpaRepository<Livreur, Long> {
    @Query("select  (l) from Livreur l where l.statutlivreur='Disponible'")
    List<Livreur> GetLivreur();
    //////////////
    @Query("select count(l) from Livraison l inner join l.livreur lv where lv.idLivreur=:id")
    Integer nbredelivraisonq(@Param("id") Long idLivreur);

    @Query("SELECT (CURRENT_DATE())-(l.datedembauche) FROM Livreur  l WHERE l.idLivreur =:idl")
    Integer nbredannee(@Param("idl") Long idLivreur);
}

