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
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/user/login").permitAll()
                        .requestMatchers("/errors").permitAll()
                        .requestMatchers("/v3/api-docs", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST, "/members").permitAll()
                        .requestMatchers(HttpMethod.GET, "/subscriptionPlans").permitAll()

                        // Authenticated user endpoints
                        .requestMatchers(HttpMethod.GET, "/subscription/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/subscriptions/{id}/renew").authenticated()
                        .requestMatchers(HttpMethod.POST, "/subscriptions/{id}/stop").authenticated()
                        .requestMatchers(HttpMethod.GET, "/tickets/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/tickets/purchase/member/{id}/match/{matchId}")
                        .authenticated()
                        .requestMatchers(HttpMethod.GET, "/matches/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/user/refresh").authenticated()

                        // Subscription Admin endpoints
                        .requestMatchers(HttpMethod.POST, "/subscriptions/{memberId}").hasAnyRole("ADMIN", "MEMBER")
                        .requestMatchers(HttpMethod.PUT, "/subscriptions/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/subscriptions/{idSubs}/changePlan")
                        .hasAnyRole("ADMIN", "MEMBER")
                        .requestMatchers(HttpMethod.DELETE, "/subscriptions/{id}").hasRole("ADMIN")

                        // Ticket Admin endpoints
                        .requestMatchers(HttpMethod.POST, "/tickets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/tickets/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tickets/**").hasRole("ADMIN")

                        // Match Admin endpoints
                        .requestMatchers(HttpMethod.POST, "/matches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/matches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/matches/**").hasRole("ADMIN")

                        // Member endpoints
                        .requestMatchers(HttpMethod.GET, "/members/{id}").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/members/{id}").hasAnyRole("MEMBER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/members/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/members").hasRole("ADMIN")

                        // Coach endpoints
                        .requestMatchers(HttpMethod.GET, "/coach/{id}").hasAnyRole("COACH", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/coach/{id}").hasAnyRole("COACH", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/coach").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/coach/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/coach").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/coach/{coachId}/team/{teamId}").hasAnyRole("COACH", "ADMIN")

                        // Player endpoints
                        .requestMatchers(HttpMethod.GET, "/player/{id}").hasAnyRole("PLAYER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/player/{id}").hasAnyRole("PLAYER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/player").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/player/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/player").hasRole("ADMIN")

                        // Team endpoints
                        .requestMatchers(HttpMethod.POST, "/Team/{teamId}/player/{playerId}")
                        .hasAnyRole("COACH", "ADMIN")

                        // Ticket endpoints
                        .requestMatchers(HttpMethod.POST, "/tickets/purchase/member/{id}/match/{matchId}")
                        .hasAnyRole("MEMBER", "ADMIN")

                        .anyRequest().authenticated())

                .addFilterBefore(validationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
