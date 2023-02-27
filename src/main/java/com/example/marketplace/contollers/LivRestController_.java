package com.example.marketplace.contollers;

import com.example.marketplace.entities.Livraison;
import com.example.marketplace.entities.Livreur;
import com.example.marketplace.services.LivraisonService;
import com.example.marketplace.services.LivreurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Gestionslivraison")
public class LivRestController_ {
    @Autowired
    private LivraisonService livraisonService;
    @Autowired
    private LivreurService livreurService;
    ////////////////////////////////////////////////////////////////////////////////////////
    //GestionLivrasion
    @GetMapping("/afficherdetailsdelivraison")
    public List<Livraison> retrieveAllLivraison() {
        return livraisonService.retrieveAllLivraison();
    }

    @PostMapping("Ajouterunelivraison")
    public Livraison saveLivraison(@RequestBody Livraison livraison) {
        return livraisonService.saveLivraison(livraison);
    }

    @GetMapping("Rechercherunelivraison/{id}")
    public Livraison getLivraisonById(@PathVariable(value = "idlivraison") Long idlivraison) {
        return livraisonService.getLivraisonById(idlivraison);
    }

    @PutMapping("Modifierlalivraison/{id}")
    public Livraison updateLivraison(@PathVariable(value = "idlivraison") Long idlivraison,
                                     @RequestBody Livraison livraisonDetails) {
        return livraisonService.updateLivraison(idlivraison, livraisonDetails);
    }

    @DeleteMapping("Supprimerunelivraison/{id}")
    public ResponseEntity<?> deleteLivraison(@PathVariable(value = "idlivraison") Long idlivraison) {
        livraisonService.deleteLivraison(idlivraison);
        return ResponseEntity.ok().build();
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //GestionLivreur
    @GetMapping("/afficherdetailsdulivreur")
    public List<Livreur> retrieveAllLivreur() {
        return livreurService.retrieveAllLivreur();
    }

    @PostMapping("Ajouterunelivreur")
    public Livreur saveLivreur(@RequestBody Livreur livreur) {
        return livreurService.saveLivreur(livreur);
    }

    @GetMapping("Rechercherunlivreur/{id}")
    public Livreur getLivreurById(@PathVariable(value = "idlivreur") Long idlivreur) {
        return livreurService.getLivreurById(idlivreur);
    }

    @PutMapping("Modifierlelivreur/{id}")
    public Livreur updateLivreur(@PathVariable(value = "idlivreur") Long idlivreur,
                                 @RequestBody Livreur livreurDetails) {
        return livreurService.updateLivreur(idlivreur, livreurDetails);
    }

    @DeleteMapping("Supprimerunlivreur/{id}")
    public ResponseEntity<?> deleteLivreur(@PathVariable(value = "idlivreur") Long idlivreur) {
        livreurService.deletelivreur(idlivreur);
        return ResponseEntity.ok().build();
    }
}
