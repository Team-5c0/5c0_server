package com.example.wllserver.domain.washer.service;

import com.example.wllserver.domain.washer.domain.entity.WasherEntity;
import com.example.wllserver.domain.washer.dto.response.RoomResponse;

import java.util.List;

public interface WasherService {
    List<RoomResponse> getAllRooms();
    List<WasherEntity> getWashersByRoom(Long roomId);
    void startWashing(Long washerId, Long userid, int washMinutes);
}
