package com.hriday.blogapis.security;

import com.hriday.blogapis.entities.User;
import com.hriday.blogapis.exceptions.ResourceNotFoundException;
import com.hriday.blogapis.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User","Email"+username,0));
       return user;
    }
}
