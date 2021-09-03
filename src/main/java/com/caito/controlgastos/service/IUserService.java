package com.caito.controlgastos.service;

import com.caito.controlgastos.dto.NewUser;
import com.caito.controlgastos.dto.UserResponse;
import com.caito.controlgastos.entity.User;

public interface IUserService {

    public User findByUserByUserName(String userName);
    public UserResponse createUser(NewUser newUser);
}
