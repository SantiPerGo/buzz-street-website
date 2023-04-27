package com.company.buzzstreet.service;

import com.company.buzzstreet.entity.User;
import com.company.buzzstreet.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> createUser(User user){
        Optional<User> optionalUser = userRepository.findByNameAndEmail(user.getName(), user.getEmail());

        if(optionalUser.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            User newUser = User.builder()
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();

            if(user.getImgUrl() != null)
                newUser.setImgUrl(user.getImgUrl());

            return new ResponseEntity<>(userRepository.saveAndFlush(newUser), HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id){
        userRepository.deleteById(id);
        return new ResponseEntity<>("{\n\t\"success:\" true\n}", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(User user) {
        return userRepository.findByNameAndEmail(user.getName(), user.getEmail())
                .map(users -> new ResponseEntity<>(users, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<User> updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            user.setId(optionalUser.get().getId());
            return new ResponseEntity<>(userRepository.saveAndFlush(user), HttpStatus.OK);
        }
        else { return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); }
    }

}

