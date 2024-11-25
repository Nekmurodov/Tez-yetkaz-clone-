package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.role.CreateRoleDto;
import com.example.Tez_Yetkaz.dto.role.RoleDto;
import com.example.Tez_Yetkaz.entity.user.Role;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.RoleMapper;
import com.example.Tez_Yetkaz.repository.RoleRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public ResponseData<?> create(CreateRoleDto createRoleDto) {
        Role role = this.roleMapper.toEntity(createRoleDto);
        this.roleRepository.save(role);
        return ResponseData.successResponse(this.roleMapper.toDto(role));
    }

    public ResponseData<?> update(UUID roleId, RoleDto roleDto) {
        Optional<Role> role = this.roleRepository.findByIdAndDeletedFalse(roleId);
        if (role.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        Role roleEntity = role.get();
        roleEntity = this.roleMapper.toUpdateEntity(roleEntity, roleDto);
        this.roleRepository.save(roleEntity);
        return ResponseData.successResponse(this.roleMapper.toDto(roleEntity));
    }

    public ResponseData<?> get(UUID roleId) {
        Optional<Role> role = this.roleRepository.findByIdAndDeletedFalse(roleId);
        if (role.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        return ResponseData.successResponse(this.roleMapper.toDto(role.get()));
    }

    public ResponseData<?> getAll() {
        List<Role> roles = this.roleRepository.findAllByDeletedFalse();
        if (roles.isEmpty()) {
            throw new NotFoundException("No users found");
        }
        return new ResponseData<>(this.roleMapper.toDto(roles), true);
    }

    public ResponseData<?> delete(UUID roleId) {
        Optional<Role> role = this.roleRepository.findByIdAndDeletedFalse(roleId);
        if (role.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        Role roleEntity = role.get();
        roleEntity.setDeleted(true);
        this.roleRepository.save(roleEntity);
        return ResponseData.successResponse("success");
    }

}
