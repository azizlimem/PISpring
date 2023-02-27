package com.example.marketplace.services;

import com.example.marketplace.entities.User;
import com.example.marketplace.repository.IUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor

public class UserService  implements IUserServices{

    private final IUserRepository userRepository;

    @Override
    public List<User> retrieveAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User addUser(User u) {

        return userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {

        return userRepository.save(u);
    }

    @Override
    public User retrieveUser(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }
    @Override
    public void removeUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }
}
