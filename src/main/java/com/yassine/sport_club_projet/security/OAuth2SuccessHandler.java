package com.yassine.sport_club_projet.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yassine.sport_club_projet.dto.OauthProvider;
import com.yassine.sport_club_projet.dto.OauthResponseDto;
import com.yassine.sport_club_projet.dto.UserResponseDto;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.mapper.UserIdentityMapper;
import com.yassine.sport_club_projet.services.AuthService;
import com.yassine.sport_club_projet.services.JwtService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {
    private final AuthService authService;
    private final JwtService jwtService;
    private final UserIdentityMapper userIdentityMapper;
    @Value("${app.jwt.tokenRefreshExpirationTime}")
    private long tokenRefreshExpirationTime;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();

        System.out.println("oidcUser"+ oidcUser.toString());
        System.out.println("family name"+ oidcUser.getFamilyName());
        System.out.println("given name"+ oidcUser.getGivenName());


        OauthResponseDto oauthResponseDto = userIdentityMapper.toDto(oidcUser);
        for(OauthProvider prov : OauthProvider.values()){
            if((oidcUser.getIssuer().toString().toUpperCase()).contains(prov.toString())){
                oauthResponseDto.setProvider(prov);
            }
        }
        User user = authService.findOrCreateOauthUser(oauthResponseDto);
        String accessToken = jwtService.generateAccessToken(user,request);
        String refreshToken = jwtService.generateRefreshToken(user,request);
        ResponseCookie cookie = ResponseCookie.from("refreshToken", refreshToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(tokenRefreshExpirationTime)
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setId(user.getId());
        userResponseDto.setRole(user.getRole());
        userResponseDto.setToken(accessToken);

        response.getWriter().write(new ObjectMapper().writeValueAsString(userResponseDto));

    }
}
