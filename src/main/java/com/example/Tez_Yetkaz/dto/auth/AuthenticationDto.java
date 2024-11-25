package com.example.Tez_Yetkaz.dto.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationDto  {

    private String accessToken;

    private String refreshToken;

}
