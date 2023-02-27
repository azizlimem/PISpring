package com.example.marketplace.services;

import com.example.marketplace.entities.Livraison;
import com.example.marketplace.repository.ILivraisonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LivraisonService implements LivraisonInt{
    @Autowired
    ILivraisonRepository livraisonRepository;

    //Affichage
    @Override
    public List<Livraison> retrieveAllLivraison() {
        List<Livraison> livraisons = livraisonRepository.findAll();
        return livraisons;
    }
    //AjoutLivraison
    @Override
    public Livraison saveLivraison(Livraison livraison) {
        return livraisonRepository.save(livraison);
    }

    //Afficher grace a l id
    @Override
    public Livraison getLivraisonById(Long idlivraison) {
        return livraisonRepository.findById(idlivraison)
                .orElse(null) ;
    }

    //ModifierLivraison
    @Override
    public Livraison updateLivraison(Long idlivraison, Livraison livraisonDetails) {
        Livraison livraison = getLivraisonById(idlivraison);
        livraison.setNomdestinataire(livraisonDetails.getNomdestinataire());
        livraison.setPrenomdestinataire(livraisonDetails.getPrenomdestinataire());
        livraison.setAdresse(livraisonDetails.getAdresse());
        livraison.setMail(livraisonDetails.getMail());
        livraison.setNumerotel(livraisonDetails.getNumerotel());
        return livraisonRepository.save(livraison);
    }

    //Supprimerlivraison
    @Override
    public void deleteLivraison(Long idlivraison) {
        Livraison livraison = getLivraisonById(idlivraison);
        livraisonRepository.delete(livraison);
    }
}

