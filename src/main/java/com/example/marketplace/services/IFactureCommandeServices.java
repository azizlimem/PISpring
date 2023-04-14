package com.example.marketplace.services;

import com.example.marketplace.entities.FactureCommande;
import com.example.marketplace.entities.Panier;

public interface IFactureCommandeServices {
    FactureCommande retrieveFactureCommande(Integer id);

    FactureCommande updateFactureCommande(FactureCommande factureCommande);
    FactureCommande addFactureCommande(FactureCommande factureCommande, Integer IdCommande, Panier panier);

    float calculePrixTotal(Panier panier);
}
