package com.example.Tez_Yetkaz.dto.auth;

import com.example.Tez_Yetkaz.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Date birthDate;
    private Boolean gender;
    private Role role;
}
