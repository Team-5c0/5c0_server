package com.example.wllserver.controller;

import com.example.wllserver.dto.register.RegisterRequest;
import com.example.wllserver.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> joinUser(@RequestBody RegisterRequest registerRequest) {
        return authService.joinUser(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam Long userId){
        return authService.loginUser(userId);
    }

}
