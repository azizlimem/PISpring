package com.example.marketplace.services;
import com.example.marketplace.entities.Product;

import java.io.Serializable;
import java.util.List;

public interface IProductServ extends Serializable {
    Product addProduct(Product product);

    List<Product> findAllProduct();

    Product updateProduct(Product product);

    Product findById(Integer id);

    void deleteProduct(Integer id);
}
