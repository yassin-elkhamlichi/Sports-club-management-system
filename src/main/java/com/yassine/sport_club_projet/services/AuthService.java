package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.enums.Role;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.entites.UserIdentity;
import com.yassine.sport_club_projet.exceptions.UserNotFoundException;
import com.yassine.sport_club_projet.mapper.UserIdentityMapper;
import com.yassine.sport_club_projet.mapper.UserMapper;
import com.yassine.sport_club_projet.repositories.UserIdentityRepository;
import com.yassine.sport_club_projet.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserIdentityRepository userIdentityRepository;
    private final UserIdentityMapper userIdentityMapper;
    @Value("${app.jwt.tokenRefreshExpirationTime}")
    private long tokenRefreshExpirationTime;

    public JwtResponseDto login(@Valid AuthUserDto authUserDto, HttpServletResponse response, HttpServletRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authUserDto.getEmail(),
                            authUserDto.getPassword()
                    )
            );

            User user = userRepository.findByEmail(authUserDto.getEmail()).orElseThrow();
            String tokenAccess = getTokenAccess(response, request, user);
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

    public JwtResponseDto signUp(@Valid UserSignUpDto userSignUpDto, HttpServletResponse response, HttpServletRequest request) {
        var user = userMapper.toEntity(userSignUpDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.MEMBER);
        userRepository.save(user);
        getTokenAccess(response,request,user);
        return new JwtResponseDto(getTokenAccess(response,request,user));
    }

    private String getTokenAccess(HttpServletResponse response, HttpServletRequest request, User user) {
        String tokenAccess = jwtService.generateAccessToken(user, request);
        String tokenRefresh = jwtService.generateRefreshToken(user, request);

        // for save refresh token in cookie
        ResponseCookie cookie = ResponseCookie.from("refreshToken", tokenRefresh)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(tokenRefreshExpirationTime)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return tokenAccess;
    }

    @Transactional
    public User findOrCreateOauthUser(
            OauthResponseDto oauthResponseDto
    ){
        var userIdentity = userIdentityRepository.findByProviderAndProviderUserId(oauthResponseDto.getProvider(),oauthResponseDto.getProviderUserId());

        if(userIdentity.isPresent()){
            return userIdentity.get().getUser();
        }

        var existsUser = userRepository.findByEmail(oauthResponseDto.getEmail());
        UserIdentity identity = userIdentityMapper.toEntity(oauthResponseDto);
        if(existsUser.isPresent()){
            User user =  existsUser.get();
            identity.setUser(user);

            if(oauthResponseDto.getEmailVerified() && !user.getEmailVerified()){
                user.setEmailVerified(true);
            }

            user.setUserIdentity(identity);
            userRepository.save(user);
            return  user;
        }

        // 3. Create new user
        User newUser = userMapper.toEntity(oauthResponseDto);
        newUser.setUserIdentity(identity);
        identity.setUser(newUser);
        newUser.setRole(Role.MEMBER);
        userRepository.save(newUser);

        return newUser;
    }
}
