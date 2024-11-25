package com.example.Tez_Yetkaz.mapper;

import com.example.Tez_Yetkaz.dto.auth.RegistrationRequest;
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

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User toEntity(RegistrationRequest registrationRequest) {
        User user = new User();
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setPhoneNumber(registrationRequest.getPhoneNumber());
        user.setRole(roleRepository.findByRoleType(RoleType.USER).orElseThrow(
                () -> new NotFoundException(MessageService.getMessage(MessageKey.ROLE_NOT_FOUND))));
        return user;
    }

    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public User updateEntity(User user, UserCreateDto userCreateDto) {
        if (userCreateDto.getFirstName() != null) user.setFirstName(userCreateDto.getFirstName());
        if (userCreateDto.getLastName() != null) user.setLastName(userCreateDto.getLastName());
        if (userCreateDto.getPhoneNumber() != null)user.setPhoneNumber(userCreateDto.getPhoneNumber());
        if (userCreateDto.getGender() != null)user.setGender(userCreateDto.getGender());
        if (userCreateDto.getEmail() != null)user.setEmail(userCreateDto.getEmail());
        if (userCreateDto.getBirthDate() != null)user.setBirthDate(userCreateDto.getBirthDate());
        return user;
    }

    public UserDto userToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setRole(user.getRole());
        userDto.setGender(user.getGender());
        userDto.setEmail(user.getEmail());
        userDto.setBirthDate(user.getBirthDate());
        return userDto;
    }

    public List<UserDto> userToDto(List<User> users) {
        List<UserDto> userDto = new ArrayList<>();
        for (User user : users) {
            userDto.add(userToDto(user));
        }
        return userDto;
    }
}
