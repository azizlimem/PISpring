package com.example.marketplace.services;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.FactureCommande;
import com.example.marketplace.entities.LigneCommande;
import com.example.marketplace.entities.Panier;
import com.example.marketplace.repository.ICommandeRepo;
import com.example.marketplace.repository.IFactureCommandeRepo;
import com.example.marketplace.repository.IPanierRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class FactureCommandeServices implements IFactureCommandeServices {
  private final IFactureCommandeRepo factureCommandeRepo;
  private final ICommandeRepo commandeRepo ;
  private final IPanierRepo panierRepository;
    @Override
    public FactureCommande retrieveFactureCommande(Integer id) {
        return  factureCommandeRepo.findById(id).orElse(null);
    }

    @Override
    public FactureCommande updateFactureCommande(FactureCommande factureCommande) {
        return factureCommandeRepo.save(factureCommande);
    }

    @Override
    public FactureCommande addFactureCommande(FactureCommande factureCommande,Integer IdCommande,Panier panier) {

            //Commande commande= commandeRepo.findById(IdCommande).orElse(null);
            //factureCommande.setCommande(commande);
           // float a=calculePrixTotal();
            //factureCommande.setPrixFacture();
        //factureCommande.setPrixFacture();
        float PrixTotal =calculePrixTotal(panier);
        FactureCommande facture = new FactureCommande();
        facture.setPrixFacture(PrixTotal);
        facture.setDateCommande(new Date());
        ////assoc facture lel commande////
        Commande commande= commandeRepo.findById(IdCommande).orElse(null);
        commande.setFactureCommandes(facture);
        facture.setCommande(commande);
                    factureCommandeRepo.save(factureCommande);
        return facture;}



    @Override
    public float calculePrixTotal(Panier panier) {
        float total= 0.0f;
        float p=0.0f;

        for (LigneCommande ligneCommande : panier.getLigneCommandes()) {
            if(panier.getPrixTotal()>=200){
                p+=ligneCommande.getPaniers().getPrixTotal()*ligneCommande.getPaniers().getRemise();
                total+=(ligneCommande.getPaniers().getPrixTotal()-p)*ligneCommande.getQuantiteProduit();


            }
            else{
            total += ligneCommande.getPaniers().getPrixTotal() * ligneCommande.getQuantiteProduit();
        }}
        return total;
    }
}