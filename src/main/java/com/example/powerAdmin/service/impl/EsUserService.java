package com.example.powerAdmin.service.impl;

import com.example.powerAdmin.entity.EsUser;
import com.example.powerAdmin.service.EsUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsUserService {

    @Autowired
    private EsUserRepository userRepository;

    public Object saveUser(EsUser user) {
        return userRepository.save(user);
    }

    public Iterable<EsUser> findAllUsers() {
        return userRepository.findAll();
    }

    public EsUser findUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}