package com.example.Tez_Yetkaz.service.email;

import com.example.Tez_Yetkaz.dto.auth.AuthenticationDto;
import com.example.Tez_Yetkaz.dto.auth.LoginDto;
import com.example.Tez_Yetkaz.entity.user.Token;
import com.example.Tez_Yetkaz.entity.user.User;
import com.example.Tez_Yetkaz.enums.State;
import com.example.Tez_Yetkaz.exception.NotFoundException;
import com.example.Tez_Yetkaz.repository.TokenRepository;
import com.example.Tez_Yetkaz.repository.UserRepository;
import com.example.Tez_Yetkaz.response.ResponseData;
import com.example.Tez_Yetkaz.security.JWTProvider;
import com.example.Tez_Yetkaz.util.MessageKey;
import com.example.Tez_Yetkaz.util.MessageService;
import com.example.Tez_Yetkaz.util.RestConstant;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTProvider jwtProvider;
    private final TokenRepository tokenRepository;

    @Override
    public ResponseData<AuthenticationDto> login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

        User user = userRepository.findByEmailAndDeletedFalse(loginDto.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(MessageService.getMessage(MessageKey.USER_NOT_FOUND)));
        String accessToken = jwtProvider.generateAccessToken(user);
        String refreshToken = jwtProvider.generateRefreshToken(user);
        revokeAllUserToken(user);
        saveUserToken(user, accessToken);
        AuthenticationDto authenticationResponse = AuthenticationDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        return ResponseData.successResponse(authenticationResponse);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith(RestConstant.TOKEN_TYPE)){
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtProvider.extractUsername(refreshToken);
        if (userEmail != null){
            User user = this.userRepository.findByEmailAndDeletedFalse(userEmail).orElseThrow(
                    () -> new NotFoundException(MessageService.getMessage(MessageKey.USER_NOT_FOUND)));
            if (jwtProvider.isTokenValid(refreshToken, user)){
                String accessToken = jwtProvider.generateAccessToken(user);
                revokeAllUserToken(user);
                saveUserToken(user, accessToken);
                AuthenticationDto authenticationResponse = AuthenticationDto.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authenticationResponse);
            }
        }
    }

    private void revokeAllUserToken(User user){
        List<Token> tokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (tokens.isEmpty()) return;
        tokens.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepository.saveAll(tokens);
    }
    private void saveUserToken(User user, String jwtToken){
        Token token = Token.builder()
                .user(user)
                .tokenType(RestConstant.TOKEN_TYPE)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .state(State.NONE)
                .build();
        tokenRepository.save(token);
    }


}
