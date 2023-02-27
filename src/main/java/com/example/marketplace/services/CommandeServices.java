package com.example.marketplace.services;

import com.example.marketplace.entities.Commande;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommandeServices implements ICommandeServices{
    @Override
    public Commande addCommande(Commande commande) {
        return null;
    }

    @Override
    public void removeCommande(Integer id) {

    }

    @Override
    public Commande retrieveCommande(Integer id) {
        return null;
    }

    @Override
    public Commande updateCommande(Commande commande) {
        return null;
    }
}
