package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.role.CreateRoleDto;
import com.example.Tez_Yetkaz.dto.role.RoleDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/role/")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create")
    public ResponseData<?> creat(@RequestBody CreateRoleDto createRoleDto) {
        return this.roleService.create(createRoleDto);
    }

//    @CheckPermission("EDIT_USER")
    @PutMapping("update/{roleId}")
    public ResponseData<?> update(@PathVariable UUID roleId, RoleDto roleDto) {
        return this.roleService.update(roleId,roleDto);
    }

    @GetMapping("get/{roleId}")
    public ResponseData<?> get(@PathVariable UUID roleId) {
        return this.roleService.get(roleId);
    }

    @GetMapping("/get-all")
    public ResponseData<?> getAllEmployee() {
        return this.roleService.getAll();
    }

    @DeleteMapping("delete/{roleId}")
    public ResponseData<?> delete(@PathVariable UUID roleId) {
        return this.roleService.delete(roleId);
    }

}
