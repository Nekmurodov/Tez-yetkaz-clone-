package com.example.Tez_Yetkaz.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.common.aliasing.qual.Unique;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @Unique
    private String email;
    private Date birthDate;
    private Boolean gender;
}
