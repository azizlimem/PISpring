package com.example.marketplace.services;

import com.example.marketplace.entities.FactureCommande;

public interface IFactureCommandeServices {
    FactureCommande retrieveFactureCommande(Integer id);

    FactureCommande updateFactureCommande(FactureCommande factureCommande);

    FactureCommande addFactureCommande(FactureCommande factureCommande);
}
