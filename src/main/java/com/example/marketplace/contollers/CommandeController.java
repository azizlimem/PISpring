package com.example.marketplace.contollers;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.Panier;
import com.example.marketplace.services.ICommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Commande")
@Tag(name = "commande")
public class CommandeController {
    private final ICommandeServices commandeServices;
    @Operation(description = "Add commande")
    @PostMapping("/add")
    Commande addCommande(@RequestBody Commande commande){
        return commandeServices.addCommande(commande);

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
}
