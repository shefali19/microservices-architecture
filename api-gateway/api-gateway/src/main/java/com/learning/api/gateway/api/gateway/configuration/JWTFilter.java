package com.learning.api.gateway.api.gateway.configuration;

import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.Base64;

@Component
@Slf4j
public class JWTFilter  extends AbstractNameValueGatewayFilterFactory {

    private String secretKey = "my-secret-key";

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String getValidateToken(String token) {
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
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            try {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Missing auth header");
                }
                String authorizationValue = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                String[] auth = authorizationValue.split(" ");
                if (auth.length != 2 || !"Bearer".equals(auth[0])) {
                    throw new RuntimeException("Enter valid auth structure");
                }
                String token = auth[1];
                String username = getValidateToken(token);
                ServerHttpRequest request = exchange.getRequest().mutate().
                        header("x-auth-login-username", username).
                        build();
                return chain.filter(exchange.mutate().request(request).build());
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
            return null;
        };
    }

    public static class Config {
        }

    }

