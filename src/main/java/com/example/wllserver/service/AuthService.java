package com.example.wllserver.service;

import com.example.wllserver.Entity.UserEntity;
import com.example.wllserver.dto.ErrorResponse;
import com.example.wllserver.dto.LoginResponse;
import com.example.wllserver.dto.register.RegisterRequest;
import com.example.wllserver.dto.register.RegisterResponse;
import com.example.wllserver.jwt.JwtUtil;
import com.example.wllserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> joinUser(RegisterRequest registerRequest) {
        if(userRepository.findByUserid(registerRequest.userid()).isPresent()){
            return ResponseEntity.badRequest().body(new ErrorResponse("join failed","이미 등록된 학번입니다."));
        }
        UserEntity userEntity = UserEntity.builder()
                .userid(registerRequest.userid())
                .username(registerRequest.username())
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(new RegisterResponse("join successful"));
    }

    public ResponseEntity<?> loginUser(Long userId){
        UserEntity user = userRepository.findByUserid(userId).orElse(null);

        if(user == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse("login failed", "유저를 찾을 수 없습니다."));
        }

        String token = jwtUtil.generateToken(user.getUserid().toString());

        return ResponseEntity.ok(new LoginResponse(token, "bearer"));
    }
}
