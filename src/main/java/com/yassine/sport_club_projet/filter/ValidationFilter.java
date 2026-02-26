package com.yassine.sport_club_projet.filter;

import com.yassine.sport_club_projet.entites.Role;
import com.yassine.sport_club_projet.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Component
@AllArgsConstructor
public class ValidationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,  HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            var authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            var tokenWithoutBearer = authHeader.replace("Bearer ", "");
            if (!jwtService.validateToken(tokenWithoutBearer)){
                filterChain.doFilter(request, response);
                return;
            }

            String ipInput = normalizeIp(jwtService.extractClaim(tokenWithoutBearer , "user_ip"));
            String ipRequest = normalizeIp(request.getRemoteAddr());

            System.out.println("IP from token: " + ipInput);
            System.out.println("IP from request: " + ipRequest);
            if (!ipInput.equals(ipRequest)){
                throw new BadCredentialsException("Token stolen or IP changed!");
            }
            Role role = jwtService.getRole(tokenWithoutBearer);
            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));

            var authentication = new UsernamePasswordAuthenticationToken(
                    jwtService.getEmail(tokenWithoutBearer),
                    null,
                    authorities
            );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            SecurityContextHolder.getContext().setAuthentication(
                    authentication
            );
            filterChain.doFilter(request, response);

    }

    private String normalizeIp(String ip) {
        try {
            InetAddress addr = InetAddress.getByName(ip);
            if (addr instanceof Inet6Address) {
                byte[] bytes = addr.getAddress();
                if (isIpv4Mapped(bytes)) {
                    return InetAddress.getByAddress(
                            new byte[]{bytes[12], bytes[13], bytes[14], bytes[15]}
                    ).getHostAddress();
                }
            }
            return addr.getHostAddress();
        } catch (UnknownHostException e) {
            return ip;
        }
    }

    private boolean isIpv4Mapped(byte[] bytes) {
        if (bytes.length != 16) return false;
        for (int i = 0; i < 10; i++) if (bytes[i] != 0) return false;
        return bytes[10] == (byte) 0xFF && bytes[11] == (byte) 0xFF;
    }
}
