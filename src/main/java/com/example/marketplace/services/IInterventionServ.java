package com.example.marketplace.services;

import com.example.marketplace.entities.Intervention;

import java.util.List;

public interface IInterventionServ {
    Intervention ajouterintervention(Intervention i);

    void deleteByIdd(Long id);

    List<Intervention> listedesinetrventions();

    void updateintervention( Long id,Integer dureeinter);
    Intervention updateintervention2 (Long id, Intervention  i);
}
