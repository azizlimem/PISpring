package com.example.marketplace.services;

import com.example.marketplace.entities.User;
import java.util.List;

public interface IUserServices {
    List<User> retrieveAllUsers();

    User addUser (User u);

    User updateUser (User u);

    User retrieveUser(Integer idUser);

    void removeUser(Integer idUser);
    List<User> rechercheDynamique (String search);
    String Verification( String email,String code);
}
