package com.example.Tez_Yetkaz.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDto {
    private String email;
    private String password;
    private String prePassword;
}
