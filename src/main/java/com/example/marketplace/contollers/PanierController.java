package com.example.marketplace.contollers;

import com.example.marketplace.entities.Panier;
import com.example.marketplace.services.IPanierServices;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/panier")
public class PanierController {
      private final IPanierServices panierServices;
    @Operation(description = "Add panier")
    @PostMapping("/add/{id}")
    Panier addPanier(@RequestBody Panier panier,@PathVariable("id") Integer IdUser){
        return panierServices.addPanierandaffectoUser(panier,IdUser);

    }

    @Operation (description = "Update panier")
    @PutMapping("/update")
    Panier updatePanier(@RequestBody Panier panier){
        return  panierServices.updatePanier(panier);
    }

    @Operation (description = "afficher panier")
    @GetMapping("/get/{id}")
    Panier getPanier(@PathVariable("id") Integer id){

        return panierServices.retrievePanier(id);
    }

    @Operation (description = "Delete panier")
    @DeleteMapping("/delete/{id}")
    void deletePanier(@PathVariable("id") Integer id){

        panierServices.removePanier(id);
    }
    @GetMapping("/testttayoub")
    void testttttttttt(){
        panierServices.romoveListPanier();
    }

}
