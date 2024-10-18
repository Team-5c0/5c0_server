package com.example.wllserver.domain.washer.controller;

import com.example.wllserver.domain.washer.domain.entity.WasherEntity;
import com.example.wllserver.domain.washer.dto.response.RoomResponse;
import com.example.wllserver.domain.washer.service.WasherServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/wash")
public class WashController {

    @Autowired
    private WasherServiceImpl washerServiceImpl;

    @Operation(summary = "세탁실 조회", description = "Returns a list of example data")
    @GetMapping("/rooms")
    public List<RoomResponse> getAllRooms() {
        return washerServiceImpl.getAllRooms();
    }

    @Operation(summary = "세탁기 조회", description = "roomid를 입력하면 해당 세탁실에 있는 세탁기들을 조회")
    @GetMapping("/washers")
    public List<Map<String, Object>> getWashersByRoom(@RequestParam Long roomId) {
        List<WasherEntity> washers = washerServiceImpl.getWashersByRoom(roomId);
        return washers.stream().map(washer -> {
            Map<String, Object> washerInfo = new HashMap<>();
            washerInfo.put("washerId", washer.getWasherid());
            washerInfo.put("washerType", washer.getWasherType());
            washerInfo.put("available", washer.isAvailable());

            if (!washer.isAvailable()) {
                washerInfo.put("remainingTime", washer.getRemainingTime());
                washerInfo.put("user", washer.getUser().getUserid());
            }

            return washerInfo;
        }).collect(Collectors.toList());
    }

    @Operation(summary = "세탁기 사용", description = "남은시간은 분단위로 입력 (예: 1시간 30분이면 90입력)")
    @PostMapping("/usewasher")
    public ResponseEntity<?> startWashing(@RequestParam Long washerId,
                                          @RequestParam Long userId,
                                          @RequestParam int washMinutes) {
        washerServiceImpl.startWashing(washerId, userId, washMinutes);
        return ResponseEntity.ok().build();
    }
}
