package com.example.marketplace.contollers;

import com.example.marketplace.entities.Comment;
import com.example.marketplace.entities.Product;
import com.example.marketplace.services.ICommentServ;
import com.example.marketplace.services.IProductServ;
import com.example.marketplace.services.ProductServ;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Product")

public class ProductController {
    final IProductServ iProductServ;


    @GetMapping("/allproducts")
    List<Product> findAllProduct() {
        return iProductServ.findAllProduct();
    }

    @PostMapping("/addproduct")
    Product addProduct(@RequestBody Product p) {
        return iProductServ.addProduct(p);
    }

    @PatchMapping("/updateProduct")
    Product updateProduct(@RequestBody Product p) {
        return iProductServ.updateProduct(p);
    }

    @GetMapping("/getProduct/{id}")
    Product findById(@PathVariable("id") Integer id) {
        return iProductServ.findById(id);
    }

    @DeleteMapping("/deleteProduct/{id}")
    void deleteProduct(@PathVariable("id") Integer id) {
        iProductServ.deleteProduct(id);
    }
}