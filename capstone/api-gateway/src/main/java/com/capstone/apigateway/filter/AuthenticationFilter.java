package com.capstone.apigateway.filter;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.capstone.apigateway.util.JwtUtil;
import com.ctc.wstx.shaded.msv_core.verifier.regexp.Token;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
    	return (exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                List<String> authHeaderList = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

                if (authHeaderList == null || authHeaderList.isEmpty() || !authHeaderList.get(0).startsWith("Bearer ")) {
                    // Return a 401 Unauthorized response
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                }

                String authToken = authHeaderList.get(0).substring(7);
                try {
                    jwtUtil.validateToken(authToken);
                } catch (Exception e) {
                    // Return a 403 Forbidden response if token is invalid
                    exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                    return exchange.getResponse().setComplete();
                }
            }
            return chain.filter(exchange);  // Continue filter chain if everything is valid
        };
    }

    public static class Config {

    }
}