package com.example.marketplace.contollers;

import com.example.marketplace.entities.Charity;
import com.example.marketplace.entities.Commande;
import com.example.marketplace.services.ICharityServ;
import com.example.marketplace.services.ICommandeServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/CommandeCh")
@Tag(name = "commandeCh")
public class CharityController {
    private final ICharityServ charityServ;





    @Operation(description = "Add charity")
    @PutMapping("/add/{id}")
    Charity addCharity(@RequestBody Charity charity,@PathVariable("id") Integer idCh){
        return charityServ.addCharity(charity,idCh);

    }

    @Operation (description = "Update charity")
    @PutMapping("/update")
    Charity updateCharity(@RequestBody Charity charity){
        return  charityServ.updateCharity(charity);
    }

    @Operation (description = "afficher  charity")
    @GetMapping("/get/{id}")
    Charity getCharity(@PathVariable("id") Integer id){

        return charityServ.retrieveCharity(id);
    }

    @Operation (description = "Delete  charity")
    @DeleteMapping("/delete/{id}")
    void deleteCharity(@PathVariable("id") Integer id){

        charityServ.removeCharity(id);
    }

}
