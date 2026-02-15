package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.AuthUserDto;
import com.yassine.sport_club_projet.dto.JwtResponseDto;
import com.yassine.sport_club_projet.dto.MemberResponseDto;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.repositories.UserRepository;
import com.yassine.sport_club_projet.services.JwtService;
import io.jsonwebtoken.Jwt;
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

import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> auth(
            @Valid @RequestBody AuthUserDto authUserDto , HttpServletRequest request
    ){
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

            // send refresh token in header
            return ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body(new JwtResponseDto(tokenAccess));
        }
        catch(Exception e){
            throw new RuntimeException();
        }
    }

    @PostMapping("refresh")
    public ResponseEntity<?> refresh(
            @CookieValue(value = "refreshToken") String refreshToken , HttpServletRequest request) {
        if (!jwtService.validateToken(refreshToken))
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    Map.of("error", "the token is invalid"));
        String email = jwtService.getEmail(refreshToken);
        var user = userRepository.findByEmail(email).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user , request);
        return ResponseEntity.ok(new JwtResponseDto(accessToken));

    }

//    @PostMapping("validate")
//    public Boolean validate(
//            @RequestHeader("Authorization") String token
//    ){
//        var tokenWithoutBearer = token.replace("Bearer ", "");
//        return jwtService.validateToken(tokenWithoutBearer);
//    }

    @GetMapping("/me")
    public ResponseEntity<String> me() {
        var authentication  = SecurityContextHolder.getContext().getAuthentication();
        var email = authentication.getPrincipal();
        var user = userRepository.findByEmail((String) email).orElse(null);
        if(user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.getFirstname());
    }

}
