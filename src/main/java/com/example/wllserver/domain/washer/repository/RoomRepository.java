package com.example.wllserver.domain.washer.repository;

import com.example.wllserver.domain.washer.domain.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
}
