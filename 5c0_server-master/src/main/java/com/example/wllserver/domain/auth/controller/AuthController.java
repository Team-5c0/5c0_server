package com.example.wllserver.domain.auth.controller;

import com.example.wllserver.domain.auth.dto.register.RegisterRequest;
import com.example.wllserver.domain.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "학번으로 로그인", description = "학번을 입력해서 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam Long userId){
        return authService.loginUser(userId);
    }

}
