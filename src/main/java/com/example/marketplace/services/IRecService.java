package com.example.marketplace.services;

import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Reclamation;
import com.example.marketplace.enumerations.Statuss;

import java.time.LocalDate;
import java.util.List;

public interface IRecService {
    Reclamation ajouterreclamation(Reclamation r);

    void deleteByIdrec(Long id);

    List<Reclamation> listedesreclamations();

    void updatereclamation(Long idrec, Statuss ticketstatus);
    Reclamation updatereclamation2 (Long idrec, Reclamation  i);
    int countReclamation (Long iduser);
    Float retournesalaire (Long idc1) ;
   // void desactiveruser (Long iduser2);
    List <Product> afficherproduitssimilaires (Integer  idprodrec);
    int prioritecondition(Long idrec );
    List<Reclamation> order();
    LocalDate calculerDateFinIntervention(Long idinter);
}
