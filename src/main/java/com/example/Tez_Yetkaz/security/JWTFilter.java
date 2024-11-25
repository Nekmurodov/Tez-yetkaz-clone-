package com.example.Tez_Yetkaz.security;

import com.example.Tez_Yetkaz.repository.TokenRepository;
import com.example.Tez_Yetkaz.util.RestConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTProvider jwtProvider;
    private final UserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().contains(RestConstant.BASE_PATH_V1 + "/auth")){
            filterChain.doFilter(request, response);
            return;
        }
        final String authHeader = request.getHeader(RestConstant.AUTHORIZATION_HEADER);
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith(RestConstant.TOKEN_TYPE)){
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtProvider.extractUsername(jwt);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            boolean isTokenValid = tokenRepository.findByToken(jwt)
                    .map(token ->  !token.isExpired() && !token.isRevoked())
                    .orElse(false);
            try{
                if (jwtProvider.isTokenValid(jwt, userDetails) && isTokenValid){
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (Exception exception){
                throw new ServletException(exception.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
