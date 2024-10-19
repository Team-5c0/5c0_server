package com.example.wllserver.domain.auth.service;

import com.example.wllserver.domain.auth.register.dto.request.RegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> joinUser(RegisterRequest registerRequest);
    ResponseEntity<?> loginUser(Long userId);
}
