package com.example.marketplace.services;


import com.example.marketplace.entities.Livraison;

import java.io.Serializable;
import java.util.List;

public interface LivraisonInt extends Serializable {
    List<Livraison> retrieveAllLivraison();
    Livraison saveLivraison(Livraison livraison);
    Livraison getLivraisonById(Long idlivraison);
    Livraison updateLivraison(Long idlivraison, Livraison livraisonDetails);
    void deleteLivraison(Long idlivraison);
}
