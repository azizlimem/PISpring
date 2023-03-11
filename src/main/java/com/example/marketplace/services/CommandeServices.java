package com.example.marketplace.services;

import com.example.marketplace.entities.*;
import com.example.marketplace.repository.ICharityRepo;
import com.example.marketplace.repository.ICommandeRepo;
import com.example.marketplace.repository.IFactureCommandeRepo;
import com.example.marketplace.repository.ILigneCommandeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class CommandeServices implements ICommandeServices{
    final ILigneCommandeRepo ligneCommandeRepo;

    final IFactureCommandeRepo factureCommandeRepo;
    private final ICommandeRepo commandeRepo;
    private final ICharityRepo charityRepo;
    @Override
    public Commande addCommande(Commande commande,Integer idLigne) {
        LigneCommande ligneCommande=ligneCommandeRepo.findById(idLigne).orElse(null);
        Commande c= commandeRepo.save(commande);
        ligneCommande.setCommande(commande);
        FactureCommande f = new FactureCommande();
        f.setDateCommande(new Date());
        if(ligneCommande.getPaniers().getPrixTotal()>200){
            f.setPrixFacture(ligneCommande.getPaniers().getPrixTotal()*ligneCommande.getPaniers().getRemise()*ligneCommande.getQuantiteProduit());
        }
        else{
            f.setPrixFacture(ligneCommande.getPaniers().getPrixTotal()*ligneCommande.getQuantiteProduit());
        }

        c.setFactureCommandes(f);
        factureCommandeRepo.save(f);
         commandeRepo.save(c);

        return c;
    }
    @Override
    public Commande addandaffectcommandeToCharity(Commande commande,Integer idCharity,Integer idLigne){
        LigneCommande ligneCommande=ligneCommandeRepo.findById(idLigne).orElse(null);
         Charity charity=charityRepo.findById(idCharity).orElse(null);
         charity.setCommande(commande);
        Commande c= commandeRepo.save(commande);
        FactureCommande f = new FactureCommande();
        f.setDateCommande(new Date());
        if(ligneCommande.getPaniers().getPrixTotal()>200){
            f.setPrixFacture(ligneCommande.getPaniers().getPrixTotal()*ligneCommande.getPaniers().getRemise()*ligneCommande.getQuantiteProduit());
        }
        else{
            f.setPrixFacture(ligneCommande.getPaniers().getPrixTotal()*ligneCommande.getQuantiteProduit());
        }

        c.setFactureCommandes(f);
        factureCommandeRepo.save(f);
        commandeRepo.save(c);

        return c;
    }

    @Override
    public void removeCommande(Integer id) {
    commandeRepo.deleteById(id);

    }

    @Override
    public Commande retrieveCommande(Integer id) {
        return commandeRepo.findById(id).orElse(null);
    }
    @Override
    public Commande updateCommande(Commande commande) {
        return commandeRepo.save(commande);
    }

}
