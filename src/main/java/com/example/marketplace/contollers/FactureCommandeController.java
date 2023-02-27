package com.example.marketplace.contollers;

import com.example.marketplace.entities.FactureCommande;
import com.example.marketplace.services.IFactureCommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/facturePanier")
@Tag(name = "facture panier")
public class FactureCommandeController {

     private final IFactureCommandeServices factureCommandeServices;
    @Operation(description = "Add facture")
    @PostMapping("/add")
    FactureCommande addFactureCommande (@RequestBody FactureCommande factureCommande){
        return factureCommandeServices.addFactureCommande(factureCommande) ;
    }
    @Operation (description = "Update facture")
    @PutMapping("/update")
    FactureCommande updateFactureCommande(@RequestBody FactureCommande factureCommande){
        return factureCommandeServices.updateFactureCommande(factureCommande);
    }

    @Operation (description = "Retrieve FactureCommande")
    @GetMapping("/get/{id}")
    FactureCommande getFactureCommande(@PathVariable("id") Integer id){

        return factureCommandeServices.retrieveFactureCommande(id);
    }

}
