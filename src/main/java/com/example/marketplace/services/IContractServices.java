package com.example.marketplace.services;

import com.example.marketplace.entities.Contract;

import java.util.List;

public interface IContractServices {
    List<Contract> retrieveAllContracts();

    Contract addContract (Contract c , Integer id);

    String updateContract (Contract c,Integer nbr);

    Contract retrieveContract(Integer idContract);

    void removeContract(Integer idContract);
}
