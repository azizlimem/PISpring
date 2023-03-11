package com.example.marketplace.contollers;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.Panier;
import com.example.marketplace.services.ICommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Commande")
@Tag(name = "commande")
public class CommandeController {
    private final ICommandeServices commandeServices;
    @Operation(description = "Add commande")
    @PutMapping("/add/{id}")
    Commande addCommande(@RequestBody Commande commande,@PathVariable("id") Integer idCommande){
        return commandeServices.addCommande(commande,idCommande);

    }

    @Operation (description = "Update Commande")
    @PutMapping("/update")
    Commande updateCommande(@RequestBody Commande commande){
        return  commandeServices.updateCommande(commande);
    }

    @Operation (description = "afficher Commande")
    @GetMapping("/get/{id}")
    Commande getPanier(@PathVariable("id") Integer id){

        return commandeServices.retrieveCommande(id);
    }

    @Operation (description = "Delete Commande")
    @DeleteMapping("/delete/{id}")
    void deleteCommande(@PathVariable("id") Integer id){

        commandeServices.removeCommande(id);
    }
    @Operation(description = "ajouter commande au charity")
    @PutMapping("/add/{idCharity}/{idLigne}")
    Commande addcommandeToCharity(@RequestBody Commande commande,@PathVariable("idCharity") Integer IdCharity,@PathVariable("idLigne") Integer IdLigne){
        return commandeServices.addandaffectcommandeToCharity(commande,IdCharity,IdLigne);
    }
}
