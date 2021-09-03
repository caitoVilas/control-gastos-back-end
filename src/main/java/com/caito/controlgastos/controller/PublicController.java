package com.caito.controlgastos.controller;

import com.caito.controlgastos.dto.JwtDto;
import com.caito.controlgastos.dto.NewUser;
import com.caito.controlgastos.dto.UserLogin;
import com.caito.controlgastos.dto.UserResponse;
import com.caito.controlgastos.entity.User;
import com.caito.controlgastos.security.jwt.JwtUtil;
import com.caito.controlgastos.service.impl.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/expenses/public")
@CrossOrigin
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;

    @PostMapping("/createuser")
    public ResponseEntity<UserResponse> createUser(@RequestBody NewUser newUser){

        return new ResponseEntity<UserResponse>(userService.createUser(newUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody UserLogin loginUser){
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        loginUser.getUserName(), loginUser.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(userDetails);
        ModelMapper mapper = new ModelMapper();
        User user = userService.findByUserByUserName(loginUser.getUserName());
        UserResponse userResponse = mapper.map(user, UserResponse.class);
        JwtDto jwtDto = new JwtDto(jwt, userResponse);
        return new ResponseEntity<JwtDto>(jwtDto, HttpStatus.OK);
    }
}
