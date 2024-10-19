package com.example.wllserver.domain.auth.controller;

import com.example.wllserver.domain.auth.register.dto.request.RegisterRequest;
import com.example.wllserver.domain.auth.service.impl.AuthServiceImpl;
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
    private AuthServiceImpl authServiceImpl;

    @PostMapping("/register")
    public ResponseEntity<?> joinUser(@RequestBody RegisterRequest registerRequest) {
        return authServiceImpl.joinUser(registerRequest);
    }

    @Operation(summary = "학번으로 로그인", description = "학번을 입력해서 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam Long userId){
        return authServiceImpl.loginUser(userId);
    }

}
