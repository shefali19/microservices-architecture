package com.learning.api.gateway.api.gateway.configuration;

import com.learning.api.gateway.api.gateway.dto.loginDTO;
import org.apache.http.HttpHeaders;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {
    private final WebClient.Builder webBuilder;

    public AuthFilter(WebClient.Builder webBuilder) {
        this.webBuilder = webBuilder;
    }
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new RuntimeException("Missing auth header");
             }
            String authorizationValue = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] auth = authorizationValue.split(" ");
            if(auth.length!=2 || !"Bearer".equals(auth[0])){
                throw new RuntimeException("Enter valid auth structure");
            }
            return webBuilder.build().post().uri("http://localhost:9005/login/validateToken?token"
            + auth[1]).retrieve().bodyToMono(loginDTO.class).map(loginDTO ->{
                exchange.getRequest().mutate().header("X-auth-login-username",
                        String.valueOf(loginDTO.getUsername()));
                return exchange;
            }).flatMap(chain::filter);
        };
    }
    public static class Config{
    }
}
