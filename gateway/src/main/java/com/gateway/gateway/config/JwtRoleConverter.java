package com.gateway.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.gateway.gateway.constant.GatewayConstants.*;

@Component
@Slf4j
public class JwtRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    /**
     * Custom converter to extract roles from JWT "roles" claim
     * Converts roles to Spring Security GrantedAuthority with ROLE_ prefix
     */

    @Override
    @SuppressWarnings("unchecked")
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        try {
            log.debug(LOG_JWT_CLAIMS, jwt.getClaims());
            List<String> roles = (List<String>) jwt.getClaims().get(CLAIM_ROLES);
            log.debug(LOG_EXTRACTED_ROLES, roles);
            if (roles == null || roles.isEmpty()) {
                log.warn(LOG_NO_ROLES);
                return List.of();
            }

            List<GrantedAuthority> authorities = roles.stream()
                    .map(String::toUpperCase)
                    .map(role -> new SimpleGrantedAuthority(ROLE_PREFIX + role))
                    .collect(Collectors.toList());

            log.debug(LOG_CONVERTED_AUTHORITIES, authorities);
            return authorities;

        } catch (Exception e) {
            log.error(LOG_ROLE_EXTRACTION_ERROR, e);
            return List.of();
        }
    }
}
