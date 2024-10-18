package com.example.wllserver.domain.user.repository;

import com.example.wllserver.domain.user.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUserid(Long userid);
}
