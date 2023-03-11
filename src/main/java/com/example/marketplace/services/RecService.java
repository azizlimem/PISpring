package com.example.marketplace.services;


import com.example.marketplace.entities.*;
import com.example.marketplace.enumerations.Categorie;
import com.example.marketplace.enumerations.Statuss;
import com.example.marketplace.enumerations.Sujetrec;
import com.example.marketplace.repository.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

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

    @Autowired
    IUserRepository userrepo;
    @Autowired
    CommentServ mdserv;
    @Autowired
    ICommentRepo commentrepo;
    @Autowired
    InterventionServ interserv;
    @Autowired
    UserService userserv;
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

            if (reclamation.getDescription().equals(Sujetrec.livreur) && reclamation.getLgcommande().getCommande().getFactureCommandes().getLivraison().getLivreur().getIdLivreur().equals(idlivreur)) {
                a++;
            }
        }
        return a;
    }



    public Integer nombredereclamationdunproduit(Sujetrec description,Integer id){
        return prodrepo.nombredereclamationdunproduit(description,id);
    }

    public Float calculatePercentage(Float numerator, int denominator) {
        return (numerator * denominator) / 100;
    }
    // @Override
    // public void desactiveruser (Long iduser2,int duree){

    //int i =0;
    // Optional<Livreur> livreur =livrepo.findById(iduser2);

    //if (livreur.isPresent() ){
    //  Livreur amalfares= livreur.get();
    // amalfares.setActif(false);
    //clrepo.save(amalfares);}}
    @Override
    public Float retournesalaire(Long idlivreur) {
        Float salaire = (float) 0.0;
        Livreur amalfares = livrepo.findById(idlivreur).orElse(null);
        if (countReclamation(idlivreur) == 1) {
            salaire = calculatePercentage(amalfares.getSalaire(), 90);
        } else if (countReclamation(idlivreur) == 3) {
            salaire = calculatePercentage(amalfares.getSalaire(), 80);
        }//else {
        //desactiveruser(amalfares);
        return salaire;
    }


    @Override
    public List<Product> afficherproduitssimilaires(Integer idprodrec) {
       Product produitreclame = prodrepo.findById(idprodrec).orElse(null);
        Float prixreclame = produitreclame.getPrice();
        Categorie categoriereclame = produitreclame.getCategorie();
        List<Product> p = new ArrayList<>();
        prodrepo.findAll().forEach(p::add);
        List<Product> produitajouterprodsimil = new ArrayList<>();
        for (Product product : p) {
            if (product.getId() != produitreclame.getId() && product.getCategorie().equals(categoriereclame) && product.getPrice().equals(prixreclame)) {

                produitajouterprodsimil.add(product);
            }
        }
        return produitajouterprodsimil;
    }



    @Override
    public List<Reclamation> order() {
        return recrepo.orderbypriorite();
    }

    @Override
    public LocalDate calculerDateFinIntervention(Long idinter) {
        Optional<Intervention> inter = interrepo.findById(idinter);
        Intervention intervention = inter.get();
        Date dateDebut = intervention.getDatedebinter();
        Integer duree = intervention.getDureeinter();
        LocalDate localDateDebut = new java.sql.Date(dateDebut.getTime()).toLocalDate();
        LocalDate dateFin = localDateDebut;
        for (int i = 0; i < duree; i++) {
            dateFin = dateFin.plusDays(1);
        }
        return dateFin;
    }

    @Override
    public String compteurdenrbdemots(Long idrec) {
        Optional<Reclamation> rec = recrepo.findById(idrec);
        Reclamation reclamation = rec.get();
        String recc = reclamation.getPriorite();
        String message = "";
        int longueur = recc.split(" ").length;
        if (longueur > 2) {
            return message.concat("la longueur ne doit pas depasser les deux mots ");
        }
        return message.concat("la chaine est validee");
    }

    @Override
    public  void prixproduit (Sujetrec description,Integer idprodrec) {
        Product produitrec = prodrepo.findById(idprodrec).orElse(null);

            if (nombredereclamationdunproduit(description,idprodrec)==3 ) {
                produitrec.setPrice(calculatePercentage(produitrec.getPrice(),95));
                produitrec.setCategorie(Categorie.Cosmetique);
                prodrepo.save(produitrec);
            }

        }
    @Override
    public List<List<String>> readExcel(String filePath) throws IOException {

        List<List<String>> excelData = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(inputStream);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

            Sheet sheet = workbook.getSheetAt(i);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                List<String> rowData = new ArrayList<>();

                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }

                excelData.add(rowData);
            }
        }

        workbook.close();
        inputStream.close();

        return excelData;

    }

    public List<List<String>> readExcelmotsneutres(String filePath) throws IOException {

        List<List<String>> excelData = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(inputStream);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

            Sheet sheet = workbook.getSheetAt(i);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                List<String> rowData = new ArrayList<>();

                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }

                excelData.add(rowData);
            }
        }

        workbook.close();
        inputStream.close();

        return excelData;

    }

    public List<List<String>> readExcelmotsnegatifs(String filePath) throws IOException {

        List<List<String>> excelData = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(filePath);

        Workbook workbook = new XSSFWorkbook(inputStream);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {

            Sheet sheet = workbook.getSheetAt(i);

            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {

                List<String> rowData = new ArrayList<>();

                Row row = rowIterator.next();

                Iterator<Cell> cellIterator = row.iterator();

                while (cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }

                excelData.add(rowData);
            }
        }

        workbook.close();
        inputStream.close();

        return excelData;

    }


    @Override
    public String retournescoredesatisfactionclient(String filepath, String filepathneutre, String filepathnegatifs) throws IOException {
        int borneInf = 0;
        int borneSup = 20;
        List<Comment> comment = mdserv.getAllComment();
        double moyenne = 0.0;
        int score = 35;
        int scorenegatifs = -92;
        int scoreneutre = 0;
        String message = "";
        for (Comment comment1 : comment) {
            String[] chaine = comment1.getText().split(" ");// recuperit un tableau contenant les mots de chaque commentaire
            for (String ch : chaine) {
                for (List<String> unelementdelistemotspositifs : readExcel(filepath)) {
                    for (List<String> unelementdelistemotneutre : readExcelmotsneutres(filepathneutre)) {
                        for (List<String> unelementdelistemotnegatifs : readExcelmotsnegatifs(filepathnegatifs)) {
                            for (String lachainevoulupositifs : unelementdelistemotspositifs) {
                                for (String lachainevouluneutre : unelementdelistemotneutre) {
                                    for (String lachainevoulunegatifs : unelementdelistemotnegatifs) {
                                        if (lachainevoulupositifs.equals(ch)) {
                                            score++;
                                            if (lachainevouluneutre.equals(ch)) {
                                                scoreneutre++;
                                            }
                                            if (lachainevoulunegatifs.equals(ch)) {
                                                scorenegatifs++;
                                            }
                                        }
                                    }

                                }
                            }
                        }
                        moyenne = (score + scoreneutre + scorenegatifs) / 3;
                        if (moyenne < 0) {
                            return message.concat("le client n'est pas satisfait du tout du site et ses services");
                        } else if (moyenne >= borneInf && moyenne <= borneSup) {
                            return message.concat("leclient est moyennement satisfait ");
                        } else if (moyenne > borneSup) {
                            return message.concat("le client est tres satisfait du siyte et ses services");
                        }
                    }
                }
            }
        }
        return message;
    }

    @Override
    public String lemeilleureemployedeumois(){
        String message="";
        List<User> listedesusers=userserv.retrieveAllUsers();
        List<Intervention> listeinterventions= interserv.listedesinetrventions();
        for (User user : listedesusers){
            int nbrpoint = user.getNbrpoints();
            for (Intervention intervention :listeinterventions) {
                LocalDate date = LocalDate.of(2023, 3, 1);
                LocalDate localDateDebut = new java.sql.Date(intervention.getDatedebinter().getTime()).toLocalDate();


                if (intervention.getUserrrr().getId().equals(user.getId()) && localDateDebut.isAfter(date) && calculerDateFinIntervention(intervention.getId()).isBefore(LocalDate.now())) {
                    nbrpoint=nbrpoint+1;
                }
            }
            user.setNbrpoints(nbrpoint);
            userrepo.save(user);

        }
        for (User i : listedesusers) {
            for (User j : listedesusers) {
                if (i.getNbrpoints()>j.getNbrpoints()) {
                    return message+i.getFirstName()+"estlemeilleur";
                } else {
                    return message+j.getFirstName()+"estlemeilleur";
                }
            }
        }
        return message ;
    }
    }

