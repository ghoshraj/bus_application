package com.gateway.gateway.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static com.gateway.gateway.constant.GatewayConstants.*;

@Slf4j
@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class GatewaySecurityConfig {

    private final JwtRoleConverter jwtRoleConverter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        // Custom JWT authentication converter to extract roles from JWT claims
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(jwtRoleConverter);

        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                        // PUBLIC APIs - No authentication required
                        .pathMatchers(AUTH_ALL)
                        .permitAll()

                        // Swagger & Public resources
                        .pathMatchers(
                                SWAGGER_UI_HTML, SWAGGER_UI, WEBJARS, API_DOCS,
                                AUTH_SWAGGER_UI, AUTH_API_DOCS,
                                USERS_SWAGGER_UI, USERS_API_DOCS,
                                VEHICLES_SWAGGER_UI, VEHICLES_API_DOCS,
                                ROUTES_SWAGGER_UI, ROUTES_API_DOCS,
                                SCHEDULES_SWAGGER_UI, SCHEDULES_API_DOCS,
                                SEATS_SWAGGER_UI, SEATS_API_DOCS,
                                BOOKINGS_SWAGGER_UI, BOOKINGS_API_DOCS,
                                PAYMENTS_SWAGGER_UI, PAYMENTS_API_DOCS,
                                FAVICON
                        ).permitAll()

                        // ADMIN & SUPER_ADMIN (GET)
                        .pathMatchers(HttpMethod.GET,
                                COMPANY_BY_ID, COMPANY_REGISTRATION)
                        .hasAnyRole(ADMIN, SUPER_ADMIN)

                        // SUPER_ADMIN only (POST)
                        .pathMatchers(HttpMethod.POST, SUPER_ADMIN_ALL)
                        .hasRole(SUPER_ADMIN)

                        // ADMIN only (POST)
                        .pathMatchers(HttpMethod.POST, SCHEDULE_ASSIGN_DRIVER, BUSES, COMPANY_APPLY, ROUTES)
                        .hasRole(ADMIN)

                        // BUS_DRIVER only (POST)
                        .pathMatchers(HttpMethod.POST, SCHEDULES, DRIVERS)
                        .hasRole(BUS_DRIVER)

                        // BUS_DRIVER & ADMIN (GET)
                        .pathMatchers(HttpMethod.GET, DRIVER_BY_ID, DRIVER_OPERATOR, DRIVER_VEHICLE)
                        .hasAnyRole(BUS_DRIVER, ADMIN)

                        // ADMIN, BUS_DRIVER, USER (GET)
                        .pathMatchers(HttpMethod.GET, USER_BY_ID)
                        .hasAnyRole(ADMIN, BUS_DRIVER, USER)

                        // USER & BUS_DRIVER APIs
                        .pathMatchers(HttpMethod.POST, SEAT_LOCK, SEAT_UNLOCK)
                        .hasAnyRole(USER, BUS_DRIVER)

                        .pathMatchers(HttpMethod.GET, SEAT_AVAILABILITY, SCHEDULE_BY_ID,
                                SCHEDULE_ROUTE, ROUTE_BY_ID, ROUTE_SEARCH, SEAT_BUS,
                                BUS_BY_ID, BUS_OPERATOR, BOOKING_BY_ID, BOOKING_USER)
                        .hasAnyRole(USER, BUS_DRIVER)

                        .pathMatchers(HttpMethod.POST, USER_PROFILE, PAYMENT_INITIATE, PAYMENT_VERIFY,
                                PAYMENT_REFUND, BOOKINGS, BOOKING_CANCEL).hasAnyRole(USER, BUS_DRIVER)

                        // Default: All other requests require authentication
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt.jwtAuthenticationConverter
                                (new ReactiveJwtAuthenticationConverterAdapter(jwtConverter)))).build();
    }
}
