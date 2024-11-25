package com.example.Tez_Yetkaz.dto.auth;

import com.example.Tez_Yetkaz.valid.PasswordValidate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    @PasswordValidate
    private String password;
}
