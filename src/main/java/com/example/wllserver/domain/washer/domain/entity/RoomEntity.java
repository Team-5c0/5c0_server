package com.example.wllserver.domain.washer.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "room")
@SuperBuilder
public class RoomEntity {

    @Id
    private Long roomid;

    @OneToMany(mappedBy = "roomEntity")
    private List<WasherEntity> washers;

}
