package com.example.Tez_Yetkaz.entity.user;

import com.example.Tez_Yetkaz.entity.AbsEntity;
import com.example.Tez_Yetkaz.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role extends AbsEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;
}
