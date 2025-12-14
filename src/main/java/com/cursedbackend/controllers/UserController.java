package com.cursedbackend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursedbackend.dtos.ErrorDto;
import com.cursedbackend.dtos.LoginDto;
import com.cursedbackend.dtos.ResponseDto;
import com.cursedbackend.dtos.UserDto;
import com.cursedbackend.jwt.JwtUtils;
import com.cursedbackend.services.UserService;

// import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, JwtUtils jwtUtils, ModelMapper modelMapper) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerUserEntity(@RequestBody UserDto userDto) {
        Optional<UserDto> user = userService.registerUser(userDto);

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ResponseDto.builder()
                            .success(true)
                            .data(user.get())
                            .build());
        }

        return ResponseEntity.badRequest()
                .body(ResponseDto.builder()
                        .success(false)
                        .error(new ErrorDto("User already exists", null))
                        .build());
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        var token = userService.login(loginDto);

        if (token.isPresent()) {
            return ResponseEntity.ok(
                    ResponseDto.builder()
                            .success(true)
                            .data(Map.of("token", token.get()))
                            .build());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .success(false)
                        .error(new ErrorDto("Invalid email or password", null))
                        .build());
    }

    @PostMapping("/verifyToken")
    public ResponseEntity<ResponseDto> verifyToken(@RequestBody String token) {
        boolean isValid = jwtUtils.verifySignature(token);

        return ResponseEntity.ok(
                ResponseDto.builder()
                        .success(isValid)
                        .data(Map.of("valid", isValid))
                        .build());
    }

    @PreAuthorize("isAuthenticated()")
//     @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    public ResponseEntity<ResponseDto> getAllUsers() {
        return ResponseEntity.ok(
                ResponseDto.builder()
                        .success(true)
                        .data(userService.getALlUsers())
                        .build());
    }

    @PreAuthorize("isAuthenticated()")
//     @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/loggedin-user")
    public ResponseEntity<ResponseDto> getUserDetails(HttpServletRequest request) {
        var user = userService.getUserByEmail(request.getUserPrincipal().getName());

        if (user.isPresent()) {
            user.get().setPassword(null);
            return ResponseEntity.ok(
                    ResponseDto.builder()
                            .success(true)
                            .data(modelMapper.map(user.get(), UserDto.class))
                            .build());
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(ResponseDto.builder()
                        .success(false)
                        .error(new ErrorDto("User not found", null))
                        .build());
    }
}
