package com.learning.api.gateway.api.gateway.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Base64;

@Component
@Slf4j
public class JWTFilter extends AbstractGatewayFilterFactory {

    @Value("${jwt.secret}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getValidateToken(String token)  {
        log.info("Validating the token:::::");
        String username = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        if(username.isEmpty()){
            throw new RuntimeException("JWT authentication is not allowed" + username);
        }
        return username;
    }

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing auth header::: Please provide valid headera");
            }
            String authorizationValue = exchange.getRequest().getHeaders().
                    get(HttpHeaders.AUTHORIZATION).get(0);
            String[] auth = authorizationValue.split(" ");
            if (auth.length != 2 || !"Bearer".equals(auth[0])) {
                throw new RuntimeException("Enter valid auth structure");
            }
            String token = auth[1];
            try {
                getValidateToken(token);
            } catch (Exception ex) {
                log.error(ex.getMessage());
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.BAD_REQUEST);
                return response.setComplete();
            }
            Claims claims = getClaims(token);
            exchange.getRequest().mutate().header("id", String.valueOf(claims.get("id"))).build();
            return chain.filter(exchange);
        };
    }

     public static class Config {
        }

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

}

