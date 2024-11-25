package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.auth.UserCreateDto;
import com.example.Tez_Yetkaz.dto.auth.UserDto;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.enums.RoleType;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.repository.RoleRepository;
import com.example.Tez_Yetkaz.util.MessageKey;
import com.example.Tez_Yetkaz.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        user.setPhoneNumber(userCreateDto.getPhoneNumber());
        user.setBirthDate(userCreateDto.getBirthDate());
        user.setGender(userCreateDto.getGender());
        user.setRole(roleRepository.findByRoleType(RoleType.USER).orElseThrow(
                () -> new NotFoundException(MessageService.getMessage(MessageKey.ROLE_NOT_FOUND))));
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setGender(user.getGender());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
