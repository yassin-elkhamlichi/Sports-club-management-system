package com.yassine.sport_club_projet.controller;

import com.yassine.sport_club_projet.dto.AuthUserDto;
import com.yassine.sport_club_projet.dto.JwtResponseDto;
import com.yassine.sport_club_projet.dto.MemberResponseDto;
import com.yassine.sport_club_projet.repositories.UserRepository;
import com.yassine.sport_club_projet.services.JwtService;
import io.jsonwebtoken.Jwt;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> auth(
            @Valid @RequestBody AuthUserDto authUserDto
    ){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authUserDto.getEmail(),
                        authUserDto.getPassword()
                )
        );
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String token = jwtService.generateToken(authUserDto.getEmail() , request);
        return ResponseEntity.ok(new JwtResponseDto(token));

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
