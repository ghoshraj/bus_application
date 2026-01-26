package com.gateway.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        // Custom JWT authentication converter to extract roles from JWT claims
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new RoleConverter());

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                        // PUBLIC APIs - No authentication required
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**",

                                "/auth/swagger-ui/**",
                                "/auth/v3/api-docs/**",

                                "/users/swagger-ui/**",
                                "/users/v3/api-docs/**",

                                "/vehicles/swagger-ui/**",
                                "/vehicles/v3/api-docs/**",

                                "/routes/swagger-ui/**",
                                "/routes/v3/api-docs/**",

                                "/schedules/swagger-ui/**",
                                "/schedules/v3/api-docs/**",

                                "/seats/swagger-ui/**",
                                "/seats/v3/api-docs/**",

                                "/favicon.ico"
                        ).permitAll()

                        // ADMIN AND SUPER_ADMIN APIs (GET)
                        .pathMatchers(HttpMethod.GET, "/companies/{id}", "/companies/registration/**")
                            .hasAnyRole("ADMIN", "SUPER_ADMIN")

                        // SUPER_ADMIN ONLY APIs (POST)
                        .pathMatchers(HttpMethod.POST, "/super-admin/**")
                            .hasRole("SUPER_ADMIN")

                        // ADMIN ONLY APIs (POST)
                        .pathMatchers(HttpMethod.POST, "/schedules/{id}/assign-driver", "/buses", "/companies/apply", "/routes")
                            .hasRole("ADMIN")

                        // BUS_DRIVER ONLY APIs (POST)
                        .pathMatchers(HttpMethod.POST, "/schedules", "/drivers")
                            .hasRole("BUS_DRIVER")

                        // BUS_DRIVER AND ADMIN APIs (GET)
                        .pathMatchers(HttpMethod.GET, "/drivers/{id}", "/drivers/operator/**", "/drivers/vehicle/**")
                            .hasAnyRole("BUS_DRIVER", "ADMIN")

                        // ADMIN, BUS_DRIVER, USER APIs (GET)
                        .pathMatchers(HttpMethod.GET, "/users/{id}")
                            .hasAnyRole("ADMIN", "BUS_DRIVER", "USER")

                        // USER AND BUS_DRIVER APIs
                        .pathMatchers(HttpMethod.POST, "/seats/lock", "/seats/unlock")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.GET, "/seats/availability/**")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.GET, "/schedules/{id}", "/schedules/route/**")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.GET, "/routes/{id}", "/routes/search")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.GET, "/seats/bus/**", "/buses/{id}", "/buses/operator/**")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.POST, "/users/profile")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.POST, "/payments/initiate", "/payments/verify", "/payments/refund")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.POST, "/bookings")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.GET, "/bookings/{id}", "/bookings/user/**")
                            .hasAnyRole("USER", "BUS_DRIVER")
                        .pathMatchers(HttpMethod.POST, "/bookings/{id}/cancel")
                            .hasAnyRole("USER", "BUS_DRIVER")

                        // Default: All other requests require authentication
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                new ReactiveJwtAuthenticationConverterAdapter(jwtConverter)
                        ))
                )
                .build();
    }

    /**
     * Custom converter to extract roles from JWT "roles" claim
     * Converts roles to Spring Security GrantedAuthority with ROLE_ prefix
     */
    static class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        @SuppressWarnings("unchecked")
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            try {
                log.debug("JWT Claims: {}", jwt.getClaims());
                List<String> roles = (List<String>) jwt.getClaims().get("roles");
                log.debug("Extracted roles from JWT: {}", roles);

                if (roles == null || roles.isEmpty()) {
                    log.warn("No roles found in JWT token");
                    return List.of();
                }

                List<GrantedAuthority> authorities = roles.stream()
                        .map(role -> role.toUpperCase()) // Ensure uppercase
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                        .collect(Collectors.toList());

                log.debug("Converted authorities: {}", authorities);
                return authorities;
            } catch (Exception e) {
                log.error("Error extracting roles from JWT: {}", e.getMessage(), e);
                return List.of();
            }
        }
    }
}
