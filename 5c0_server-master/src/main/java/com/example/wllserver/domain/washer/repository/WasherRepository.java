package com.example.wllserver.domain.washer.repository;

import com.example.wllserver.domain.washer.domain.entity.RoomEntity;
import com.example.wllserver.domain.washer.domain.entity.WasherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasherRepository extends JpaRepository<WasherEntity, Long> {
    List<WasherEntity> findByRoomEntity(RoomEntity roomEntity);
}

