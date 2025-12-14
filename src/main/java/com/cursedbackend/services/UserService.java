package com.cursedbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cursedbackend.dtos.LoginDto;
import com.cursedbackend.dtos.UserDto;
import com.cursedbackend.entities.User;

public interface UserService extends UserDetailsService {
    public Optional<UserDto> registerUser(UserDto userDto);
    public Optional<String> login(LoginDto loginDto);
    public Optional<User> getUserByEmail(String email);
    public List<User> getALlUsers();
}
