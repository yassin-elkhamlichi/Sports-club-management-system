package com.yassine.sport_club_projet.security;

import com.yassine.sport_club_projet.filter.ValidationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private final ValidationFilter validationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers("/errors").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()


                        // Role-based access
                        .requestMatchers(HttpMethod.GET, "/members/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/members/**").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/coach/**").hasAnyRole("COACH", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/coach/**").hasAnyRole("COACH", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/player/**").hasAnyRole("PLAYER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/player/**").hasAnyRole("PLAYER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/user/refresh").hasAnyRole("PLAYER", "ADMIN", "COACH", "MEMBER")

                        // Admin has access to everything
                        .requestMatchers("/**").hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(validationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(c -> {
                    c.authenticationEntryPoint(
                            new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
                    c.accessDeniedHandler(((request, response, accessDeniedException) -> response
                            .setStatus(HttpStatus.FORBIDDEN.value())));
                });
        return http.build();
    }
}
