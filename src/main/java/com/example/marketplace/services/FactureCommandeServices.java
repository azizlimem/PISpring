package com.example.marketplace.services;

import com.example.marketplace.entities.FactureCommande;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FactureCommandeServices implements IFactureCommandeServices {

    @Override
    public FactureCommande retrieveFactureCommande(Integer id) {
        return null;
    }

    @Override
    public FactureCommande updateFactureCommande(FactureCommande factureCommande) {
        return null;
    }

    @Override
    public FactureCommande addFactureCommande(FactureCommande factureCommande) {
        return null;
    }
}
