package com.example.marketplace.services;


import com.example.marketplace.entities.Livreur;

import java.io.Serializable;
import java.util.List;

public interface LivreurInt extends Serializable {
    List<Livreur> retrieveAllLivreur();
    Livreur saveLivreur(Livreur livreur);
    Livreur getLivreurById(Long idlivreur);
    Livreur updateLivreur(Long idlivreur, Livreur livreurDetails);
    public void deletelivreur(Long idlivreur);
    public List<Livreur> getLivreurDispo();

}
