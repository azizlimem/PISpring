package com.example.marketplace.repository;

import com.example.marketplace.entities.Catalogue;
import org.springframework.data.repository.CrudRepository;

public interface ICatalogueRepo extends CrudRepository<Catalogue,Integer> {
}
