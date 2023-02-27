package com.example.marketplace.contollers;

import com.example.marketplace.entities.Contract;
import com.example.marketplace.services.IContractServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contract")
@Tag(name = "Contract Management")
public class ContractRestController {
    private final IContractServices contractServices;

    @Operation(description = "Retrieve all contracts")
    @GetMapping("/all")
    List<Contract> getAllContracts(){

        return contractServices.retrieveAllContracts();
    }

    @Operation (description = "Retrieve contract")
    @GetMapping("/get/{id}")
    Contract getContract(@PathVariable("id") Integer idContract){

        return contractServices.retrieveContract(idContract);
    }

    @Operation (description = "Add contract")
    @PostMapping("/add")
    Contract addContract(@RequestBody Contract c){
        return contractServices.addContract(c);
    }

    @Operation (description = "Update contract")
    @PutMapping("/update")
    Contract updateContract(@RequestBody Contract c){
        return contractServices.updateContract(c);
    }

    @Operation (description = "Delete contract")
    @DeleteMapping("/delete/{id}")
    void deleteContract(@PathVariable("id") Integer idContract){
        contractServices.removeContract(idContract);
    }
}
