package com.example.marketplace.services;

import com.example.marketplace.entities.Panier;
import com.example.marketplace.repository.IPanierRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PanierServices implements IPanierServices{
private final IPanierRepo panierRepo;

    @Override
    public Panier addPanier(Panier panier) {
        return panierRepo.save(panier);
    }

    @Override
    public Panier updatePanier(Panier panier) {
        return panierRepo.save(panier);
    }

    @Override
    public Panier retrievePanier(Integer idPanier) {
        return panierRepo.findById(idPanier).orElse(null);
    }

    @Override
    public void removePanier(Integer idPanier) {
        panierRepo.deleteById(idPanier);

    }
}
