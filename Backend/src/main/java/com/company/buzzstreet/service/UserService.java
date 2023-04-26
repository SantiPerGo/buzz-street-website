package com.company.buzzstreet.service;

import com.company.buzzstreet.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<List<User>> getUsers();
    ResponseEntity<User> createUser(User user);
    ResponseEntity<String> deleteUser(Long id);

    ResponseEntity<User> getUser(User user);
    ResponseEntity<User> updateUser(Long id, User visitor);
}
