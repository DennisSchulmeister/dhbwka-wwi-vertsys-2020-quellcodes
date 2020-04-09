package com.example.demo.api.users.service;

import com.example.demo.api.users.data.UserRepository;
import com.example.demo.api.users.web.UserModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

/**
 * Kapselt die Datentransformation der Benutzer und bietet auch Raum für notwendige Business Logik, wie z.B. Autorisierungspruefungen.
 * 
*/
@Service
public class UserFacade {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserModel getUserById(Long id) {
        return userRepository.findById(id).map(userMapper::toModel)
                .orElseThrow(() -> new RuntimeException("No user found with id " + id));
    }

    public CollectionModel<UserModel> getUsers() {
        return userMapper.toCollectionModel(userRepository.findAll());
    }

}