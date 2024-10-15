package com.example.wllserver.domain.washer.domain.entity;

import com.example.wllserver.domain.user.domain.entity.UserEntity;
import com.example.wllserver.domain.washer.domain.enums.WasherType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "washer")
@SuperBuilder
public class WasherEntity {

    @Id
    private Long washerid;

    @Enumerated(EnumType.STRING)
    private WasherType washerType; // 세탁기인지 건조기인지

    private boolean available; // 사용 가능한지 여부

    private LocalDateTime endTime; // 끝나는 시간

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user; // 사용하고 있는 사람

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity roomEntity;

    public long getRemainingTime() {
        return endTime != null ? Duration.between(LocalDateTime.now(), endTime).toMinutes() : 0;
    }

}
