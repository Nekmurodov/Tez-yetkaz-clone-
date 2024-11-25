package com.example.Tez_Yetkaz.dto.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OTPVerificationRequest {

    private String email;
    private Integer otp;

}
