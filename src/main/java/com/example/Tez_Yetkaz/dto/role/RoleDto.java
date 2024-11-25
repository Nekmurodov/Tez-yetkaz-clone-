package com.example.Tez_Yetkaz.dto.role;

import com.example.Tez_Yetkaz.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
    private UUID roleId;
    private String roleName;
    private RoleType roleType;
}
