package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.AuthUserDto;
import com.yassine.sport_club_projet.dto.JwtResponseDto;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.UserNotFoundException;
import com.yassine.sport_club_projet.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public JwtResponseDto login(@Valid AuthUserDto authUserDto, HttpServletResponse response, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authUserDto.getEmail(),
                            authUserDto.getPassword()
                    )
            );

            User user = userRepository.findByEmail(authUserDto.getEmail()).orElseThrow();
            String tokenAccess = jwtService.generateAccessToken(user, request);
            String tokenRefresh = jwtService.generateRefreshToken(user, request);

            // for save refresh token in cookie
            ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenRefresh)
                    .httpOnly(true)
                    .secure(false)
                    .path("/")
                    .maxAge(7 * 24 * 60 * 60)
                    .sameSite("Lax")
                    .build();

            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
            // send refresh token in header
            return new JwtResponseDto(tokenAccess);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public String me() throws UserNotFoundException {
        var authentication  = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getPrincipal();
        var user = userRepository.findByEmail((String) email).orElse(null);
        if(user == null)
            throw new UserNotFoundException("user not found");
        return user.getFirstname();

    }

    public String refresh(String refreshToken, HttpServletRequest request) {
        if (!jwtService.validateToken(refreshToken))
           throw new BadCredentialsException("invalid refresh token");
        String email = jwtService.getEmail(refreshToken);
        var user = userRepository.findByEmail(email).orElseThrow();
        return jwtService.generateAccessToken(user , request);
    }
}
