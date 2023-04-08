package com.example.marketplace.contollers;

import com.example.marketplace.entities.Intervention;
import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Reclamation;
import com.example.marketplace.entities.User;
import com.example.marketplace.enumerations.Statuss;
import com.example.marketplace.enumerations.Sujetrec;
import com.example.marketplace.repository.IUserRepository;
import com.example.marketplace.services.IRecService;
import com.example.marketplace.services.InterventionServ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
public class inter {
    @Autowired
    InterventionServ interserv;
    @Autowired
    IRecService recserv;
    @Autowired
    IUserRepository userRepository;

    @PostMapping("/addintervention")
    public Intervention addintervention(@RequestBody Intervention u ) {
        return interserv.ajouterintervention(u);
    }
    @DeleteMapping("/supprimerinter/{id}")
    public  void delete(@PathVariable("id") Long id ){
        interserv.deleteByIdd(id);
    }
    @GetMapping("/listeinterventions")
    public List<Intervention> listedesinetrventionss(){
        return interserv.listedesinetrventions();
    }
    @PutMapping("/updateintervention/{id}/{dureeinter}")
    public void updateTicket(@PathVariable Long id, @PathVariable int dureeinter) {
        interserv.updateintervention( id,dureeinter);

    }
    @PutMapping("/updateintervention2/{id}")
    public Intervention updateintervention2 ( @PathVariable Long id,@RequestBody Intervention  i){
        return interserv.updateintervention2(id,i);
    }

    @PostMapping("/addreclamation")
    public Reclamation addreclamation(@RequestBody Reclamation i ) {


        return recserv.ajouterreclamation(i);    }
    @DeleteMapping("/reclamationsupprimer/{id}")
    public  void deleterec(@PathVariable("id") Long id ){
        recserv.deleteByIdrec(id);
    }
    @GetMapping("/listereclamations")
    public List<Reclamation> listereclamations(){
        return recserv.listedesreclamations();
    }
    @GetMapping("/listeproduitsimilaires/{idproduitrec}")
    public List<Product> listeproduits(@PathVariable int idproduitrec ){
        return  recserv.afficherproduitssimilaires(idproduitrec);

    }
    //@PutMapping("/updaterrecwithquery/{idrec}/{ticketstatus}")
    //public void updaterec1(@PathVariable Long idrec, @PathVariable Statuss ticketstatus) {
     //   recserv.updatereclamation( idrec,ticketstatus);

    //}
    @PutMapping("/updaterecwithrec/{idrec}")
    public Reclamation updatereccc ( @PathVariable Long idrec,@RequestBody Reclamation  i){
        return recserv.updatereclamation2(idrec,i);
    }
    @GetMapping("/nombredereclamationlivreur")
    public int nbrrec(Long iduser){
        return   recserv.countReclamation(iduser);
    }

    @GetMapping ("/calculsalaire/{iduser}")
    public double retournesalaire (@PathVariable Long iduser ){
        return   recserv.retournesalaire(iduser);
    }
    //@GetMapping("/listeproduits/{idproduitreclame}")
    //public List<Product> afficherproduitsimilaire(@PathVariable int idproduitreclame){
      //  return recserv.afficherproduitssimilaires(idproduitreclame);
    //}

    @GetMapping("/order")
    public   List<Reclamation> order1(){
        return  recserv.order();
    }
    @GetMapping("/calculdeladatedefindeinter/{idinter}")
    public LocalDate calculerDateFinIntervention(@PathVariable Long idinter){
         return recserv.calculerDateFinIntervention(idinter);

    }
    @GetMapping("/nbrdemotsdanspriorite/{idrec}")
    public String nbrdemotsdanspriorite(@PathVariable Long idrec){
        return recserv.compteurdenrbdemots(idrec);

    }
    @GetMapping("/affetcteruserrec/{idrec}/{iduser}")
    public void nbrdemotsdanspriorite(@PathVariable Long idrec,@PathVariable Integer iduser){
         recserv.affecetruserlreclamation(idrec,iduser);

    }
    @GetMapping("/nombredereclamationsproduit/{description}/{id}")
    Integer nombredereclamationdunproduit(@PathVariable Sujetrec description,@PathVariable Integer id){
        return recserv.nombredereclamationdunproduit(description,id);
    }
    @GetMapping("/prixproduits/{description}/{idprodrec}")
   public  void prixproduit (@PathVariable Sujetrec description,@PathVariable Integer idprodrec){
        recserv.prixproduit(  description, idprodrec);
   }
    //@GetMapping("/listemotspositifs/{filePath}")
    //public   List<List<String>> readExcellistemotspositifs(String filePath) throws IOException {
    //    return recserv.readExcel(filePath);
    //}
    @GetMapping("scoresatisfaction/{filepath}/{filepathneutre}/{filepathnegatifs}")
    public  String retournescoredesatisfactionclient1(String filepath,String filepathneutre,String filepathnegatifs) throws IOException {
        return   recserv.retournescoredesatisfactionclient(filepath,filepathneutre,filepathnegatifs);
    }

    @GetMapping("lemeilleuremployebrusque")
    public String lemeilleureemployedeumois(){
        return   recserv.lemeilleureemployedeumois();

    }
    @GetMapping("affecterinterrec/{idinterv}/{idrec}")
    public void affecter(@PathVariable Long idinterv,@PathVariable Long idrec){
        recserv.affecterinterventionareclamation(idinterv,idrec);
    }
    @GetMapping("affecterlgcommandetoreclamation/{idligcmd}/{idrec}")
    public void affecterlg(@PathVariable Integer idligcmd,@PathVariable Long idrec){
        recserv.affecterlignecommandereclamation(idligcmd,idrec);
    }

    @GetMapping("affecteradminto/{iduser}/{idinter}")
    public void affecteradmininter(@PathVariable Integer iduser ,@PathVariable Long idinter){
        interserv.affecteradminintervention(iduser,idinter);
    }


}
