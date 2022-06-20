package com.learning.loginservice.login.jwtsecurity;

import com.learning.loginservice.login.service.JwtUserDetailsService;
import io.jsonwebtoken.*;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Collections;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            CsrfToken csrfToken = (CsrfToken) request.getAttribute("_csrf");
            response.setHeader("CSRF-TOKEN-VALUE",csrfToken.getToken());

            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if(header!=null) {
                String[] authElemants = header.split(" ");
                if (authElemants.length == 2 && "Bearer".equals(authElemants[0])) {
                    SecurityContextHolder.getContext().setAuthentication(
                            validateToken(authElemants[1]));
                }
            }
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
            return;
        } chain.doFilter(request, response);
    }

    public Authentication validateToken(String token) {
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        UserDetails user = jwtUserDetailsService.loadUserByUsername(username);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

}
