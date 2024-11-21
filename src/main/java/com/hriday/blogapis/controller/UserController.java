package com.hriday.blogapis.controller;


import com.hriday.blogapis.payloads.ApiResponse;
import com.hriday.blogapis.payloads.UserDto;
import com.hriday.blogapis.services.UserSerivce;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;

    //method for create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUser =  this.userSerivce.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    //method for update user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer userId){
        UserDto updatedUser =  this.userSerivce.updateUser(userDto,userId);
        return ResponseEntity.ok(updatedUser);
    }

    //method for delete user by id
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId){
       this.userSerivce.deleteUser(userId);
       return new ResponseEntity(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
    }


    //method for get all user
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return ResponseEntity.ok(this.userSerivce.getAllUser());
    }

    //method for get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userSerivce.getUserById(userId));
    }



}
