package com.example.marketplace.repository;

import com.example.marketplace.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserRepository extends CrudRepository<User,Integer> {
}
