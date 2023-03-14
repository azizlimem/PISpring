package com.example.marketplace.services;
import com.example.marketplace.entities.Catalogue;
import java.io.Serializable;
import java.util.List;

public interface ICatalogueServ extends Serializable {
    Catalogue addCatalogue(Catalogue catalogue);

    List<Catalogue> findAllCatalogue();

    Catalogue updateCatalogue(Catalogue catalogue);

    Catalogue findById(Integer id);

    void deleteCatalogue(Integer id);
    public Catalogue addAndassignCatalogueToMarket(Catalogue c, Integer idMarket);
}
