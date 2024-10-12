package com.example.wllserver.service;

import com.example.wllserver.Entity.UserEntity;
import com.example.wllserver.dto.ErrorResponse;
import com.example.wllserver.dto.register.RegisterRequest;
import com.example.wllserver.dto.register.RegisterResponse;
import com.example.wllserver.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> joinUser(RegisterRequest registerRequest) {
        if(userRepository.findByUserid(registerRequest.userid()).isPresent()){
            return ResponseEntity.badRequest().body(new ErrorResponse("join failed","이미 사용중인 아이디입니다."));
        }
        UserEntity userEntity = UserEntity.builder()
                .userid(registerRequest.userid())
                .username(registerRequest.username())
                .build();

        log.info("2");
        userRepository.save(userEntity);
        log.info("3");
        return ResponseEntity.ok(new RegisterResponse("join successful"));
    }
}
