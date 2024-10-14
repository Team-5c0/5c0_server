package com.example.wllserver.controller;

import com.example.wllserver.Entity.WasherEntity;
import com.example.wllserver.dto.RoomResponse;
import com.example.wllserver.service.WashService;
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
    private WashService washService;

    @GetMapping("/rooms")
    public List<RoomResponse> getAllRooms() {
        return washService.getAllRooms();
    }

    @GetMapping("/washers")
    public List<Map<String, Object>> getWashersByRoom(@RequestParam Long roomId) {
        List<WasherEntity> washers = washService.getWashersByRoom(roomId);
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

    @PostMapping("/usewasher")
    public ResponseEntity<?> startWashing(@RequestParam Long washerId,
                                          @RequestParam Long userId,
                                          @RequestParam int washMinutes) {
        washService.startWashing(washerId, userId, washMinutes);
        return ResponseEntity.ok().build();
    }
}
