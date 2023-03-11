package com.example.marketplace.services;

import com.example.marketplace.entities.LigneCommande;

public interface ILigneCommandeServices {
    LigneCommande retrieveLigneCommande(Integer id);

    LigneCommande updateLigneCommande(LigneCommande ligneCommande);

    LigneCommande affecterpanierAndProductlignedecommande(LigneCommande ligneCommande, Integer idpanier, Integer idProduct);
}
