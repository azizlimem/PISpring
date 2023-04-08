package com.example.marketplace.contollers;

import com.example.marketplace.entities.Catalogue;
import com.example.marketplace.entities.Product;
import com.example.marketplace.services.ICatalogueServ;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Catalogue")
public class CatalogueController {
    final ICatalogueServ iCatalogueServ;

    @GetMapping("/allCatalogue")
    List<Catalogue> findAllCatalogue() {
        return iCatalogueServ.findAllCatalogue();
    }

    @PostMapping("/addCatalogue")
    Catalogue addCatalogue(@RequestBody Catalogue c) {
        return iCatalogueServ.addCatalogue(c);
    }

    @PatchMapping("/updateCatalogue")
    Catalogue updateCatalogue(@RequestBody Catalogue c) {
        return iCatalogueServ.updateCatalogue(c);
    }

    @GetMapping("/getCatalogue/{id}")
    Catalogue findById(@PathVariable("id") Integer id) {
        return iCatalogueServ.findById(id);
    }

    @DeleteMapping("/deleteCatalogue/{id}")
    void deleteCatalogue(@PathVariable("id") Integer id) {
        iCatalogueServ.deleteCatalogue(id);
    }
    @PutMapping("/addAndassignCataloqueToMarket/{idMarket}")
    Catalogue addAndassignCataloqueToMarket(@RequestBody Catalogue C,@PathVariable("idMarket") Integer idMarket){
        return iCatalogueServ.addAndassignCatalogueToMarket(C,idMarket);
    }

}
