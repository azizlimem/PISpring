package com.example.marketplace.repository;

import com.example.marketplace.entities.Product;
import com.example.marketplace.enumerations.Statuss;
import com.example.marketplace.enumerations.Sujetrec;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

public interface IProductRepo extends CrudRepository<Product,Integer> {


    @Query("Select count(p)from Reclamation  p inner join p.lgcommande lg inner join lg.products pr where p.description=:description and pr.id=:id ")
Integer nombredereclamationdunproduit (@Param("description") Sujetrec description, @Param("id") Integer id);}


