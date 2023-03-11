package com.example.marketplace.services;

import com.example.marketplace.entities.Commande;

public interface ICommandeServices {
    Commande addCommande(Commande commande,Integer idLigne);

    Commande addandaffectcommandeToCharity(Commande commande, Integer idCharity, Integer idLigne);

    void removeCommande(Integer id);


    Commande retrieveCommande(Integer id);

    Commande updateCommande(Commande commande);
}
