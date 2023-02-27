package com.example.marketplace.contollers;

import com.example.marketplace.entities.Commande;
import com.example.marketplace.entities.LigneCommande;
import com.example.marketplace.services.ICommandeServices;
import com.example.marketplace.services.ILigneCommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/LigneCommande")
@Tag(name = "ligne de commande")
public class LigneCommandeController {
private final ILigneCommandeServices ligneCommandeServices;

    @Operation(description = "Add lignecommande")
    @PostMapping("/add")
    LigneCommande addLigneCommande(@RequestBody LigneCommande ligneCommande){
        return ligneCommandeServices.addLigneCommande(ligneCommande);

    }

    @Operation (description = "Update ligneCommande")
    @PutMapping("/update")
    LigneCommande updateLigneCommande(@RequestBody LigneCommande ligneCommande){
        return  ligneCommandeServices.updateLigneCommande(ligneCommande);
    }

    @Operation (description = "afficher ligne Commande")
    @GetMapping("/get/{id}")
    LigneCommande getLigneCommande(@PathVariable("id") Integer id){

        return ligneCommandeServices.retrieveLigneCommande(id);
    }
}
