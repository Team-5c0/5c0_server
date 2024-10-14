package com.example.wllserver.repository;

import com.example.wllserver.Entity.RoomEntity;
import com.example.wllserver.Entity.WasherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasherRepository extends JpaRepository<WasherEntity, Long> {
    List<WasherEntity> findByRoomEntity(RoomEntity roomEntity);
}

