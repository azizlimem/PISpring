package com.example.marketplace.services;

import com.example.marketplace.entities.Charity;

public interface ICharityServ {
   

    Charity addCharity(Charity charity, Integer idCh);

    Charity updateCharity(Charity charity);

    Charity retrieveCharity(Integer id);

    void removeCharity(Integer id);
}
