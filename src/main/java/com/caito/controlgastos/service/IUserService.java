package com.caito.controlgastos.service;

import com.caito.controlgastos.entity.User;

public interface IUserService {

    public User findByUserByUserName(String userName);
}
