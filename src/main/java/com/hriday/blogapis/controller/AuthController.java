package com.hriday.blogapis.controller;

import com.hriday.blogapis.entities.User;
import com.hriday.blogapis.exceptions.ApiException;
import com.hriday.blogapis.payloads.JwtAuthRequest;
import com.hriday.blogapis.payloads.JwtAuthResponse;
import com.hriday.blogapis.payloads.UserDto;
import com.hriday.blogapis.security.JWTTokenHelper;
import com.hriday.blogapis.services.UserSerivce;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Autowired
    private UserSerivce userSerivce;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest request
    ){
        this.authenticate(request.getUserName(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUserName());

       String token = this.jwtTokenHelper.generateToken(userDetails);



       JwtAuthResponse response = new JwtAuthResponse();
       response.setToken(token);
       response.setUser(this.modelMapper.map((User)userDetails,UserDto.class));
       return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String userName,String password){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
        try{
            this.authenticationManager.authenticate(authenticationToken);
        }catch (DisabledException e){
            System.out.println("User is disabled");
            throw new ApiException("Inavlid username or password");
        }
    }

    //register new user

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto registerUser = this.userSerivce.registerUser(userDto);
        return new ResponseEntity<UserDto>(registerUser,HttpStatus.CREATED);
    }
}
