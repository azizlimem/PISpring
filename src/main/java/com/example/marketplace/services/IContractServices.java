package com.example.marketplace.services;

import com.example.marketplace.entities.Contract;

import java.util.List;

public interface IContractServices {
    List<Contract> retrieveAllContracts();

    Contract addContract (Contract c);

    Contract updateContract (Contract c);

    Contract retrieveContract(Integer idContract);

    void removeContract(Integer idContract);
}
