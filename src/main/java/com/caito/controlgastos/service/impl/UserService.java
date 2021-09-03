package com.caito.controlgastos.service.impl;

import com.caito.controlgastos.constants.ConstantsExceptionMessages;
import com.caito.controlgastos.dto.NewUser;
import com.caito.controlgastos.dto.UserResponse;
import com.caito.controlgastos.entity.Role;
import com.caito.controlgastos.entity.User;
import com.caito.controlgastos.enums.RoleName;
import com.caito.controlgastos.exceptions.customs.BadRequestException;
import com.caito.controlgastos.repository.RoleRepository;
import com.caito.controlgastos.repository.UserRepository;
import com.caito.controlgastos.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findByUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserResponse createUser(NewUser newUser) {

        if (newUser.getUserName() == null || newUser.getUserName() == ""){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_USER_USERNAME_EMPTY);
        }
        if (newUser.getEmail() == null || newUser.getEmail() == ""){
            throw  new BadRequestException(ConstantsExceptionMessages.MSG_USER_EMAIL_EMPTY);
        }
        if (newUser.getPassword() == null || newUser.getPassword() == ""){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_USER_PASSWORD_EMPTY);
        }
        if (userRepository.existsByUserName(newUser.getUserName())){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_USER_USERNAME_EXISTS);
        }
        if (userRepository.existsByEmail(newUser.getEmail())){
            throw new BadRequestException(ConstantsExceptionMessages.MSG_USER_EMAIL_EXIST);
        }

        ModelMapper mapper = new ModelMapper();
        User user = mapper.map(newUser, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();

        if (newUser.getRoles().contains("admin")){
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_ADMIN).get());
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).get());
        }else {
            roles.add(roleRepository.findByRoleName(RoleName.ROLE_USER).get());
        }
        user.setRoles(roles);
        userRepository.save(user);
        return mapper.map(user, UserResponse.class);
    }
}
