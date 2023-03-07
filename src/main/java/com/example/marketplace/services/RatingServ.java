package com.example.marketplace.services;

import com.example.marketplace.entities.Product;
import com.example.marketplace.entities.Rating;
import com.example.marketplace.entities.User;
import com.example.marketplace.repository.IProductRepo;
import com.example.marketplace.repository.IRatingRepo;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class RatingServ implements IRatingServ {
    @Autowired
    IRatingRepo iRatingRepo;
    @Autowired
    IProductRepo iProductRepo;
    @Autowired
    IUserRepository iUserRepository;

    @Override
    public Rating addRating(Rating rating) {
        return iRatingRepo.save(rating);
    }

    @Override
    public List<Rating> findAllRating() {
        List<Rating> rat =new ArrayList<>();
        iRatingRepo.findAll().forEach(rat::add);
        return rat;
    }

    @Override
    public Rating updateRating(Rating rating) {
        return iRatingRepo.save(rating);
    }

    @Override
    public Rating findById(Integer id) {
        return iRatingRepo.findById(id).orElse(null);
    }

    @Override
    public void deleteRating(Integer id) {
        iRatingRepo.deleteById(id);
    }

    @Override
    public Rating addAndassignRatingToProductAndUser(Rating r, Integer idProd, Integer idUser) {

            Product product  = iProductRepo.findById(idProd).orElse(null);
            User user = iUserRepository.findById(idUser).orElse(null);
            if(r.getUsers()==null){
                Set<User> users = new HashSet<>();
                users.add(user);
                r.setUsers(users);
            }else{
                r.getUsers().add(user);
            }
            if(r.getProducts()==null){
                Set<Product> c = new HashSet<>();
                c.add(product);
                r.setProducts(c);
            }else{
                r.getProducts().add(product);
            }
            return iRatingRepo.save(r);
        }

    @Override
    public float RatingCalcul(Integer id) {
        return iRatingRepo.NbRating(id);
    }


}
