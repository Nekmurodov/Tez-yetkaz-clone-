package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.role.CreateRoleDto;
import com.example.Tez_Yetkaz.dto.role.RoleDto;
import com.example.Tez_Yetkaz.entity.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleMapper {

    public Role toEntity(CreateRoleDto createRoleDto) {
        Role role = new Role();
        role.setName(createRoleDto.getName());
        role.setRoleType(createRoleDto.getRoleType());
        return role;
    }

    public Role toUpdateEntity(Role role, RoleDto roleDto) {
        if (roleDto.getRoleType() != null)role.setRoleType(roleDto.getRoleType());
        if (roleDto.getRoleName() != null)role.setName(roleDto.getRoleName());
        return role;
    }

    public RoleDto toDto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRoleName(role.getName());
        roleDto.setRoleType(role.getRoleType());
        return roleDto;
    }

    public List<RoleDto> toDto(List<Role> roles) {
        List<RoleDto> roleDto = new ArrayList<>();
        for (Role role : roles) {
            roleDto.add(toDto(role));
        }
        return roleDto;
    }

}
