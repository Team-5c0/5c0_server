package com.example.wllserver.domain.user.domain.entity;

import com.example.wllserver.domain.user.domain.enums.UserType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@SuperBuilder
public class UserEntity {

    @Id
    private Long userid;

    private String username;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}
