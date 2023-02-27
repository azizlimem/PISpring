package com.example.marketplace.services;

import com.example.marketplace.entities.Panier;

public interface IPanierServices {
    Panier addPanier(Panier panier);

    Panier updatePanier(Panier panier);

    Panier retrievePanier(Integer id);

    void removePanier(Integer id);
}
