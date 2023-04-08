package com.example.marketplace.services;

import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Reclamation;
import com.example.marketplace.enumerations.Statuss;
import com.example.marketplace.enumerations.Sujetrec;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface IRecService {
    Reclamation ajouterreclamation(Reclamation r );
    void  affecetruserlreclamation (Long idrec,Integer iduser);
    void deleteByIdrec(Long id);

    List<Reclamation> listedesreclamations();

    void updatereclamation(Long idrec, Statuss ticketstatus);
    Reclamation updatereclamation2 (Long idrec, Reclamation  i);
    int countReclamation (Long iduser);
    double retournesalaire (Long idc1) ;
   // void desactiveruser (Long iduser2);
    List <Product> afficherproduitssimilaires (Integer  idprodrec);

    List<Reclamation> order();
    LocalDate calculerDateFinIntervention(Long idinter);
    String compteurdenrbdemots (Long idrec);
    void prixproduit (Sujetrec description,Integer idprodrec);
    Integer nombredereclamationdunproduit(Sujetrec description,Integer id);
    List<List<String>> readExcel(String filePath) throws IOException;
    String retournescoredesatisfactionclient (String filepath, String filepathneutre,String filepathnegatifs)throws IOException ;
    String lemeilleureemployedeumois();
    void  affecterinterventionareclamation(Long idinterv,Long idrec);
    void  affecterlignecommandereclamation (Integer idligcmd,Long idrec);

}
