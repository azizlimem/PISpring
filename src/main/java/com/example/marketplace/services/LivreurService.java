package com.example.marketplace.services;

import com.example.marketplace.entities.Livreur;
import com.example.marketplace.repository.ILivreurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LivreurService implements LivreurInt{
    @Autowired
    ILivreurRepository livreurRepository;

    //Affichage
    @Override
    public List<Livreur> retrieveAllLivreur() {
        List<Livreur> livreurs = livreurRepository.findAll();
        return livreurs;
    }
    //Ajout
    @Override
    public Livreur saveLivreur(Livreur livreur) {
        return livreurRepository.save(livreur);
    }

    //Afficher grace l id
    @Override
    public Livreur getLivreurById(Long idlivreur) {
        return livreurRepository.findById(idlivreur)
                .orElse(null);
    }

    //ModifierLivraison
    @Override
    public Livreur updateLivreur(Long idlivreur, Livreur livreurDetails) {
        Livreur livreur = getLivreurById(idlivreur);
        livreur.setSalaire(livreurDetails.getSalaire());
        return livreurRepository.save(livreur);
    }

    //Supprimerlivreur
    @Override
    public void deletelivreur(Long idlivreur) {
        Livreur livreur = getLivreurById(idlivreur);
        livreurRepository.delete(livreur);
    }

    @Override
    public List<Livreur> getLivreurDispo() {
        return livreurRepository.GetLivreur();
    }


}

