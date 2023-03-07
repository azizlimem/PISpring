package com.example.marketplace.services;


import com.example.marketplace.entities.Livraison;

import java.io.Serializable;
import java.util.List;

public interface LivraisonInt extends Serializable {
    List<Livraison> retrieveAllLivraison();
   // Livraison saveLivraison(Livraison livraison);

    //AjoutLivraison
    Livraison saveandaffectLivreurtoLivraison(Livraison livraison, Long idLivreur);

    Livraison getLivraisonById(Long idlivraison);
    Livraison updateLivraison(Long idlivraison, Livraison livraisonDetails);
    void deleteLivraison(Long idlivraison);
}
