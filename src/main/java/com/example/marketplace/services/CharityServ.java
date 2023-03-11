package com.example.marketplace.services;

import com.example.marketplace.entities.Charity;
import com.example.marketplace.entities.LigneCommande;
import com.example.marketplace.repository.ICharityRepo;
import com.example.marketplace.repository.ILigneCommandeRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CharityServ implements ICharityServ {

    private final ICharityRepo charityRepo;
    @Override
    public Charity addCharity(Charity charity, Integer id) {

        Charity c= charityRepo.save(charity);
        return c;
    }

    @Override
    public void removeCharity(Integer id) {
        charityRepo.deleteById(id);

    }

    @Override
    public Charity retrieveCharity(Integer id) {
        return charityRepo.findById(id).orElse(null);
    }
    @Override
    public Charity updateCharity(Charity charity) {
        return charityRepo.save(charity);
    }
}

