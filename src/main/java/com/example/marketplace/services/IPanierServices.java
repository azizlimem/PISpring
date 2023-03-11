package com.example.marketplace.services;

import com.example.marketplace.entities.Panier;

public interface IPanierServices {

    Panier addPanierandaffectoUser(Panier panier, Integer IdUser);

    void addProductToPanier(Integer IdPanier, Integer IdProduct, Integer quantiteProduit);

    Panier updatePanier(Panier panier);

    Panier retrievePanier(Integer id);

    void removePanier(Integer id);
    public void romoveListPanier();
}
