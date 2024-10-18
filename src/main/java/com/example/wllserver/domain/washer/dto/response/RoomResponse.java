package com.example.wllserver.domain.washer.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {
    private Long roomid;

    public RoomResponse(Long roomid) {
        this.roomid = roomid;
    }

}
