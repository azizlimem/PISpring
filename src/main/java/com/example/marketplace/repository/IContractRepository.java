package com.example.marketplace.repository;

import com.example.marketplace.entities.Contract;
import com.example.marketplace.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IContractRepository extends CrudRepository<Contract,Integer> {

    @Query("SELECT c FROM Contract c WHERE c.discount=(SELECT MAX(discount) FROM Contract)")
    List<Contract> maxDiscount();


}
