package com.example.Tez_Yetkaz.service;

import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.repository.UserRepository;
import com.example.Tez_Yetkaz.util.MessageKey;
import com.example.Tez_Yetkaz.util.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email.isBlank() || email.isEmpty()){
            throw new UsernameNotFoundException(MessageService.getMessage(MessageKey.NULL_USERNAME_FROM_TOKEN));
        }
        return userRepository.findByEmailAndDeletedFalse(email).orElseThrow(
                () -> new NotFoundException(MessageService.getMessage(MessageKey.USERNAME_NOT_FOUND)));

    }
}
