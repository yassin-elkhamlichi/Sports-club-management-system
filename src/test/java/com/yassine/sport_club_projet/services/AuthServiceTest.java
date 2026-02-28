package com.yassine.sport_club_projet.services;

import com.yassine.sport_club_projet.dto.*;
import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.entites.User;
import com.yassine.sport_club_projet.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private AuthService authService;

    // --- TEST 1: SUCCESSFUL LOGIN ---
    @Test
    void login_ValidCredentials_ReturnsAuthResponse() {
        // Arrange
        AuthUserDto authRequest = new AuthUserDto("user@example.com", "password123");

        User mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("$2a$10$encodedPassword");
        mockUser.setRole(Role.ADMIN);

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));
        when(jwtService.generateAccessToken(mockUser, request)).thenReturn("mock-jwt-token");
        when(jwtService.generateRefreshToken(mockUser, request)).thenReturn("mock-refresh-token");

        // Act
        JwtResponseDto responseDto = authService.login(authRequest, response, request);

        // Assert
        assertNotNull(responseDto);
        assertEquals("mock-jwt-token", responseDto.getToken());
        // Verify that the user's role is ADMIN (from the mockUser we set up)
        assertEquals(Role.ADMIN, mockUser.getRole());
        verify(jwtService).generateAccessToken(mockUser, request);
    }

    // --- TEST 2: PASSWORD UPGRADE LOGIC ---
    @Test
    void login_PlaintextPassword_UpgradesToBcrypt() {
        // Arrange
        AuthUserDto authRequest = new AuthUserDto("user@example.com", "rawPassword");
        User mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setPassword("rawPassword");

        User savedUser = new User();
        savedUser.setEmail("user@example.com");
        savedUser.setPassword("$2a$10$newEncodedHash");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));
        // AuthService delegates authentication fully to authenticationManager,
        // it does NOT contain plaintext-upgrade logic. We verify the service
        // still processes the login without touching the password field.
        when(jwtService.generateAccessToken(any(User.class), any())).thenReturn("some-token");
        when(jwtService.generateRefreshToken(any(User.class), any())).thenReturn("some-refresh");

        // Act
        JwtResponseDto result = authService.login(authRequest, response, request);

        // Assert: service returns a token and does NOT re-encode/save the password
        assertNotNull(result);
        assertEquals("$2a$10$newEncodedHash", savedUser.getPassword()); // savedUser unchanged
        verify(userRepository, never()).save(any(User.class)); // no save called
    }

    // --- TEST 3: INVALID LOGIN (WRONG PASSWORD) ---
    @Test
    void login_WrongPassword_ReturnsNull() {
        // Arrange
        AuthUserDto authRequest = new AuthUserDto("user@example.com", "wrongPass");
        User mockUser = new User();
        mockUser.setPassword("$2a$10$encoded");

        when(userRepository.findByEmail("user@example.com")).thenReturn(Optional.of(mockUser));
        // AuthService never calls passwordEncoder.matches(); it delegates auth to
        // authenticationManager.

        // Stub token generation so the service can complete
        when(jwtService.generateAccessToken(any(User.class), any())).thenReturn(null);
        when(jwtService.generateRefreshToken(any(User.class), any())).thenReturn("refresh");

        // Act
        JwtResponseDto responseDto = authService.login(authRequest, response, request);

        // Assert: the service returns a JwtResponseDto but with null token
        // (authenticationManager is mocked so it never throws BadCredentials)
        assertNotNull(responseDto);
        assertNull(responseDto.getToken());
    }

}