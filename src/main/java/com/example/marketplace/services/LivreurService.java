package com.example.marketplace.services;
import com.example.marketplace.entities.Livraison;
import com.example.marketplace.enumerations.Statut_livreur;
import com.example.marketplace.repository.ILivraisonRepository;
import com.google.maps.*;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import lombok.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

import com.example.marketplace.entities.Livreur;
import com.example.marketplace.repository.ILivreurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LivreurService implements LivreurInt{
    //@Value("${google.maps.apikey}")
   // private String apiKey;
    @Autowired
    ILivraisonRepository livraisonRepository;
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

   /* @Override
    public int countLivraison(Long idlivreur) {
        int a = 0;

        List<Livraison> listeliv = livraisonRepository.findAll();

        for (Livraison livraison : listeliv) {
            if (livraison.getLivreur().getIdLivreur()==null ){
                return a;
            }
            if (livraison.getLivreur().getIdLivreur().equals(idlivreur) ) {
                a++;
            }
        }
        return a;
    }*/
    @Override
    public int nbredelivraison(Long idLivreur){
        return livreurRepository.nbredelivraisonq(idLivreur);
    }

    @Override
    public void retournestatut(Long idlivreur) {

        Livreur livreur=livreurRepository.findById(idlivreur).orElse(null);
        if (nbredelivraison(idlivreur)>0) {
            livreur.setStatutlivreur(Statut_livreur.Nondisponible);
            System.out.println("non");
            livreurRepository.save(livreur);

        }
        else {
            livreur.setStatutlivreur(Statut_livreur.Disponible);
            System.out.println("dispo");
        }
    }



    @Override
    public void ajouterunbonus(Long idLivreur){
        Livreur livreur=livreurRepository.findById(idLivreur).orElse(null);
        float sal=livreur.getSalaire();
        if (livreurRepository.nbredannee(idLivreur)>=3){
            livreur.setSalaire(sal+50);
            livreurRepository.save(livreur);
        }
    }

    @Override
    public void ajouterunbonusencasdelivadd(Long idLivreur){
        Livreur livreur=livreurRepository.findById(idLivreur).orElse(null);
        float sal=livreur.getSalaire();
        if (livreurRepository.nbredelivraisonq(idLivreur)>3){
            livreur.setSalaire(sal+7);
            livreurRepository.save(livreur);
        }
    }
  @Override
  public int getdatedembauche(Long idLivreur) {
        return livreurRepository.nbredannee(idLivreur);
    }

    /*@Override
    public void salaryraise(Long idlivreur) {

        Livreur livreur=livreurRepository.findById(idlivreur).orElse(null);
        if (date) {
            livreur.setStatutlivreur(Statut_livreur.Nondisponible);
            System.out.println("non");
            livreurRepository.save(livreur);
        }
        else {
            livreur.setStatutlivreur(Statut_livreur.Disponible);
            System.out.println("dispo");
        }
    }*/


    ///////////////////////////////////////////////////

    @Override
    public double calculateDistance(String origin, String destination) throws ApiException, InterruptedException, IOException {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyDvv-7LdYSN_67e1Z6IdG8tojsTpDRE7Co")
                .build();

        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context)
                .origins(new String[]{origin})
                .destinations(new String[]{destination})
                .mode(TravelMode.DRIVING)
                .avoid(DirectionsApi.RouteRestriction.TOLLS);

        DistanceMatrix result = req.await();
        System.out.println(result);
        DistanceMatrixElement[] elements = result.rows[0].elements;
        Distance distance = elements[0].distance;
        return distance.inMeters / 1000.0;
    }


}

