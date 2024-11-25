package com.example.Tez_Yetkaz.dto.role;

import com.example.Tez_Yetkaz.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleDto {
    private String name;
    private RoleType roleType;
}
