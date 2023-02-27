package com.example.marketplace.services;

import com.example.marketplace.entities.Product;
import com.example.marketplace.repository.IProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ProductServ implements IProductServ{
    @Autowired
    IProductRepo iProductRepo;
    @Override
    public Product addProduct(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public List<Product> findAllProduct() {
       List<Product> prod =new ArrayList<>();
       iProductRepo.findAll().forEach(prod::add);
        return prod;
    }

    @Override
    public Product updateProduct(Product product) {
        return iProductRepo.save(product);
    }

    @Override
    public Product findById(Integer id) {
        return iProductRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(Integer id) {
        iProductRepo.deleteById(id);

    }
}
