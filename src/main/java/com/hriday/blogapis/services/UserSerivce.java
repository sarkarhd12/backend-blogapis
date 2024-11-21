package com.hriday.blogapis.services;

import com.hriday.blogapis.payloads.UserDto;
import com.hriday.blogapis.repositories.UserRepo;

import java.util.List;

public interface UserSerivce {

    UserDto registerUser(UserDto user);

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);
}
