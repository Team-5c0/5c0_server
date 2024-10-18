package com.example.wllserver.domain.washer.service.impl;

import com.example.wllserver.domain.washer.domain.entity.RoomEntity;
import com.example.wllserver.domain.user.domain.entity.UserEntity;
import com.example.wllserver.domain.washer.domain.entity.WasherEntity;
import com.example.wllserver.domain.washer.dto.response.RoomResponse;
import com.example.wllserver.domain.washer.repository.RoomRepository;
import com.example.wllserver.domain.user.repository.UserRepository;
import com.example.wllserver.domain.washer.repository.WasherRepository;
import com.example.wllserver.domain.washer.service.WasherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Slf4j
public class WasherServiceImpl implements WasherService {
    private RoomRepository roomRepository;
    private WasherRepository washerRepository;
    private UserRepository userRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public List<RoomResponse> getAllRooms() {

        List<RoomEntity> rooms = roomRepository.findAll();

        return rooms.stream()
                .map(room -> new RoomResponse(room.getRoomid()))
                .collect(Collectors.toList());

    }

    public List<WasherEntity> getWashersByRoom(Long roomId) {

        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("세탁실을 찾을 수 없습니다."));

        return washerRepository.findByRoomEntity(room);

    }

    public void startWashing(Long washerId, Long userid, int washMinutes) {

        log.info("start washing");

        WasherEntity washer = washerRepository.findById(washerId)
                .orElseThrow(() -> new IllegalArgumentException("세탁기를 찾을 수 없습니다."));

        UserEntity user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        washer.setAvailable(false);
        washer.setEndTime(LocalDateTime.now().plusMinutes(washMinutes));
        washer.setUser(user);

        washerRepository.save(washer);

        long washingTime = (long) washMinutes;
        scheduler.schedule(() -> completeWashing(washerId), washingTime, TimeUnit.MINUTES);

    }

    private void completeWashing(Long washerId) {

        log.info("complete washing");

        WasherEntity washer = washerRepository.findById(washerId)
                .orElseThrow(() -> new IllegalArgumentException("세탁기를 찾을 수 없습니다."));

        washer.setAvailable(true);
        washer.setEndTime(null);
        washer.setUser(null);

        washerRepository.save(washer);

        // TODO: 여기에 알람 보내는 로직 추가하기
    }
}