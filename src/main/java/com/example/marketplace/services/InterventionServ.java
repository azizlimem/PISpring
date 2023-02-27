package com.example.marketplace.services;

import com.example.marketplace.entities.Intervention;
import com.example.marketplace.repository.IinterventionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InterventionServ  implements  IInterventionServ{
    @Autowired
    IinterventionRepo interrepo;

    @Override
    public Intervention ajouterintervention(Intervention i ){
        return interrepo.save(i);
    }
    @Override
    public void deleteByIdd(Long id) {
        interrepo.deleteById(id);
    }

    @Override
    public List<Intervention> listedesinetrventions(){
        return interrepo.findAll();
    }
    @Override
    public  void updateintervention(Long id,Integer dureeinter){
        interrepo.updateintervention(id,dureeinter);}
    @Override
    public Intervention updateintervention2 (Long id, Intervention  i) {
        if (interrepo.findById(id).isPresent()){
            Intervention interv = interrepo.findById(id).get();

            interv.setDureeinter(i.getDureeinter());
            interv.setDatedebinter(i.getDatedebinter());
            Intervention updatedinterention = interrepo.save(interv);
            return updatedinterention;
        }else{
            return null;
        }
    }
}
