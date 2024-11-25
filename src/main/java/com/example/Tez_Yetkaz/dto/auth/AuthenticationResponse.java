package com.example.Tez_Yetkaz.dto.auth;

import com.example.Tez_Yetkaz.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private User user;

}
