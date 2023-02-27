package com.example.marketplace.services;


import com.example.marketplace.entities.Intervention;
import com.example.marketplace.entities.Livreur;
import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Reclamation;
import com.example.marketplace.enumerations.Categorie;
import com.example.marketplace.enumerations.Statuss;
import com.example.marketplace.repository.ILivreurRepository;
import com.example.marketplace.repository.IProductRepo;

import com.example.marketplace.repository.IRecrepo;
import com.example.marketplace.repository.IinterventionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RecService implements  IRecService {
    @Autowired
    IRecrepo recrepo;
    @Autowired
    IProductRepo prodrepo;
    @Autowired
    ILivreurRepository livrepo;
    @Autowired
    IinterventionRepo interrepo;
    @Override
    public Reclamation ajouterreclamation(Reclamation i) {
        return recrepo.save(i);
    }

    @Override
    public void deleteByIdrec(Long id) {
        recrepo.deleteById(id);
    }

    @Override
    public List<Reclamation> listedesreclamations() {
        return recrepo.findAll();
    }

    @Override
    public void updatereclamation(Long idrec, Statuss ticketstatus) {
        recrepo.updaterecc(idrec, ticketstatus);
    }

    @Override
    public Reclamation updatereclamation2(Long idrec, Reclamation i) {
        if (recrepo.findById(idrec).isPresent()) {
            Reclamation interv = recrepo.findById(idrec).get();
            interv.setDescription(i.getDescription());
            interv.setCreatedDate(i.getCreatedDate());
            interv.setPriorite(i.getPriorite());
            interv.setTicketstatus(i.getTicketstatus());
            interv.setTyperec(i.getTyperec());
            Reclamation updatedinterention = recrepo.save(interv);
            return updatedinterention;
        } else {
            return null;
        }
    }
    /// thsyb les reclamations ta livreur passe en parmetre //////
    ////user houni houa livreur //////
    @Override
    public int countReclamation(Long idlivreur) {
        int a = 0;
        List<Reclamation> amal = listedesreclamations();

        for (Reclamation reclamation : amal) {

            if ( reclamation.getLgcommande().getCommande().getFactureCommandes().getLivraison().getLivreur().getIdLivreur().equals(idlivreur)) {
                a++;
            }
        }
        return a;
    }

    public Float calculatePercentage(Float numerator, int denominator) {
        return (numerator *denominator) / 100;
    }
    //@Override
    //public void desactiveruser (Long iduser2){


        //Optional<Livreur> userr=clrepo.findById(iduser2);
        //if (userr.isPresent()){
            //Livreur amalfares= userr.get();
           // amalfares.setActif(false);
         //   clrepo.save(amalfares);}}
    @Override
    public Float retournesalaire(Long idlivreur) {
        Float salaire= (float)0.0;
        Optional <Livreur> livreur=livrepo.findById(idlivreur);
        Livreur amalfares= livreur.get();
        if (countReclamation(idlivreur) == 1) {
            salaire =calculatePercentage(amalfares.getSalaire(),90) ;
        }
        else if (countReclamation(idlivreur) == 3){
            salaire =calculatePercentage(amalfares.getSalaire(),80);
        }//else {
        //desactiveruser(amalfares);
        return salaire;
    }
    @Override
    public List<Product>afficherproduitssimilaires (Integer idprodrec) {
        Optional<Product> produitopt = prodrepo.findById(idprodrec);
        Product produitreclame = produitopt.get();
        Float prixreclame = produitreclame.getPrice();
        Categorie categoriereclame = produitreclame.getCategorie();
        List<Product> p = new ArrayList<>();
        prodrepo.findAll().forEach(p::add);
        List<Product> produitajouterprodsimil = new ArrayList<>();
        for (Product product : p) {
            if (product.getId() != produitreclame.getId() && product.getCategorie().equals(categoriereclame) && product.getPrice().equals(prixreclame) ) {

                produitajouterprodsimil.add(product);
            }
        }
        return produitajouterprodsimil;
    }
    @Override
    public int prioritecondition (Long idrec){
        Optional<Reclamation> rec = recrepo.findById(idrec);
        Reclamation reclamation = rec.get();
        String priorite = reclamation.getPriorite();
        int a =0;
        int taille = priorite.length();
        if (taille > 10){
            a++;
        }
        return a ;
    }
    @Override
    public List<Reclamation> order(){
        return recrepo.orderbypriorite();
    }
    @Override
    public LocalDate calculerDateFinIntervention(Long idinter) {
        Optional<Intervention> inter = interrepo.findById(idinter);
        Intervention intervention = inter.get();
        Date dateDebut = intervention.getDatedebinter();
        Integer duree=intervention.getDureeinter();
        LocalDate localDateDebut = new java.sql.Date(dateDebut.getTime()).toLocalDate();
        LocalDate dateFin = localDateDebut;
        for (int i = 0; i < duree; i++) {
            dateFin = dateFin.plusDays(1);
        }
        return dateFin;
    }



}