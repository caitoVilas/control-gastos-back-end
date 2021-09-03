package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.entity.User;
import com.caito.controlgastos.repository.UserRepository;
import com.caito.controlgastos.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;




    @Override
    public User findByUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
