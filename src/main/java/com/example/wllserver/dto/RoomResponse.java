package com.example.wllserver.dto;

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
