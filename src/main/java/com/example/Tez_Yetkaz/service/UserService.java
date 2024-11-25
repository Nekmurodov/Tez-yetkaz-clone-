package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.dto.auth.UserCreateDto;
import com.example.Tez_Yetkaz.dto.auth.UserDto;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.UserMapper;
import com.example.Tez_Yetkaz.repository.UserRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public ResponseData<?> updateUser(UUID userId, UserCreateDto userCreateDto) {
        Optional<User> userOptional = userRepository.findByIdAndDeletedFalse(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = this.userMapper.updateEntity(userOptional.get(),userCreateDto);
        this.userRepository.save(user);
        return ResponseData.successResponse(this.userMapper.userToDto(user));
    }

    public ResponseData<?> deleteUser(UUID userId) {
        Optional<User> userOptional = userRepository.findByIdAndDeletedFalse(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        User user = userOptional.get();
        user.setDeleted(true);
        this.userRepository.save(user);
        return ResponseData.successResponse("success");
    }

    public ResponseData<UserDto> getUser(UUID userId) {
        Optional<User> userOptional = userRepository.findByIdAndDeletedFalse(userId);
        if (userOptional.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return ResponseData.successResponse(this.userMapper.userToDto(userOptional.get()));
    }

    public ResponseData<?> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = this.userRepository.findAllByDeletedFalse(pageable);
        if (users.isEmpty()) {
            throw new NotFoundException("User not found");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", userMapper.userToDto(users.toList()));
        response.put("total", users.getTotalElements());
        response.put("totalPages", users.getTotalPages());

        return ResponseData.successResponse(response);
    }

}
