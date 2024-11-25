package com.example.Tez_Yetkaz.controller;

import com.example.Tez_Yetkaz.dto.auth.ForgotPasswordDto;
import com.example.Tez_Yetkaz.dto.auth.LoginDto;
import com.example.Tez_Yetkaz.dto.auth.UserCreateDto;
import com.example.Tez_Yetkaz.dto.email.OTPVerificationRequest;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.email.AuthService;
import com.example.Tez_Yetkaz.service.email.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public ResponseData<?> registerUser(@RequestBody UserCreateDto userCreateDto) {
        return this.authService.registerUser(userCreateDto);
    }

    @PostMapping("/verification")
    public ResponseData<?> verificationOTP(@RequestBody OTPVerificationRequest otpVerificationRequest) {
        return this.authService.verificationOTP(otpVerificationRequest);
    }

    @PostMapping("/login")
    public ResponseData<?> loginUser(@RequestBody LoginDto loginDto) {
        return this.authenticationService.login(loginDto);
    }

    @PostMapping("forgot-password/{email}")
    public ResponseData<?> forgotPassword(@PathVariable String email){
        return this.authService.forgotPassword(email);
    }

    @PostMapping("forgot-password-confirmation")
    public ResponseData<?> forgotPasswordConfirmation(@RequestBody OTPVerificationRequest otpVerificationRequest){
        return this.authService.OTPForForgot(otpVerificationRequest);
    }

    @PutMapping("update-for-password")
    public ResponseData<?> updateForPassword(@RequestBody ForgotPasswordDto forgotPasswordDto){
        return this.authService.updateForPassword(forgotPasswordDto);
    }
}
