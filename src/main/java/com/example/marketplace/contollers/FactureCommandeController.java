package com.example.marketplace.contollers;

import com.example.marketplace.entities.FactureCommande;
import com.example.marketplace.entities.Panier;
import com.example.marketplace.repository.IPanierRepo;
import com.example.marketplace.services.IFactureCommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facturePanier")
@Tag(name = "facture panier")
public class FactureCommandeController {

     private final IFactureCommandeServices factureCommandeServices;
     private final IPanierRepo panierRepo;
    @Operation(description = "Add facture")
    @PutMapping("/add/{id}")
    FactureCommande addFactureCommande (@RequestBody FactureCommande factureCommande , @PathVariable("id") Integer idcommande,@RequestBody Panier panier){
        return factureCommandeServices.addFactureCommande(factureCommande,idcommande,panier) ;
    }
    @Operation (description = "Update facture")
    @PutMapping("/update")
    FactureCommande updateFactureCommande(@RequestBody FactureCommande factureCommande){
        return factureCommandeServices.updateFactureCommande(factureCommande);
    }

    @Operation (description = "Retrieve FactureCommande")
    @PutMapping("/get/{id}")
    FactureCommande getFactureCommande(@PathVariable("id") Integer id){

        return factureCommandeServices.retrieveFactureCommande(id);
    }
    @Operation (description = "Retrieve prix FactureCommande")
    @GetMapping("/get")
    float  getPrixFactureCommande(@RequestBody Panier panier){
      //  Panier panier1=panierRepo.findById(IdPanier).orElse(null);
        return factureCommandeServices.calculePrixTotal(panier);
    }
}
