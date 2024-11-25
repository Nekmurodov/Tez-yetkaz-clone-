package com.example.Tez_Yetkaz.service.email;

import com.example.Tez_Yetkaz.dto.auth.AuthenticationDto;
import com.example.Tez_Yetkaz.dto.auth.LoginDto;
import com.example.Tez_Yetkaz.response.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface IAuthenticationService {

    ResponseData<AuthenticationDto> login(LoginDto loginDto);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
