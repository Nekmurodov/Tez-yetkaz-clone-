package com.example.Tez_Yetkaz.util;

import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetUserIdFromToken {

    public UUID getUserIdFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            User user = (User) authentication.getPrincipal();
            return user.getId();
        }
        throw new NotFoundException("User ID not found in token");
    }
}

