package com.example.marketplace.repository;

import com.example.marketplace.entities.Contract;
import org.springframework.data.repository.CrudRepository;

public interface IContractRepository extends CrudRepository<Contract,Integer> {
}
