package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.AuthUserDto;
import com.yassine.sport_club_projet.dto.JwtResponseDto;
import com.yassine.sport_club_projet.dto.MemberResponseDto;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.exceptions.UserNotFoundException;
import com.yassine.sport_club_projet.repositories.UserRepository;
import com.yassine.sport_club_projet.services.AuthService;
import com.yassine.sport_club_projet.services.JwtService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.bind.annotation.*;

import java.security.AuthProvider;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> auth(
            @Valid @RequestBody AuthUserDto authUserDto ,  HttpServletResponse response,HttpServletRequest request
    ){
       return ResponseEntity.ok()
               .body(authService.login(authUserDto, response, request));
    }

    @PostMapping("refresh")
    public ResponseEntity<String> refresh(
            @CookieValue(value = "refreshToken") String refreshToken , HttpServletRequest request) {

        return ResponseEntity.ok(authService.refresh(refreshToken , request));

    }

    @PostMapping("validate")
    public Boolean validate(
            @RequestHeader("Authorization") String token
    ){
        var tokenWithoutBearer = token.replace("Bearer ", "");
        return jwtService.validateToken(tokenWithoutBearer);
    }

    @GetMapping("/me")
    public ResponseEntity<String> me() throws UserNotFoundException {
        return ResponseEntity.ok(authService.me());
    }

}
