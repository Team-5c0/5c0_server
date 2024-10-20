package com.example.wllserver.domain.auth.service.impl;

import com.example.wllserver.domain.auth.service.AuthService;
import com.example.wllserver.domain.user.domain.entity.UserEntity;
import com.example.wllserver.domain.user.domain.enums.UserType;
import com.example.wllserver.global.exception.ErrorResponse;
import com.example.wllserver.domain.auth.register.dto.response.LoginResponse;
import com.example.wllserver.domain.auth.register.dto.request.RegisterRequest;
import com.example.wllserver.domain.auth.register.dto.response.RegisterResponse;
import com.example.wllserver.global.security.jwt.JwtUtil;
import com.example.wllserver.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ResponseEntity<?> joinUser(RegisterRequest registerRequest) {
        if(userRepository.findByUserid(registerRequest.userid()).isPresent()){
            return ResponseEntity.badRequest().body(new ErrorResponse("join failed","이미 등록된 학번입니다."));
        }
        UserEntity userEntity = UserEntity.builder()
                .userid(registerRequest.userid())
                .username(registerRequest.username())
                .userType(UserType.valueOf(registerRequest.usertype()))
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
