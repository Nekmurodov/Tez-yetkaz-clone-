package com.example.Tez_Yetkaz.service.email;

import com.example.Tez_Yetkaz.dto.auth.ForgotPasswordDto;
import com.example.Tez_Yetkaz.dto.auth.UserCreateDto;
import com.example.Tez_Yetkaz.dto.email.MessageToEmail;
import com.example.Tez_Yetkaz.dto.email.OTPVerificationRequest;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.exception.AlreadyExistException;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.mapper.AuthMapper;
import com.example.Tez_Yetkaz.repository.UserRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.service.email.impl.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthMapper authMapper;
    private final OtpProvider otpProvider;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public ResponseData<?> registerUser(UserCreateDto userCreateDto) {
        if (userRepository.existsByEmailAndDeletedFalse(userCreateDto.getEmail())) {
            throw new AlreadyExistException("Email already exists!");
        }
        User user = authMapper.toEntity(userCreateDto);
        user.setEnabled(false); // Yangi foydalanuvchilarni boshlang'ich holati "faol emas"
        userRepository.save(user);
        sendingGeneratedOTP(userCreateDto.getEmail());
        return ResponseData.successResponse(authMapper.toDto(user));
    }

    public ResponseData<?> verificationOTP(OTPVerificationRequest otpVerificationRequest) {
        if (!validateOTP(otpVerificationRequest.getEmail(), otpVerificationRequest.getOtp())) {
            return new ResponseData<>("OTP verification failed!", false);
        }
        return ResponseData.successResponse("Successful");
    }

    private boolean validateOTP(String email, Integer otp) {
        Integer otpFromOTP = otpProvider.getOTPByKey(email);
        User user = userRepository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new NotFoundException("Email not found"));
        if (otpFromOTP != null && otpFromOTP.equals(otp)) {
            otpProvider.removeOTPFromCache(user.getUsername());
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
        throw new RuntimeException("Invalid OTP");
    }

    private void sendingGeneratedOTP(String email) {
        Integer optValue = otpProvider.generatorOTP(email);
        if (optValue == -1) {
            throw new RuntimeException("OTP generation failed");
        }

        User user = userRepository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() -> new NotFoundException("User not found"));
        List<String> recipients = Collections.singletonList(user.getUsername());

        MessageToEmail message = new MessageToEmail();
        message.setBody("One-time password: " + optValue);
        message.setSubject("Assalomu Alaykum");
        message.setRecipients(recipients);

        emailService.sendMessage(message);
    }


    public ResponseData<?> forgotPassword(String email) {
        Optional<User> optionalUser = userRepository.findByEmailAndDeletedFalse(email);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException("Email not found");
        }
        sendingGeneratedOTP(email);
        User user = optionalUser.get();
        user.setEnabled(false);
        userRepository.save(user);
        return ResponseData.successResponse("Success");
    }

    public ResponseData<?> OTPForForgot(OTPVerificationRequest otpVerificationRequest) {
        Integer otpFromOTP = otpProvider.getOTPByKey(otpVerificationRequest.getEmail());
        User user = userRepository.findByEmailAndDeletedFalse(otpVerificationRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("Email not found"));
        if (otpFromOTP != null && otpFromOTP.equals(otpVerificationRequest.getOtp())) {
            otpProvider.removeOTPFromCache(user.getUsername());
            user.setEnabled(true);
            userRepository.save(user);

            return ResponseData.successResponse("Success");
        }
        return new ResponseData<>("Invalid OTP",false);
    }

    public ResponseData<?> updateForPassword(ForgotPasswordDto forgotPasswordDto) {
        Optional<User> userOptional = userRepository.findByEmailAndDeletedFalse(forgotPasswordDto.getEmail());
        if (userOptional.isEmpty()) {
            throw  new NotFoundException("Email not found");
        }
        User user = userOptional.get();
        user.setPassword(passwordEncoder.encode(forgotPasswordDto.getPassword()));
        userRepository.save(user);
        return ResponseData.successResponse("Success");
    }
}