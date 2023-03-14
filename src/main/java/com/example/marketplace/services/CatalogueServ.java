package com.example.marketplace.services;

import com.example.marketplace.entities.Catalogue;
import com.example.marketplace.entities.Market;
import com.example.marketplace.entities.Product;
import com.example.marketplace.repository.ICatalogueRepo;
import com.example.marketplace.repository.IMarketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CatalogueServ implements ICatalogueServ {
    @Autowired
    ICatalogueRepo iCatalogueRepo;
    IMarketRepository iMarketRepository;

    @Override
    public Catalogue addCatalogue(Catalogue catalogue) {
        return iCatalogueRepo.save(catalogue);
    }

    @Override
    public List<Catalogue> findAllCatalogue() {
        List<Catalogue> cat =new ArrayList<>();
        iCatalogueRepo.findAll().forEach(cat::add);
        return cat;
    }

    @Override
    public Catalogue updateCatalogue(Catalogue catalogue) {
        return iCatalogueRepo.save(catalogue);
    }

    @Override
    public Catalogue findById(Integer id) {
        return iCatalogueRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteCatalogue(Integer id) {
        iCatalogueRepo.deleteById(id);

    }
    public Catalogue addAndassignCatalogueToMarket(Catalogue c, Integer idMarket) {
        Market market =iMarketRepository.findById(idMarket).orElse(null);
        c.setMarket(market);
        iCatalogueRepo.save(c);
        return null;
    }
}
