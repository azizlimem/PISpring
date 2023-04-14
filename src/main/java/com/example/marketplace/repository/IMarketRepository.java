package com.example.marketplace.repository;

import com.example.marketplace.entities.Market;
import org.springframework.data.repository.CrudRepository;

public interface IMarketRepository extends CrudRepository<Market,Integer> {

}
