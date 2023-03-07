package com.example.marketplace.services;

import com.example.marketplace.entities.Contract;
import com.example.marketplace.repository.IContractRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ContractService implements IContractServices {

    private final IContractRepository contractRepository;

    @Override
    public List<Contract> retrieveAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        contractRepository.findAll().forEach(contracts::add);
        return contracts;
    }

    @Override
    public Contract addContract(Contract c) {

        return contractRepository.save(c);
    }

    @Override
    public Contract updateContract(Contract c) {

        return contractRepository.save(c);
    }

    @Override
    public Contract retrieveContract(Integer idContract) {
        return contractRepository.findById(idContract).orElse(null);
    }
    @Override
    public void removeContract(Integer idContract) {
        contractRepository.deleteById(idContract);
    }

}
